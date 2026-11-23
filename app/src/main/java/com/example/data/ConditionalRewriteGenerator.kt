package com.example.data

import com.example.model.ExamCategory
import com.example.model.QuestionFormat
import com.example.model.VerbQuestion

internal object ConditionalRewriteGenerator {

    fun generateAll(scenarios: List<ConditionalScenario>): List<VerbQuestion> {
        val result = ArrayList<VerbQuestion>(scenarios.size * 20)
        var nextId = 2001
        for (s in scenarios) {
            result += buildMixedAndInversionRewrites(s, startId = nextId)
            nextId += 10
            result += buildSpecialConjunctionRewrites(s, startId = nextId)
            nextId += 10
        }
        return result
    }

    private fun buildMixedAndInversionRewrites(s: ConditionalScenario, startId: Int): List<VerbQuestion> {
        return listOf(
            // 1. If it had not been for -> Had it not been for (Formal 3rd Inversion)
            VerbQuestion(
                id = startId,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                verbsInvolved = listOf("Had-Inversion", "3rd Conditional", s.domain),
                sentenceWithBlank = "If it had not been for ${s.butForNoun}, ${s.butForPastResult}.",
                rewritePrompt = "Rewrite using formal inversion starting with 'Had' (omit 'if'; do NOT contract 'not'):",
                rewriteStarterHint = "Had it not been for...",
                acceptableRewrites = listOf(
                    "Had it not been for ${s.butForNoun}, ${s.butForPastResult}.",
                ),
                reason = "Formal 3rd-conditional inversion fronts 'Had', drops 'if', and keeps 'not' uncontracted after the subject: 'Had it not been for...'. Writing *'Hadn't it been for'* is ungrammatical in formal inversion.",
                englishMeaning = "Without ${s.butForNoun} in that past situation, ${s.butForPastResult}.",
            ),
            // 2. If it had not been for -> But for (Concise Nominalization)
            VerbQuestion(
                id = startId + 1,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                verbsInvolved = listOf("But for", "3rd Counterfactual", s.domain),
                sentenceWithBlank = "If it had not been for ${s.butForNoun}, ${s.butForPastResult}.",
                rewritePrompt = "Rewrite concisely starting with 'But for' (keep the same past result clause):",
                rewriteStarterHint = "But for...",
                acceptableRewrites = listOf(
                    "But for ${s.butForNoun}, ${s.butForPastResult}.",
                ),
                reason = "The prepositional phrase 'But for + noun phrase' replaces the entire conditional clause 'If it had not been for + noun phrase' in formal academic prose.",
                englishMeaning = "Had ${s.butForNoun} been absent, ${s.butForPastResult}.",
            ),
            // 3. If it were not for -> Were it not for (Formal 2nd Inversion)
            VerbQuestion(
                id = startId + 2,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                verbsInvolved = listOf("Were-Inversion", "2nd Conditional", s.domain),
                sentenceWithBlank = "If it were not for ${s.butForNoun}, ${s.butForPresentResult}.",
                rewritePrompt = "Rewrite using formal 'Were'-inversion (omit 'if' and avoid contractions):",
                rewriteStarterHint = "Were it not for...",
                acceptableRewrites = listOf(
                    "Were it not for ${s.butForNoun}, ${s.butForPresentResult}.",
                ),
                reason = "For present/general counterfactuals, formal inversion drops 'if', places 'Were' before 'it', and keeps 'not' separate ('Were it not for...').",
                englishMeaning = "If ${s.butForNoun} did not exist right now, ${s.butForPresentResult}.",
            ),
            // 4. Without -> If it were not for (Full Clause Expansion)
            VerbQuestion(
                id = startId + 3,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                verbsInvolved = listOf("If it were not for", "2nd Conditional", s.domain),
                sentenceWithBlank = "Without ${s.butForNoun}, ${s.butForPresentResult}.",
                rewritePrompt = "Expand 'Without' into a full formal clause starting with 'If it were not for':",
                rewriteStarterHint = "If it were not for...",
                acceptableRewrites = listOf(
                    "If it were not for ${s.butForNoun}, ${s.butForPresentResult}.",
                    "If it weren't for ${s.butForNoun}, ${s.butForPresentResult}.",
                ),
                reason = "A prepositional 'Without + NP' counterfactual expands to 'If it were not for + NP' when paired with a Type 2 present conditional result ('${s.butForPresentResult}').",
                englishMeaning = "${cap(s.butForPresentResult)} in the absence of ${s.butForNoun}.",
            ),
            // 5. Mixed Trait -> Were-Inversion
            VerbQuestion(
                id = startId + 4,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                verbsInvolved = listOf("Mixed: 2nd+3rd", "Were-Inversion", s.domain),
                sentenceWithBlank = "If ${s.traitSubj} were ${s.traitAdjPhrase}, ${s.traitSubj} ${s.traitPastModalVerb} ${s.traitPastTail}.",
                rewritePrompt = "Rewrite using formal 'Were'-inversion (start with 'Were', omit 'if'):",
                rewriteStarterHint = "Were ${s.traitSubj}...",
                acceptableRewrites = listOf(
                    "Were ${s.traitSubj} ${s.traitAdjPhrase}, ${s.traitSubj} ${s.traitPastModalVerb} ${s.traitPastTail}.",
                ),
                reason = "To invert 'If + subject + were + adjective', move 'Were' to the very front and omit 'if'. The past modal ('${s.traitPastModalVerb}') stays unchanged because the result happened in the past.",
                englishMeaning = "Because ${s.traitRealPresent}, ${s.traitRealPast}.",
            ),
            // 6. Inverted Mixed Trait -> Standard If ... were
            VerbQuestion(
                id = startId + 5,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                verbsInvolved = listOf("Mixed: 2nd+3rd", "De-Inversion", s.domain),
                sentenceWithBlank = "Were ${s.traitSubj} ${s.traitAdjPhrase}, ${s.traitSubj} ${s.traitPastModalVerb} ${s.traitPastTail}.",
                rewritePrompt = "Rewrite using a standard 'If'-clause instead of 'Were'-inversion:",
                rewriteStarterHint = "If ${s.traitSubj} were...",
                acceptableRewrites = listOf(
                    "If ${s.traitSubj} were ${s.traitAdjPhrase}, ${s.traitSubj} ${s.traitPastModalVerb} ${s.traitPastTail}.",
                ),
                reason = "Restoring 'If' returns subjunctive 'were' directly after the subject ('If ${s.traitSubj} were ${s.traitAdjPhrase}'). Keep '${s.traitPastModalVerb}' in the past result clause.",
                englishMeaning = "In reality, ${s.traitRealPresent}, so ${s.traitRealPast}.",
            ),
            // 7. Mixed Past->Present Fronted Result -> Had-Inversion
            VerbQuestion(
                id = startId + 6,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                verbsInvolved = listOf("Mixed: 3rd+2nd", "Had-Inversion", s.domain),
                sentenceWithBlank = "${cap(s.mixedSubj)} ${s.mixedPresentModalVerb} ${s.mixedPresentTail} if past decisions had gone differently.",
                rewritePrompt = "Move the condition to the front using formal 'Had'-inversion (omit 'if'):",
                rewriteStarterHint = "Had past decisions gone...",
                acceptableRewrites = listOf(
                    "Had past decisions gone differently, ${s.mixedSubj} ${s.mixedPresentModalVerb} ${s.mixedPresentTail}.",
                ),
                reason = "Fronting 'Had' before 'past decisions' creates a C2 mixed conditional where an inverted past perfect protasis produces a present result ('${s.mixedPresentModalVerb} ${s.mixedPresentTail}').",
                englishMeaning = "Since ${s.mixedRealPastCause}, ${s.mixedRealPresentNow}.",
            ),
            // 8. Fronted 3rd Conditional Result -> Formal Had-Inversion
            VerbQuestion(
                id = startId + 7,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                verbsInvolved = listOf("3rd Conditional", "Had-Inversion", s.domain),
                sentenceWithBlank = "${cap(s.thirdGoodOutcome)} if that oversight had been prevented.",
                rewritePrompt = "Rewrite starting with formal 'Had'-inversion (omit 'if' and put the condition first):",
                rewriteStarterHint = "Had that oversight...",
                acceptableRewrites = listOf(
                    "Had that oversight been prevented, ${s.thirdGoodOutcome}.",
                ),
                reason = "Passive past-perfect inversion moves 'Had' ahead of 'that oversight been prevented' and drops 'if', followed by a comma before the past result clause.",
                englishMeaning = "Because ${s.thirdRealCause}, ${s.thirdRealEffect}.",
            ),
            // 9. Were it not for -> But for (2nd Conditional Concise)
            VerbQuestion(
                id = startId + 8,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                verbsInvolved = listOf("But for", "2nd Conditional", s.domain),
                sentenceWithBlank = "Were it not for ${s.butForNoun}, ${s.butForPresentResult}.",
                rewritePrompt = "Rewrite the opening using the two-word preposition 'But for':",
                rewriteStarterHint = "But for...",
                acceptableRewrites = listOf(
                    "But for ${s.butForNoun}, ${s.butForPresentResult}.",
                ),
                reason = "'But for + NP' works identically with Type 2 present counterfactuals ('Were it not for') as well as Type 3 past counterfactuals ('Had it not been for').",
                englishMeaning = "Without ${s.butForNoun} right now, ${s.butForPresentResult}.",
            ),
            // 10. 3rd Conditional -> Emphatic 'If only'
            VerbQuestion(
                id = startId + 9,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_MIXED_AND_INVERSION,
                verbsInvolved = listOf("If only", "3rd Counterfactual", s.domain),
                sentenceWithBlank = "If that critical mistake had been avoided, ${s.thirdGoodOutcome}.",
                rewritePrompt = "Rewrite the condition as an emphatic counterfactual regret using 'If only':",
                rewriteStarterHint = "If only that critical mistake...",
                acceptableRewrites = listOf(
                    "If only that critical mistake had been avoided, ${s.thirdGoodOutcome}.",
                ),
                reason = "'If only + past perfect' intensifies a 3rd conditional, expressing strong regret over an unchangeable past failure while keeping the Type 3 main clause.",
                englishMeaning = "Had that mistake only been prevented, ${s.thirdGoodOutcome}.",
            ),
        )
    }

