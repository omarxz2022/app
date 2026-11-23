package com.example.data

import com.example.model.ExamCategory
import com.example.model.QuestionFormat
import com.example.model.VerbQuestion

internal object ConditionalChoiceGenerator {

    fun generateAll(scenarios: List<ConditionalScenario>): List<VerbQuestion> {
        val result = ArrayList<VerbQuestion>(scenarios.size * 20)
        var nextId = 1001
        for (s in scenarios) {
            result += buildMixedAndInversion(s, startId = nextId)
            nextId += 10
            result += buildSpecialConjunctions(s, startId = nextId)
            nextId += 10
        }
        return result
    }

    private fun buildMixedAndInversion(s: ConditionalScenario, startId: Int): List<VerbQuestion> {
        return listOf(
            // 1. Mixed Present Trait -> Past Result
            rotatedChoice(
                id = startId,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                tags = listOf("Mixed: 2nd + 3rd", s.domain, "if + were"),
                sentenceWithBlank = "Fact: ${cap(s.traitRealPresent)}, which is why ${s.traitRealPast}.\nIf ${s.traitSubj} were ${s.traitAdjPhrase}, ${s.traitSubj} ________ ${s.traitPastTail}.",
                correct = s.traitPastModalVerb,
                distractors = listOf(
                    s.traitPastSimpleTrap,
                    s.traitPastSimpleTrap.replaceFirst("would ", "will "),
                    s.traitPastModalVerb.replaceFirst("would have ", "had "),
                ),
                reason = "This is a Mixed Conditional linking an unreal permanent present quality ('were ${s.traitAdjPhrase}') to a counterfactual past result. Even though the trait is timeless, the specific past event requires 'would have + V3' (${s.traitPastModalVerb}), not 'would + base' (${s.traitPastSimpleTrap}).",
                meaning = "Because ${s.traitRealPresent}, ${s.traitRealPast}.",
            ),
            // 2. Were-inversion (trait -> past)
            rotatedChoice(
                id = startId + 1,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                tags = listOf("Were-Inversion", "Mixed Conditional", s.domain),
                sentenceWithBlank = "Fact: ${cap(s.traitRealPresent)}, so ${s.traitRealPast}.\n________ ${s.traitAdjPhrase}, ${s.traitSubj} ${s.traitPastModalVerb} ${s.traitPastTail}.",
                correct = "Were ${s.traitSubj}",
                distractors = listOf(
                    "Was ${s.traitSubj}",
                    "If ${s.traitSubj} is",
                    "Should ${s.traitSubj} be",
                ),
                reason = "Formal 2nd-conditional inversion fronts subjunctive 'Were' before the subject and drops 'if' ('Were ${s.traitSubj}...'). 'Was' is rejected in formal C2 style; 'If ... is' is Type 1 real present; 'Should ... be' expresses unlikely future, clashing with counterfactual past '${s.traitPastModalVerb}'.",
                meaning = "If ${s.traitSubj} possessed the quality of being ${s.traitAdjPhrase}, ${s.traitPastModalVerb} ${s.traitPastTail} would have happened.",
            ),
            // 3. Were it not for (present counterfactual)
            rotatedChoice(
                id = startId + 2,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                tags = listOf("Were it not for", "2nd Cond Inversion", s.domain),
                sentenceWithBlank = "Reality: ${cap(s.butForNoun)} keeps operating every day.\n________ ${s.butForNoun}, ${s.butForPresentResult}.",
                correct = "Were it not for",
                distractors = listOf(
                    "Had it not been for",
                    "Should it not be for",
                    "Was it not for",
                ),
                reason = "The main clause ('${s.butForPresentResult}') describes a present/general counterfactual consequence, requiring present-time inversion 'Were it not for + noun phrase'. 'Had it not been for' belongs to past 3rd conditionals.",
                meaning = "If ${s.butForNoun} did not exist today, ${s.butForPresentResult}.",
            ),
            // 4. Had it not been for (past counterfactual)
            rotatedChoice(
                id = startId + 3,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                tags = listOf("Had it not been for", "3rd Cond Inversion", s.domain),
                sentenceWithBlank = "During that past crisis, ________ ${s.butForNoun}, ${s.butForPastResult}.",
                correct = "had it not been for",
                distractors = listOf(
                    "were it not for",
                    "if it wasn't for",
                    "should it not have been for",
                ),
                reason = "Because '${s.butForPastResult}' refers to an averted past disaster ('would have V3'), only inverted 3rd conditional 'had it not been for' matches the past timeframe.",
                meaning = "Without ${s.butForNoun} during that past incident, ${s.butForPastResult}.",
            ),
            // 5. But for + NP
            rotatedChoice(
                id = startId + 4,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                tags = listOf("But for", "Prepositional Cond", s.domain),
                sentenceWithBlank = "________ ${s.butForNoun}, ${s.butForPastResult}.",
                correct = "But for",
                distractors = listOf(
                    "Unless",
                    "Provided",
                    "In case",
                ),
                reason = "'But for + noun phrase' means 'if it had not been for...'. 'Unless', 'Provided', and 'In case' are subordinating conjunctions that require a full subject-verb clause, never a bare noun phrase.",
                meaning = "If ${s.butForNoun} had not intervened, ${s.butForPastResult}.",
            ),
            // 6. Formal Inversion Contraction Trap (*Hadn't it been)
            rotatedChoice(
                id = startId + 5,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                tags = listOf("No-Contraction Rule", "Had Inversion", s.domain),
                sentenceWithBlank = "Select the strictly grammatical formal opening:\n________ ${s.butForNoun}, ${s.butForPastResult}.",
                correct = "Had it not been for",
                distractors = listOf(
                    "Hadn't it been for",
                    "Were it not been for",
                    "Did it not be for",
                ),
                reason = "In formal inverted conditionals, 'not' must follow the subject ('Had it not been for'), NEVER contract onto the fronted auxiliary (*'Hadn't it been for'). 'Were ... been' and 'Did ... be' mix auxiliaries illegally.",
                meaning = "Were it not for the past presence of ${s.butForNoun}, ${s.butForPastResult}.",
            ),
            // 7. Mixed Past -> Present Consequence
            rotatedChoice(
                id = startId + 6,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                tags = listOf("Mixed: 3rd + 2nd", "Present Result", s.domain),
                sentenceWithBlank = "Reality: ${cap(s.mixedRealPastCause)}, so ${s.mixedRealPresentNow}.\nIf the earlier choice had been different, ${s.mixedSubj} ________ ${s.mixedPresentTail}.",
                correct = s.mixedPresentModalVerb,
                distractors = listOf(
                    s.mixedPresentPastModalVerb,
                    s.mixedPresentModalVerb.replaceFirst("would ", "will "),
                    s.mixedPresentPastModalVerb.replaceFirst("would ", "might "),
                ),
                reason = "This Mixed Conditional (Type 3 + Type 2) pairs an unreal past cause ('had been different') with an ongoing present consequence marked by 'now/today'. Therefore the main clause needs 'would + base verb' (${s.mixedPresentModalVerb}), not 'would have + V3'.",
                meaning = "Because ${s.mixedRealPastCause}, ${s.mixedRealPresentNow}.",
            ),
            // 8. Mixed Past -> Present Had-Inversion
            rotatedChoice(
                id = startId + 7,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                tags = listOf("Had-Inversion", "Mixed: 3rd + 2nd", s.domain),
                sentenceWithBlank = "Since ${s.mixedRealPastCause}, ${s.mixedRealPresentNow}.\n________ things gone differently back then, ${s.mixedSubj} ${s.mixedPresentModalVerb} ${s.mixedPresentTail}.",
                correct = "Had",
                distractors = listOf(
                    "Were",
                    "Should",
                    "If",
                ),
                reason = "The past participle 'gone' requires inverted past perfect 'Had things gone differently...'. 'If things gone' omits the auxiliary 'had'; 'Were' and 'Should' cannot govern past participle 'gone' here.",
                meaning = "If events had turned out otherwise in the past, ${s.mixedSubj} ${s.mixedPresentModalVerb} ${s.mixedPresentTail}.",
            ),
            // 9. 3rd Conditional Passive Had-Inversion
            rotatedChoice(
                id = startId + 8,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                tags = listOf("3rd Conditional", "Passive Inversion", s.domain),
                sentenceWithBlank = "Because ${s.thirdRealCause}, ${s.thirdRealEffect}.\n________ that mistake been avoided, ${s.thirdGoodOutcome}.",
                correct = "Had",
                distractors = listOf(
                    "Were",
                    "Did",
                    "Would",
                ),
                reason = "Passive 3rd-conditional inversion fronts 'Had' before the subject and 'been + V3': 'Had that mistake been avoided'. 'Were' would clash with past participle 'been', and 'Would' cannot appear in a standard conditional protasis.",
                meaning = "If someone had prevented that mistake in the past, ${s.thirdGoodOutcome}.",
            ),
            // 10. 3rd Conditional Result Clause
            rotatedChoice(
                id = startId + 9,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                tags = listOf("3rd Conditional", "Result Clause", s.domain),
                sentenceWithBlank = "Because ${s.thirdRealCause}, ${s.thirdRealEffect}.\nIf that oversight had been prevented, ________.",
                correct = s.thirdGoodOutcome,
                distractors = buildThirdOutcomeDistractors(s.thirdGoodOutcome),
                reason = "Type 3 counterfactual past condition ('had been prevented') demands 'would/could + (never) + have + V3' in the main clause. Using simple 'would + base', 'will have', or 'had' breaks past counterfactual sequence.",
                meaning = "Had the mistake not occurred, ${s.thirdGoodOutcome}.",
            ),
        )
    }

