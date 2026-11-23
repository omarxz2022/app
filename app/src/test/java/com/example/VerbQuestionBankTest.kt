package com.example

import com.example.data.PrizeCatalog
import com.example.data.ProgressStore
import com.example.data.VerbCodexData
import com.example.data.VerbQuestionRepository
import com.example.model.CollectableItem
import com.example.model.ExamCategory
import com.example.model.ExamLength
import com.example.model.ExamPhase
import com.example.model.ExamSuite
import com.example.model.PrizeCategory
import com.example.model.PrizeItem
import com.example.model.QuestionFormat
import com.example.model.UserProfile
import com.example.ui.art.PrizeArtRegistry
import com.example.viewmodel.VerbCraftViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class VerbQuestionBankTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private class FakeProgressStore : ProgressStore {
        val profile = MutableStateFlow<UserProfile?>(null)
        val collection = MutableStateFlow<List<CollectableItem>>(emptyList())
        val wrongIds = MutableStateFlow<List<Int>>(emptyList())

        override fun observeUserProfile(userId: String): Flow<UserProfile?> = profile

        override fun observeCollection(userId: String): Flow<List<CollectableItem>> = collection

        override fun observeWrongQuestionIds(userId: String): Flow<List<Int>> = wrongIds

        override suspend fun ensureProfileExists(userId: String, defaultName: String): Result<Unit> {
            if (profile.value == null) {
                profile.value = UserProfile(id = userId, userId = userId, displayName = defaultName)
            }
            return Result.success(Unit)
        }

        override suspend fun updateProfile(userId: String, displayName: String, grade: String): Result<Unit> {
            val cur = profile.value ?: UserProfile(id = userId, userId = userId)
            profile.value = cur.copy(displayName = displayName, grade = grade)
            return Result.success(Unit)
        }

        override suspend fun setFastingMode(userId: String, enabled: Boolean): Result<Unit> {
            val cur = profile.value ?: UserProfile(id = userId, userId = userId)
            profile.value = cur.copy(fastingMode = enabled)
            return Result.success(Unit)
        }

        override suspend fun recordAnswer(
            userId: String,
            questionId: Int,
            isCorrect: Boolean,
            isRewrite: Boolean,
        ): Result<Unit> {
            val cur = profile.value ?: UserProfile(id = userId, userId = userId)
            val coinDelta = when {
                !isCorrect -> 0L
                isRewrite -> 5L
                else -> 1L
            }
            profile.value = cur.copy(
                coins = cur.coins + coinDelta,
                totalAttempted = cur.totalAttempted + 1L,
                totalCorrect = cur.totalCorrect + if (isCorrect) 1L else 0L,
                choiceCorrect = cur.choiceCorrect + if (isCorrect && !isRewrite) 1L else 0L,
                rewriteCorrect = cur.rewriteCorrect + if (isCorrect && isRewrite) 1L else 0L,
            )
            wrongIds.value = if (isCorrect) {
                wrongIds.value.filterNot { it == questionId }
            } else {
                (listOf(questionId) + wrongIds.value).distinct()
            }
            return Result.success(Unit)
        }

        override suspend fun purchasePrize(userId: String, prize: PrizeItem): Result<Unit> {
            val cur = profile.value ?: return Result.failure(IllegalStateException("Missing profile"))
            if (cur.coins < prize.price) return Result.failure(IllegalStateException("Not enough coins"))
            profile.value = cur.copy(coins = cur.coins - prize.price)
            val existing = collection.value.firstOrNull { it.prizeId == prize.id }
            collection.value = if (existing == null) {
                collection.value + CollectableItem(
                    id = prize.id,
                    prizeId = prize.id,
                    userId = userId,
                    title = prize.title,
                    category = prize.category.name,
                    price = prize.price.toLong(),
                    quantity = 1L,
                )
            } else {
                collection.value.map {
                    if (it.prizeId == prize.id) it.copy(quantity = it.quantity + 1L) else it
                }
            }
            return Result.success(Unit)
        }
    }

    @Test
    fun all3050QuestionsHaveValidUniqueStructure() {
        val verbs = VerbQuestionRepository.verbQuestions
        val verbExpr = VerbQuestionRepository.verbExpressionQuestions
        val condChoice = VerbQuestionRepository.conditionalChoiceQuestions
        val condRewrite = VerbQuestionRepository.conditionalRewriteQuestions
        val all = VerbQuestionRepository.allQuestions

        assertEquals(50, verbs.size)
        assertEquals(1000, verbExpr.size)
        assertEquals(1000, verbExpr.map { it.sentenceWithBlank }.toSet().size)
        assertEquals(1000, condChoice.size)
        assertEquals(1000, condRewrite.size)
        assertEquals(3050, all.size)
        assertEquals(3050, all.map { it.id }.toSet().size)

        (verbs + verbExpr + condChoice).forEach { q ->
            assertEquals(QuestionFormat.CHOICE, q.format)
            assertEquals(1, q.coinReward)
            assertEquals("Q${q.id} must have 4 options", 4, q.options.size)
            assertEquals("Q${q.id} options must be unique", 4, q.options.toSet().size)
            assertTrue("Q${q.id} correctIndex out of range", q.correctIndex in 0..3)
            assertTrue("Q${q.id} reason blank", q.reason.isNotBlank())
            assertTrue("Q${q.id} englishMeaning blank", q.englishMeaning.isNotBlank())
            val blankCount = q.sentenceWithBlank.split("________").size - 1
            assertTrue("Q${q.id} missing blank", blankCount >= 1)
            q.options.forEach { opt ->
                assertEquals(
                    "Q${q.id} slot mismatch for '$opt'",
                    blankCount,
                    opt.split(" / ").size,
                )
            }
            assertFalse(q.filledSentence(q.correctOption).contains("________"))
        }

        condRewrite.forEach { q ->
            assertEquals(QuestionFormat.REWRITE, q.format)
            assertEquals(5, q.coinReward)
            assertTrue("Q${q.id} rewritePrompt blank", q.rewritePrompt.isNotBlank())
            assertTrue("Q${q.id} acceptableRewrites empty", q.acceptableRewrites.isNotEmpty())
            assertTrue("Q${q.id} reason blank", q.reason.isNotBlank())
            assertTrue("Q${q.id} englishMeaning blank", q.englishMeaning.isNotBlank())
            val canonical = q.acceptableRewrites.first()
            assertTrue("Q${q.id} must accept canonical lowercased without period", q.isRewriteCorrect(canonical.lowercase().dropLast(1)))
            assertFalse("Q${q.id} must reject unrelated input", q.isRewriteCorrect("completely wrong sentence"))
        }
    }

    @Test
    fun prizeCatalog312ItemsAllHaveUniqueCoinPricesAndValueOrder() {
        val items = PrizeCatalog.items
        assertEquals(312, items.size)
        assertEquals(104, items.count { it.category == PrizeCategory.FOOD })
        assertEquals(104, items.count { it.category == PrizeCategory.MOTORBIKE })
        assertEquals(104, items.count { it.category == PrizeCategory.CAR })

        assertEquals(312, items.map { it.id }.toSet().size)
        assertEquals(312, items.map { it.title }.toSet().size)
        assertEquals(312, items.map { it.price }.toSet().size)
        assertTrue(items.all { it.price > 0 })

        val maxFoodPrice = items.filter { it.category == PrizeCategory.FOOD }.maxOf { it.price }
        val minBikePrice = items.filter { it.category == PrizeCategory.MOTORBIKE }.minOf { it.price }
        val maxBikePrice = items.filter { it.category == PrizeCategory.MOTORBIKE }.maxOf { it.price }
        val minCarPrice = items.filter { it.category == PrizeCategory.CAR }.minOf { it.price }
        assertTrue(maxFoodPrice < minBikePrice)
        assertTrue(maxBikePrice < minCarPrice)

        val normalBike = PrizeCatalog.findById("honda_navi")!!
        val h2r = PrizeCatalog.findById("kawasaki_ninja_h2r")!!
        assertTrue(normalBike.price < h2r.price)

        assertEquals(0.0, UserProfile().ratingOutOfTen, 0.001)
        assertEquals(1.0, UserProfile(totalAttempted = 10, totalCorrect = 0).ratingOutOfTen, 0.001)
        assertEquals(5.5, UserProfile(totalAttempted = 10, totalCorrect = 5).ratingOutOfTen, 0.001)
        assertEquals(10.0, UserProfile(totalAttempted = 10, totalCorrect = 10).ratingOutOfTen, 0.001)
    }

    @Test
    fun everyCollectableHasExplicitUniqueArtworkSpec() {
        val items = PrizeCatalog.items
        assertEquals(312, PrizeArtRegistry.allSpecs.size)
        items.forEach { prize ->
            assertTrue(
                "Missing explicit artwork spec for ${prize.id}",
                PrizeArtRegistry.allSpecs.containsKey(prize.id),
            )
        }
        val uniqueVisualSignatures = items.map { prize ->
            val spec = PrizeArtRegistry.specFor(prize)
            listOf(
                spec.shape,
                spec.primary,
                spec.secondary,
                spec.accent,
                spec.pattern,
                spec.badge,
            )
        }.toSet()
        assertEquals(312, uniqueVisualSignatures.size)
    }

    @Test
    fun fixingExamTracksWrongQuestionsAndClearsFixedOnes() = runTest {
        val store = FakeProgressStore()
        val vm = VerbCraftViewModel(store, "user_1", "Alex")
        val wrongCollectorJob = backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            vm.wrongQuestionIdsState.collect()
        }
        advanceUntilIdle()

        vm.selectSuite(ExamSuite.VERBS_EXPRESSIONS)
        vm.selectLength(ExamLength.TEN)
        vm.startExam()
        assertEquals(10, vm.uiState.value.examQuestions.size)

        val expectedWrongIds = mutableSetOf<Int>()
        while (vm.uiState.value.examPhase == ExamPhase.ACTIVE) {
            val q = vm.uiState.value.currentQuestion!!
            if (vm.uiState.value.currentIndex < 3) {
                val wrongIdx = (q.correctIndex + 1) % 4
                expectedWrongIds += q.id
                vm.submitChoiceAnswer(wrongIdx)
            } else {
                vm.submitChoiceAnswer(q.correctIndex)
            }
            vm.advanceToNextQuestion()
        }
        advanceUntilIdle()

        assertEquals(expectedWrongIds, vm.wrongQuestionIdsState.value.toSet())

        // Start Fixing Exam: must load all 3 wrong questions in one session, question by question
        vm.selectSuite(ExamSuite.FIXING)
        vm.startExam()
        advanceUntilIdle()

        val fixingState = vm.uiState.value
        assertEquals(ExamPhase.ACTIVE, fixingState.examPhase)
        assertTrue(fixingState.isFixingExamSession)
        assertEquals(3, fixingState.examQuestions.size)
        assertEquals(expectedWrongIds, fixingState.examQuestions.map { it.id }.toSet())

        // Fix first 2, still miss the 3rd
        var remainingId = -1
        while (vm.uiState.value.examPhase == ExamPhase.ACTIVE) {
            val q = vm.uiState.value.currentQuestion!!
            if (vm.uiState.value.currentIndex < 2) {
                vm.submitChoiceAnswer(q.correctIndex)
            } else {
                remainingId = q.id
                vm.submitChoiceAnswer((q.correctIndex + 1) % 4)
            }
            vm.advanceToNextQuestion()
        }
        advanceUntilIdle()

        assertEquals(listOf(remainingId), vm.wrongQuestionIdsState.value)
        wrongCollectorJob.cancel()
    }

    @Test
    fun fastingModeTogglesPersistsAndResetsFoodCategorySelection() = runTest {
        val store = FakeProgressStore()
        val vm = VerbCraftViewModel(store, "user_1", "Alex")
        advanceUntilIdle()

        assertFalse(vm.uiState.value.fastingMode)
        vm.selectPrizeCategory(PrizeCategory.FOOD)
        assertEquals(PrizeCategory.FOOD, vm.uiState.value.selectedPrizeCategory)

        vm.toggleFastingMode()
        advanceUntilIdle()

        assertTrue(vm.uiState.value.fastingMode)
        assertEquals(null, vm.uiState.value.selectedPrizeCategory)
        assertTrue(store.profile.value?.fastingMode == true)

        // New ViewModel restores saved fastingMode
        val vmRestored = VerbCraftViewModel(store, "user_1", "Alex")
        advanceUntilIdle()
        assertTrue(vmRestored.uiState.value.fastingMode)
    }

    @Test
    fun viewModelChoiceRewriteCoinsProfileAndPrizeFlow() = runTest {
        assertTrue(VerbCodexData.entries.size >= 95)
        val store = FakeProgressStore()
        val vm = VerbCraftViewModel(store, "user_1", "Alex")
        advanceUntilIdle()

        vm.saveUserProfile("Samira", "Grade 12")
        advanceUntilIdle()
        assertEquals("Samira", store.profile.value?.displayName)
        assertEquals("Grade 12", store.profile.value?.grade)

        // Start a mixed 100-question exam (50 choice + 50 rewrite) -> 50*1 + 50*5 = 300 coins
        vm.selectSuite(ExamSuite.IF_MIXED)
        vm.selectCategory(ExamCategory.ALL)
        vm.selectLength(ExamLength.ALL)
        vm.startExam()

        var state = vm.uiState.value
        assertEquals(ExamPhase.ACTIVE, state.examPhase)
        assertEquals(100, state.examQuestions.size)
        assertEquals(50, state.examQuestions.count { it.format == QuestionFormat.CHOICE })
        assertEquals(50, state.examQuestions.count { it.format == QuestionFormat.REWRITE })

        while (vm.uiState.value.examPhase == ExamPhase.ACTIVE) {
            val q = vm.uiState.value.currentQuestion!!
            assertNotNull(q)
            if (q.format == QuestionFormat.CHOICE) {
                vm.submitChoiceAnswer(q.correctIndex)
            } else {
                vm.updateRewriteInput(q.acceptableRewrites.first())
                vm.submitRewriteAnswer()
            }
            vm.advanceToNextQuestion()
        }
        advanceUntilIdle()

        state = vm.uiState.value
        assertEquals(ExamPhase.RESULTS, state.examPhase)
        assertEquals(100, state.sessionCorrectCount)
        assertEquals(300, state.sessionCoinsEarned)
        assertEquals(300L, store.profile.value?.coins)
        assertEquals(10.0, store.profile.value!!.ratingOutOfTen, 0.001)

        // Buy Porsche 911 GT3 (267 coins)
        val gt3 = PrizeCatalog.findById("porsche_911_gt3")!!
        vm.purchasePrize(gt3)
        advanceUntilIdle()

        assertEquals(33L, store.profile.value?.coins)
        assertEquals(1, store.collection.value.size)
        assertEquals("porsche_911_gt3", store.collection.value.first().prizeId)
    }
}