    private fun buildSpecialConjunctionRewrites(s: ConditionalScenario, startId: Int): List<VerbQuestion> {
        return listOf(
            // 11. Zero Conditional: Whenever -> If
            VerbQuestion(
                id = startId,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                verbsInvolved = listOf("Zero Conditional", "If / Whenever", s.domain),
                sentenceWithBlank = "Whenever ${s.zeroIf}, ${s.zeroThen}.",
                rewritePrompt = "Rewrite this invariant Zero Conditional using 'If' instead of 'Whenever':",
                rewriteStarterHint = "If ${s.zeroIf}...",
                acceptableRewrites = listOf(
                    "If ${s.zeroIf}, ${s.zeroThen}.",
                ),
                reason = "Zero conditionals use Present Simple in both clauses to state laws that always hold true; 'If' and 'Whenever' are interchangeable here.",
                englishMeaning = "In every case where ${s.zeroIf}, ${s.zeroThen}.",
            ),
            // 12. Zero Conditional: If -> Provided that
            VerbQuestion(
                id = startId + 1,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                verbsInvolved = listOf("Zero Conditional", "Provided that", s.domain),
                sentenceWithBlank = "If ${s.zeroIf}, ${s.zeroThen}.",
                rewritePrompt = "Rewrite substituting 'Provided that' for 'If' at the start:",
                rewriteStarterHint = "Provided that...",
                acceptableRewrites = listOf(
                    "Provided that ${s.zeroIf}, ${s.zeroThen}.",
                    "Provided ${s.zeroIf}, ${s.zeroThen}.",
                ),
                reason = "'Provided (that)' functions as a formal conditional conjunction requiring a finite clause, keeping Present Simple in both clauses for general truths.",
                englishMeaning = "On the condition that ${s.zeroIf}, ${s.zeroThen}.",
            ),
            // 13. Zero Conditional: Clause Reversal
            VerbQuestion(
                id = startId + 2,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                verbsInvolved = listOf("Zero Conditional", "Clause Order", s.domain),
                sentenceWithBlank = "If ${s.zeroIf}, ${s.zeroThen}.",
                rewritePrompt = "Rewrite starting with the result clause ('${cap(s.zeroThen)}') and moving 'if' to the middle:",
                rewriteStarterHint = "${cap(s.zeroThen)} if...",
                acceptableRewrites = listOf(
                    "${cap(s.zeroThen)} if ${s.zeroIf}.",
                ),
                reason = "When the main result clause precedes the 'if'-clause, drop the comma between clauses while keeping Present Simple tense intact.",
                englishMeaning = "This consequence occurs every time ${s.zeroIf}.",
            ),
            // 14. 1st Conditional: Should-Inversion -> If
            VerbQuestion(
                id = startId + 3,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                verbsInvolved = listOf("1st Conditional", "Should -> If", s.domain),
                sentenceWithBlank = "Should it turn out that ${s.precautionHazard}, ${s.firstFutureOutcome}.",
                rewritePrompt = "Rewrite removing the 'Should'-inversion and using standard 'If it turns out that':",
                rewriteStarterHint = "If it turns out that...",
                acceptableRewrites = listOf(
                    "If it turns out that ${s.precautionHazard}, ${s.firstFutureOutcome}.",
                ),
                reason = "De-inverting 'Should it turn out...' restores 'If' and conjugates the verb to 3rd-person Present Simple ('turns out')—never 'will turn out'.",
                englishMeaning = "If ${s.precautionHazard} comes to pass, ${s.firstFutureOutcome}.",
            ),
            // 15. 1st Conditional: Standard If -> Formal Should-Inversion
            VerbQuestion(
                id = startId + 4,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                verbsInvolved = listOf("1st Conditional", "Should-Inversion", s.domain),
                sentenceWithBlank = "If it happens that ${s.precautionHazard}, ${s.firstFutureOutcome}.",
                rewritePrompt = "Rewrite using formal 1st-conditional inversion starting with 'Should it happen that':",
                rewriteStarterHint = "Should it happen that...",
                acceptableRewrites = listOf(
                    "Should it happen that ${s.precautionHazard}, ${s.firstFutureOutcome}.",
                ),
                reason = "Formal Type 1 inversion deletes 'If', fronts modal 'Should', and changes 'happens' back to the bare infinitive 'happen' ('Should it happen that...').",
                englishMeaning = "In the event that ${s.precautionHazard}, ${s.firstFutureOutcome}.",
            ),
            // 16. Precaution: Because might -> In case
            VerbQuestion(
                id = startId + 5,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                verbsInvolved = listOf("In case", "Precaution", s.domain),
                sentenceWithBlank = "Take precaution: ${s.precautionAction} because ${s.precautionHazard} might happen.",
                rewritePrompt = "Combine into one crisp preventive instruction using 'in case' (drop 'because' and 'might happen'):",
                rewriteStarterHint = "${s.precautionAction} in case...",
                acceptableRewrites = listOf(
                    "${s.precautionAction} in case ${s.precautionHazard}.",
                ),
                reason = "'In case + Present Simple' replaces 'because ... might happen' to express proactive preparation against a future risk.",
                englishMeaning = "Prepare ahead by carrying out this step (${s.precautionAction}) for fear that ${s.precautionHazard}.",
            ),
            // 17. Precaution: Fronted In case clause
            VerbQuestion(
                id = startId + 6,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                verbsInvolved = listOf("In case", "Fronted Clause", s.domain),
                sentenceWithBlank = "${s.precautionAction} in case ${s.precautionHazard}.",
                rewritePrompt = "Rewrite putting the 'In case' clause at the very beginning:",
                rewriteStarterHint = "In case ${s.precautionHazard}, ...",
                acceptableRewrites = listOf(
                    "In case ${s.precautionHazard}, ${s.precautionAction.replaceFirstChar { it.lowercase() }}.",
                ),
                reason = "Preposing 'In case ${s.precautionHazard},' places a comma before the imperative instruction while retaining Present Simple inside the 'in case' clause.",
                englishMeaning = "As a safeguard if ${s.precautionHazard}, ${s.precautionAction.replaceFirstChar { it.lowercase() }}.",
            ),
            // 18. Concessive Even if: Clause Reversal
            VerbQuestion(
                id = startId + 7,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                verbsInvolved = listOf("Even if", "Concessive Clause", s.domain),
                sentenceWithBlank = "Even if ${s.evenIfClause}, ${s.evenIfResult}.",
                rewritePrompt = "Rewrite starting with '${cap(s.evenIfResult)}' and placing 'even if' second:",
                rewriteStarterHint = "${cap(s.evenIfResult)} even if...",
                acceptableRewrites = listOf(
                    "${cap(s.evenIfResult)} even if ${s.evenIfClause}.",
                ),
                reason = "When the main result clause opens the sentence before 'even if', omit the comma and preserve the Type 2 past-tense counterfactual verb in the subordinate clause.",
                englishMeaning = "${cap(s.evenIfResult)} regardless of whether ${s.evenIfClause}.",
            ),
            // 19. 2nd Conditional: Suppose -> Supposing that
            VerbQuestion(
                id = startId + 8,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                verbsInvolved = listOf("Supposing that", "2nd Conditional", s.domain),
                sentenceWithBlank = "Suppose ${s.evenIfClause}; ${s.evenIfResult}.",
                rewritePrompt = "Rewrite as a single comma-joined sentence starting with 'Supposing that':",
                rewriteStarterHint = "Supposing that ${s.evenIfClause}, ...",
                acceptableRewrites = listOf(
                    "Supposing that ${s.evenIfClause}, ${s.evenIfResult}.",
                    "Supposing ${s.evenIfClause}, ${s.evenIfResult}.",
                ),
                reason = "'Supposing (that)' is a participle conjunction introducing a hypothetical Type 2 condition and requires a comma before the result clause when fronted.",
                englishMeaning = "Assuming for argument that ${s.evenIfClause}, ${s.evenIfResult}.",
            ),
            // 20. 2nd Conditional Prepositional Fronted Result -> Without
            VerbQuestion(
                id = startId + 9,
                format = QuestionFormat.REWRITE,
                category = ExamCategory.IF_SPECIAL_CONJUNCTIONS,
                verbsInvolved = listOf("Without", "2nd Conditional", s.domain),
                sentenceWithBlank = "If it were not for ${s.butForNoun}, ${s.butForPresentResult}.",
                rewritePrompt = "Rewrite starting with '${cap(s.butForPresentResult)}' and replacing the 'if'-clause with 'without':",
                rewriteStarterHint = "${cap(s.butForPresentResult)} without...",
                acceptableRewrites = listOf(
                    "${cap(s.butForPresentResult)} without ${s.butForNoun}.",
                ),
                reason = "When moving the counterfactual result to the front, 'without + noun phrase' concisely captures 'if it were not for + noun phrase' with no comma needed.",
                englishMeaning = "If ${s.butForNoun} were absent now, ${s.butForPresentResult}.",
            ),
        )
    }

    private fun cap(text: String): String =
        text.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}