    private fun buildSpecialConjunctions(s: ConditionalScenario, startId: Int): List<VerbQuestion> {
        return listOf(
            // 11. Zero Conditional
            rotatedChoice(
                id = startId,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                tags = listOf("Zero Conditional", "Invariant Law", s.domain),
                sentenceWithBlank = "In ${s.domain.lowercase()}, the mechanism is deterministic: ________ ${s.zeroIf}, ${s.zeroThen}.",
                correct = "whenever",
                distractors = listOf(
                    "unless",
                    "but for",
                    "had",
                ),
                reason = "Zero conditionals describe invariant cause-and-effect laws in present simple. 'Whenever' (meaning 'every time if') matches the positive causal law; 'unless' reverses polarity, 'but for' needs a noun phrase, and 'had' implies past inversion.",
                meaning = "Every time ${s.zeroIf}, ${s.zeroThen}.",
            ),
            // 12. Even if (Concessive Conditional)
            rotatedChoice(
                id = startId + 1,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                tags = listOf("Even if", "Concessive", s.domain),
                sentenceWithBlank = "________ ${s.evenIfClause}, ${s.evenIfResult}.",
                correct = "Even if",
                distractors = listOf(
                    "Provided that",
                    "As long as",
                    "On condition that",
                ),
                reason = "The adverb 'still' in '${s.evenIfResult}' signals concession—the result holds despite an extreme hypothetical obstacle. Only 'Even if' conveys 'whether or not'; 'Provided that', 'As long as', and 'On condition that' all state favorable conditions.",
                meaning = "The outcome (${s.evenIfResult}) remains unchanged no matter whether ${s.evenIfClause}.",
            ),
            // 13. In case (Precaution)
            rotatedChoice(
                id = startId + 2,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                tags = listOf("In case", "Precaution", s.domain),
                sentenceWithBlank = "Act ahead of trouble: ${s.precautionAction} ________ ${s.precautionHazard}.",
                correct = "in case",
                distractors = listOf(
                    "provided that",
                    "unless",
                    "as long as",
                ),
                reason = "'In case' introduces a precaution taken NOW to prepare for a potential future hazard ('${s.precautionHazard}'). 'Provided that' and 'as long as' mean 'only if' (making nonsense of carrying backups), and 'unless' reverses the warning.",
                meaning = "${s.precautionAction} beforehand as protection against the possibility that ${s.precautionHazard}.",
            ),
            // 14. Otherwise (Implicit Negative Condition)
            rotatedChoice(
                id = startId + 3,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                tags = listOf("Otherwise", "Implicit Condition", s.domain),
                sentenceWithBlank = "Ensure you follow this safeguard (${s.precautionAction.replaceFirstChar { it.lowercase() }}); ________, ${s.firstUnlessOutcome}.",
                correct = "otherwise",
                distractors = listOf(
                    "provided that",
                    "unless",
                    "in case",
                ),
                reason = "Following a semicolon, the conjunctive adverb 'otherwise' stands in for the entire negative conditional clause ('if you do not do that'). 'Unless', 'provided that', and 'in case' are subordinating conjunctions that cannot stand detached before a comma and main clause.",
                meaning = "If that safeguard is not maintained, ${s.firstUnlessOutcome}.",
            ),
            // 15. Supposing (2nd Conditional Speculation)
            rotatedChoice(
                id = startId + 4,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                tags = listOf("Supposing", "2nd Conditional", s.domain),
                sentenceWithBlank = "________ ${s.evenIfClause}, would the real bottleneck vanish? No—${s.evenIfResult}.",
                correct = "Supposing",
                distractors = listOf(
                    "Unless",
                    "But for",
                    "In case of",
                ),
                reason = "'Supposing (that) + past simple' sets up a hypothetical Type 2 thought experiment ('What if...?'). 'But for' and 'In case of' require noun phrases, and 'Unless' contradicts the question's supposition.",
                meaning = "Imagine for argument's sake that ${s.evenIfClause}; even then, ${s.evenIfResult}.",
            ),
            // 16. 1st Conditional Formal Should-Inversion
            rotatedChoice(
                id = startId + 5,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                tags = listOf("Should-Inversion", "1st Conditional", s.domain),
                sentenceWithBlank = "________ it turn out that ${s.precautionHazard}, ${s.firstFutureOutcome}.",
                correct = "Should",
                distractors = listOf(
                    "Were",
                    "Would",
                    "Did",
                ),
                reason = "Formal 1st-conditional inversion uses 'Should + subject + bare infinitive' ('Should it turn out...'). 'Were' would require 'to' ('Were it to turn out'); 'Did' expresses past time clashing with the future main clause; 'Would' cannot invert a conditional protasis.",
                meaning = "If it happens that ${s.precautionHazard}, ${s.firstFutureOutcome}.",
            ),
            // 17. 1st Conditional Present Simple vs *If + will error
            rotatedChoice(
                id = startId + 6,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                tags = listOf("1st Conditional", "No-Will-in-If Rule", s.domain),
                sentenceWithBlank = "If the contingency plan ________ to activate promptly, ${s.firstFutureOutcome}.",
                correct = "fails",
                distractors = listOf(
                    "will fail",
                    "would fail",
                    "had failed",
                ),
                reason = "In neutral future predictions (1st conditional), the 'if'-clause takes Present Simple ('fails'), never future 'will fail'. 'Would fail' (2nd) and 'had failed' (3rd) clash with the real future main clause.",
                meaning = "Whenever the backup fails to engage in time, ${s.firstFutureOutcome}.",
            ),
            // 18. 2nd Conditional Result Clause
            rotatedChoice(
                id = startId + 7,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                tags = listOf("2nd Conditional", "Unreal Present/Future", s.domain),
                sentenceWithBlank = "If ${s.secondSubj} ${s.secondVerbPast} current standards aggressively right now, ________.",
                correct = s.secondFutureOutcome,
                distractors = buildSecondOutcomeDistractors(s.secondFutureOutcome),
                reason = "Past Simple in the protasis ('${s.secondVerbPast}') signals a Type 2 hypothetical present/future scenario, requiring 'would/could + bare infinitive' in the result clause—not 'will', 'would have', or simple past.",
                meaning = "If ${s.secondSubj} made that bold change now, ${s.secondFutureOutcome}.",
            ),
            // 19. Unless vs Provided that / As long as (Negative Polarity)
            rotatedChoice(
                id = startId + 8,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                tags = listOf("Unless", "Negative Polarity", s.domain),
                sentenceWithBlank = "________ strict compliance holds throughout this phase, ${s.firstUnlessOutcome}.",
                correct = "Unless",
                distractors = listOf(
                    "Provided that",
                    "As long as",
                    "On condition that",
                ),
                reason = "Because '${s.firstUnlessOutcome}' is an adverse outcome, it occurs IF strict compliance does NOT hold ('Unless' = 'if ... not'). 'Provided that', 'As long as', and 'On condition that' mean 'only if' and would reverse the logic.",
                meaning = "If strict compliance fails to hold, ${s.firstUnlessOutcome}.",
            ),
            // 20. Were + Subject + to-infinitive (2nd Conditional Formal Unlikely Future)
            rotatedChoice(
                id = startId + 9,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                tags = listOf("Were ... to", "C2 Inversion", s.domain),
                sentenceWithBlank = "________ ${s.secondSubj} to ${s.secondVerbBase} the policy tomorrow, ${s.secondFutureOutcome}.",
                correct = "Were",
                distractors = listOf(
                    "Should",
                    "Had",
                    "Did",
                ),
                reason = "The infinitive marker 'to ${s.secondVerbBase}' proves this is the 'Were + subject + to-infinitive' construction for remote future hypotheses. 'Should' and 'Did' take bare infinitives (no 'to'), while 'Had' requires a past participle.",
                meaning = "In the unlikely event that ${s.secondSubj} enacted that policy tomorrow, ${s.secondFutureOutcome}.",
            ),
        )
    }

