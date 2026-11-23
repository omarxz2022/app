const {
  initializeTestEnvironment,
  assertFails,
  assertSucceeds,
} = require("@firebase/rules-unit-testing");
const { test, before, after, beforeEach } = require("node:test");
const fs = require("node:fs");

let testEnv;
const PROJECT_ID = process.env.GCP_PROJECT || "demo-no-project";
const ALICE_UID = "alice_123";
const BOB_UID = "bob_456";

const [emulatorHost, emulatorPortStr] = (process.env.FIRESTORE_EMULATOR_HOST || "127.0.0.1:8085").split(":");
const emulatorPort = parseInt(emulatorPortStr, 10);

before(async () => {
  const rules = fs.readFileSync("./firestore.rules", "utf8");
  testEnv = await initializeTestEnvironment({
    projectId: PROJECT_ID,
    firestore: {
      rules,
      host: emulatorHost,
      port: emulatorPort,
    },
  });
});

after(async () => {
  if (testEnv) {
    await testEnv.cleanup();
  }
});

beforeEach(async () => {
  if (testEnv) {
    await testEnv.clearFirestore();
  }
});

function validProfilePayload(uid, now = new Date(Date.now() - 2000)) {
  return {
    userId: uid,
    displayName: "Omer",
    grade: "Grade 12",
    coins: 0,
    totalAttempted: 0,
    totalCorrect: 0,
    choiceCorrect: 0,
    rewriteCorrect: 0,
    createdAt: now,
    updatedAt: now,
  };
}

test("Unauthenticated user cannot read or write profiles or collection", async () => {
  const unauthDb = testEnv.unauthenticatedContext().firestore();
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).get());
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).set(validProfilePayload(ALICE_UID)));
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).collection("collection").get());
});

test("Owner can create profile, edit name/grade, record quiz answers, and spend coins", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  const created = new Date(Date.now() - 10000);
  const userRef = aliceDb.collection("users").doc(ALICE_UID);

  await assertSucceeds(userRef.set(validProfilePayload(ALICE_UID, created)));

  // Profile edit (name + grade)
  await assertSucceeds(
    userRef.update({
      displayName: "Omer Ahmed",
      grade: "C2 Advanced",
      updatedAt: new Date(Date.now() - 5000),
    })
  );

  // Record rewrite correct (+5 coins)
  await assertSucceeds(
    userRef.update({
      coins: 5,
      totalAttempted: 1,
      totalCorrect: 1,
      choiceCorrect: 0,
      rewriteCorrect: 1,
      updatedAt: new Date(Date.now() - 3000),
    })
  );

  // Reject ghost field / coin inflation exploit (+500 coins in one answer)
  await assertFails(
    userRef.update({
      coins: 505,
      totalAttempted: 2,
      totalCorrect: 2,
      choiceCorrect: 1,
      rewriteCorrect: 1,
      updatedAt: new Date(Date.now() - 1000),
    })
  );

  // Reject immutable createdAt mutation
  await assertFails(
    userRef.update({
      createdAt: new Date(Date.now() - 500),
      updatedAt: new Date(Date.now() - 500),
    })
  );

  // Spend coins
  await assertSucceeds(
    userRef.update({
      coins: 1,
      updatedAt: new Date(Date.now() - 500),
    })
  );
});

test("Cross-user access to profile and collection is blocked", async () => {
  await testEnv.withSecurityRulesDisabled(async (ctx) => {
    await ctx.firestore().collection("users").doc(BOB_UID).set(validProfilePayload(BOB_UID));
  });

  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  await assertFails(aliceDb.collection("users").doc(BOB_UID).get());
  await assertFails(aliceDb.collection("users").doc(BOB_UID).update({ displayName: "Hacked", grade: "1", updatedAt: new Date(Date.now() - 1000) }));
  await assertFails(aliceDb.collection("users").doc(BOB_UID).collection("collection").get());
  await assertFails(aliceDb.collection("users").get());
});

test("Owner can list empty collection and create/increment purchased collectables", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  const created = new Date(Date.now() - 10000);
  const colRef = aliceDb.collection("users").doc(ALICE_UID).collection("collection");

  // Empty list query succeeds
  await assertSucceeds(colRef.get());

  // Purchase item (quantity = 1)
  await assertSucceeds(
    colRef.doc("porsche_gt3").set({
      prizeId: "porsche_gt3",
      userId: ALICE_UID,
      title: "Porsche 911 GT3",
      category: "CAR",
      price: 25,
      quantity: 1,
      createdAt: created,
      updatedAt: created,
    })
  );

  // Increment quantity to 2 on second purchase
  await assertSucceeds(
    colRef.doc("porsche_gt3").update({
      quantity: 2,
      updatedAt: new Date(Date.now() - 1000),
    })
  );

  // Reject invalid category or ghost id field
  await assertFails(
    colRef.doc("fake_item").set({
      id: "fake_item",
      prizeId: "fake_item",
      userId: ALICE_UID,
      title: "Fake",
      category: "SPACESHIP",
      price: 10,
      quantity: 1,
      createdAt: created,
      updatedAt: created,
    })
  );
});