    private fun rotatedChoice(
        id: Int,
        category: ExamCategory,
        tags: List<String>,
        sentenceWithBlank: String,
        correct: String,
        distractors: List<String>,
        reason: String,
        meaning: String,
    ): VerbQuestion {
        val uniqueDistractors = distractors.filter { it != correct }.distinct()
        val padded = (uniqueDistractors + listOf("had otherwise", "would otherwise", "unless otherwise"))
            .distinct()
            .filter { it != correct }
            .take(3)
        val correctIndex = id % 4
        val options = MutableList(4) { "" }
        var dIdx = 0
        for (i in 0..3) {
            if (i == correctIndex) {
                options[i] = correct
            } else {
                options[i] = padded[dIdx++]
            }
        }
        return VerbQuestion(
            id = id,
            format = QuestionFormat.CHOICE,
            category = category,
            verbsInvolved = tags,
            sentenceWithBlank = sentenceWithBlank,
            options = options,
            correctIndex = correctIndex,
            reason = reason,
            englishMeaning = meaning,
        )
    }

    private fun buildThirdOutcomeDistractors(correct: String): List<String> {
        return when {
            correct.contains("would never have ") -> listOf(
                correct.replaceFirst("would never have ", "would never "),
                correct.replaceFirst("would never have ", "will never have "),
                correct.replaceFirst("would never have ", "had never "),
            )
            correct.contains("could never have ") -> listOf(
                correct.replaceFirst("could never have ", "could never "),
                correct.replaceFirst("could never have ", "can never have "),
                correct.replaceFirst("could never have ", "had never "),
            )
            correct.contains("would not have ") -> listOf(
                correct.replaceFirst("would not have ", "would not "),
                correct.replaceFirst("would not have ", "will not have "),
                correct.replaceFirst("would not have ", "had not "),
            )
            correct.contains("would have ") -> listOf(
                correct.replaceFirst("would have ", "would "),
                correct.replaceFirst("would have ", "will have "),
                correct.replaceFirst("would have ", "had "),
            )
            else -> listOf(
                "it would fail to hold",
                "it will have failed",
                "it had already held",
            )
        }
    }

    private fun buildSecondOutcomeDistractors(correct: String): List<String> {
        return when {
            correct.contains(" would ") -> listOf(
                correct.replaceFirst(" would ", " will "),
                correct.replaceFirst(" would ", " would have "),
                correct.replaceFirst(" would ", " had "),
            )
            correct.contains(" could ") -> listOf(
                correct.replaceFirst(" could ", " can "),
                correct.replaceFirst(" could ", " could have "),
                correct.replaceFirst(" could ", " had "),
            )
            else -> listOf(
                "results will shift immediately",
                "results would have shifted earlier",
                "results had shifted yesterday",
            )
        }
    }

    private fun cap(text: String): String =
        text.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}
