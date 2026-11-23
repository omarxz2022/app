package com.example.data

import com.example.model.ExamCategory
import com.example.model.QuestionFormat
import com.example.model.VerbQuestion

internal object VerbExpressionGenerator {

    fun generateAll(scenarios: List<VerbExprScenario>): List<VerbQuestion> {
        val result = ArrayList<VerbQuestion>(scenarios.size * 20)
        var nextId = 3001
        for (s in scenarios) {
            result += buildPrepToExpressions(s, startId = nextId)
            nextId += 5
            result += buildFixedGerundExpressions(s, startId = nextId)
            nextId += 5
            result += buildInfinitiveAndPreference(s, startId = nextId)
            nextId += 5
            result += buildMeaningAndCausative(s, startId = nextId)
            nextId += 5
        }
        return result
    }

    private fun buildPrepToExpressions(s: VerbExprScenario, startId: Int): List<VerbQuestion> {
        return listOf(
            // 1. look forward to + V-ing
            rotatedChoice(
                id = startId,
                category = ExamCategory.EXPRESSIONS_PREP_TO,
                tags = listOf("look forward to + V-ing", s.domain),
                sentenceWithBlank = "${s.contextClause} ${s.actor} is genuinely looking forward ________ without any further delay.",
                correct = "to ${s.actIng}",
                distractors = listOf(
                    "to ${s.actInf}",
                    "for ${s.actIng}",
                    s.actInf,
                ),
                reason = "In 'look forward to', 'to' is a true preposition, not an infinitive marker. Prepositions govern gerunds ('to ${s.actIng}'), never bare verbs (*to ${s.actInf}*).",
                meaning = "${cap(s.actor)} is eagerly anticipating ${s.actIng} right away.",
            ),
            // 2. object to + being V3
            rotatedChoice(
                id = startId + 1,
                category = ExamCategory.EXPRESSIONS_PREP_TO,
                tags = listOf("object to + being V3", s.domain),
                sentenceWithBlank = "${s.contextClause} ${s.actor} firmly objected ________ without a formal hearing.",
                correct = "to being ${s.passV3}",
                distractors = listOf(
                    "to be ${s.passV3}",
                    "being ${s.passV3}",
                    "against being ${s.passV3}",
                ),
                reason = "'Object to' takes the preposition 'to' plus a gerund (here passive gerund 'being ${s.passV3}'). Using *object to be* or dropping 'to' is ungrammatical.",
                meaning = "${cap(s.actor)} protested strongly against being ${s.passV3} without due process.",
            ),
            // 3. resort to + V-ing
            rotatedChoice(
                id = startId + 2,
                category = ExamCategory.EXPRESSIONS_PREP_TO,
                tags = listOf("resort to + V-ing", s.domain),
                sentenceWithBlank = "When calmer measures failed, ${s.actor} reluctantly resorted ________ to save the operation.",
                correct = "to ${s.actIng}",
                distractors = listOf(
                    "to ${s.actInf}",
                    "in ${s.actIng}",
                    s.actInf,
                ),
                reason = "The expression 'resort to' treats 'to' as a preposition ('turn to as a last option'), so it strictly requires V-ing ('to ${s.actIng}'), never *resort to + inf*.",
                meaning = "${cap(s.actor)} felt forced to adopt the drastic measure of ${s.actIng}.",
            ),
            // 4. with a view to + V-ing (contrast with in order to + inf)
            rotatedChoice(
                id = startId + 3,
                category = ExamCategory.EXPRESSIONS_PREP_TO,
                tags = listOf("with a view to + V-ing", s.domain),
                sentenceWithBlank = "${cap(s.actor)} revised the workflow with a view ________ ahead of the inspection.",
                correct = "to ${s.actIng}",
                distractors = listOf(
                    "to ${s.actInf}",
                    "of ${s.actIng}",
                    "for ${s.actInf}",
                ),
                reason = "Unlike 'in order to + infinitive', the formal purpose expression 'with a view to' ends with a preposition 'to' and therefore must be followed by V-ing ('to ${s.actIng}').",
                meaning = "${cap(s.actor)} updated the procedure specifically intending to accomplish ${s.actIng} before the review.",
            ),
            // 5. committed to + V-ing vs determined to + inf
            rotatedChoice(
                id = startId + 4,
                category = ExamCategory.EXPRESSIONS_PREP_TO,
                tags = listOf("committed to + V-ing", "determined to + V", s.domain),
                sentenceWithBlank = "Even when skeptics urged retreat, ${s.actor} remained wholly committed ________ before dawn.",
                correct = "to ${s.actIng}",
                distractors = listOf(
                    "to ${s.actInf}",
                    "at ${s.actIng}",
                    s.actInf,
                ),
                reason = "Contrast 'be determined to + infinitive' with 'be committed/dedicated/devoted to + V-ing': here 'to' is a preposition linking 'committed' to a gerund ('to ${s.actIng}').",
                meaning = "${cap(s.actor)} stayed completely dedicated to ${s.actIng} prior to sunrise.",
            ),
        )
    }

    private fun buildFixedGerundExpressions(s: VerbExprScenario, startId: Int): List<VerbQuestion> {
        return listOf(
            // 6. get round to + V-ing
            rotatedChoice(
                id = startId,
                category = ExamCategory.FIXED_GERUND_EXPRESSIONS,
                tags = listOf("get round to + V-ing", s.domain),
                sentenceWithBlank = "Despite constant interruptions, ${s.partner} finally got round ________ late that evening.",
                correct = "to ${s.actIng}",
                distractors = listOf(
                    "to ${s.actInf}",
                    "with ${s.actIng}",
                    "on ${s.actInf}",
                ),
                reason = "The phrasal expression 'get (a)round to' means 'find time to do something', and its final 'to' is a preposition requiring a gerund ('to ${s.actIng}').",
                meaning = "${cap(s.partner)} eventually found the time for ${s.actIng} despite being busy.",
            ),
            // 7. confess to + having V3
            rotatedChoice(
                id = startId + 1,
                category = ExamCategory.FIXED_GERUND_EXPRESSIONS,
                tags = listOf("confess to + having V3", s.domain),
                sentenceWithBlank = "${s.contextClause} ${s.partner} quietly confessed ________ without senior clearance.",
                correct = "to having ${s.actV3}",
                distractors = listOf(
                    "to have ${s.actV3}",
                    "for having ${s.actV3}",
                    "to ${s.actInf}",
                ),
                reason = "Unlike 'claim to have V3', 'confess to' takes the preposition 'to' + gerund. Referring to an earlier event calls for the perfect gerund ('to having ${s.actV3}').",
                meaning = "${cap(s.partner)} admitted to having already ${s.actV3} without approval.",
            ),
            // 8. it's no use + V-ing
            rotatedChoice(
                id = startId + 2,
                category = ExamCategory.FIXED_GERUND_EXPRESSIONS,
                tags = listOf("it's no use + V-ing", s.domain),
                sentenceWithBlank = "As ${s.actor} reminded the staff, it is no use ________ once the log is locked.",
                correct = s.actIng,
                distractors = listOf(
                    "to ${s.actInf}",
                    s.actInf,
                    "for ${s.actInf}",
                ),
                reason = "The idiomatic expressions 'it's no use' and 'it's no good' take a gerund ('${s.actIng}'), not a to-infinitive in standard formal exams.",
                meaning = "${cap(s.actIng)} after the log is sealed accomplishes nothing.",
            ),
            // 9. there's no point (in) + V-ing
            rotatedChoice(
                id = startId + 3,
                category = ExamCategory.FIXED_GERUND_EXPRESSIONS,
                tags = listOf("there's no point in + V-ing", s.domain),
                sentenceWithBlank = "${s.contextClause} there is clearly no point in ________ at this late stage.",
                correct = s.actIng,
                distractors = listOf(
                    "to ${s.actInf}",
                    s.actInf,
                    "having to ${s.actInf}",
                ),
                reason = "'There is no point (in)' governs a gerund ('${s.actIng}'). Preposition 'in' can never be followed by 'to + infinitive'.",
                meaning = "Doing ${s.actIng} right now serves no useful purpose.",
            ),
            // 10. on the verge of + V-ing (contrast with be about to + inf)
            rotatedChoice(
                id = startId + 4,
                category = ExamCategory.FIXED_GERUND_EXPRESSIONS,
                tags = listOf("on the verge of + V-ing", "be about to + V", s.domain),
                sentenceWithBlank = "Just as new readings arrived, ${s.actor} was on the verge of ________.",
                correct = s.actIng,
                distractors = listOf(
                    "to ${s.actInf}",
                    s.actInf,
                    "to have ${s.actV3}",
                ),
                reason = "Compare 'be about to + infinitive' with 'be on the verge/point of + V-ing'. Because the expression ends in the preposition 'of', it strictly requires a gerund ('${s.actIng}').",
                meaning = "${cap(s.actor)} was just seconds away from ${s.actIng} when updated readings appeared.",
            ),
        )
    }

    private fun buildInfinitiveAndPreference(s: VerbExprScenario, startId: Int): List<VerbQuestion> {
        return listOf(
            // 11. can't help + V-ing vs can't afford + to inf
            rotatedChoice(
                id = startId,
                category = ExamCategory.INFINITIVE_AND_PREFERENCE,
                tags = listOf("can't help + V-ing", "can't afford + to V", s.domain),
                sentenceWithBlank = "Although ${s.partner} simply cannot afford ________ more errors, ${s.partner} couldn't help ________ when the sensor spiked.",
                correct = "to risk / ${s.habitIng}",
                distractors = listOf(
                    "risking / to ${s.habitInf}",
                    "to risk / to ${s.habitInf}",
                    "risk / ${s.habitIng}",
                ),
                reason = "'Cannot afford' always takes 'to + infinitive' ('to risk'), whereas 'couldn't help' (meaning 'couldn't stop oneself') strictly takes a gerund ('${s.habitIng}').",
                meaning = "Even though ${s.partner} cannot bear the cost of risking further mistakes, ${s.partner} involuntarily kept ${s.habitIng}.",
            ),
            // 12. would prefer + to inf (vs bare prefer + V-ing)
            rotatedChoice(
                id = startId + 1,
                category = ExamCategory.INFINITIVE_AND_PREFERENCE,
                tags = listOf("would prefer + to V", "would rather + V", s.domain),
                sentenceWithBlank = "Given the tight margin, ${s.actor} would prefer ________ rather than wait for committee approval.",
                correct = "to ${s.actInf}",
                distractors = listOf(
                    s.actIng,
                    s.actInf,
                    "to ${s.actIng}",
                ),
                reason = "While bare 'prefer' can take a gerund, 'would prefer' (with 'would') strictly requires 'to + infinitive' ('to ${s.actInf}'). Only 'would rather' takes the bare infinitive.",
                meaning = "${cap(s.actor)} prefers to ${s.actInf} immediately instead of waiting for approval.",
            ),
            // 13. had better + bare inf
            rotatedChoice(
                id = startId + 2,
                category = ExamCategory.INFINITIVE_AND_PREFERENCE,
                tags = listOf("had better + bare V", s.domain),
                sentenceWithBlank = "${s.contextClause} ${s.partner} had better ________ before the oversight board arrives.",
                correct = s.actInf,
                distractors = listOf(
                    "to ${s.actInf}",
                    s.actIng,
                    "having ${s.actV3}",
                ),
                reason = "The modal idiom 'had better' is always followed by the bare infinitive ('${s.actInf}'), never *had better to + V* or a gerund.",
                meaning = "${cap(s.partner)} urgently needs to ${s.actInf} before the board shows up.",
            ),
            // 14. worth + V-ing (passive meaning in active form)
            rotatedChoice(
                id = startId + 3,
                category = ExamCategory.INFINITIVE_AND_PREFERENCE,
                tags = listOf("be worth + V-ing", s.domain),
                sentenceWithBlank = "In light of the ${s.domain.lowercase()} readings, ${s.actor} concluded it was not worth ________ twice.",
                correct = s.actIng,
                distractors = listOf(
                    "to ${s.actInf}",
                    s.actInf,
                    "for ${s.actIng}",
                ),
                reason = "The adjective expression 'be worth' strictly governs V-ing ('worth ${s.actIng}'), never *worth to + infinitive*.",
                meaning = "Doing ${s.actIng} a second time was unnecessary given the stable baseline.",
            ),
            // 15. be made + to inf (passive causative)
            rotatedChoice(
                id = startId + 4,
                category = ExamCategory.INFINITIVE_AND_PREFERENCE,
                tags = listOf("be made + to V", "make + O + V", s.domain),
                sentenceWithBlank = "Under strict ${s.domain.lowercase()} rules, ${s.partner} was made ________ before logging off.",
                correct = "to ${s.actInf}",
                distractors = listOf(
                    s.actInf,
                    s.actIng,
                    "for ${s.actIng}",
                ),
                reason = "Active 'make someone do' drops 'to', but in the passive voice ('was made'), the full infinitive marker 'to' returns: 'was made to ${s.actInf}'.",
                meaning = "${cap(s.partner)} was compelled by protocol to ${s.actInf} prior to signing out.",
            ),
        )
    }

    private fun buildMeaningAndCausative(s: VerbExprScenario, startId: Int): List<VerbQuestion> {
        return listOf(
            // 16. accustomed to + V-ing
            rotatedChoice(
                id = startId,
                category = ExamCategory.MEANING_AND_CAUSATIVE,
                tags = listOf("accustomed to + V-ing", s.domain),
                sentenceWithBlank = "After a decade in ${s.domain.lowercase()}, ${s.actor} is thoroughly accustomed ________ under extreme time pressure.",
                correct = "to ${s.habitIng}",
                distractors = listOf(
                    "to ${s.habitInf}",
                    "with ${s.habitIng}",
                    s.habitInf,
                ),
                reason = "In 'be/grow/get accustomed to', 'to' is a preposition meaning 'familiar with', so it always takes a noun or gerund ('to ${s.habitIng}').",
                meaning = "${cap(s.actor)} is completely comfortable with ${s.habitIng} under pressure.",
            ),
            // 17. get used to + V-ing vs used to + inf
            rotatedChoice(
                id = startId + 1,
                category = ExamCategory.MEANING_AND_CAUSATIVE,
                tags = listOf("get used to + V-ing", "used to + V", s.domain),
                sentenceWithBlank = "The overnight shifts felt brutal at first, yet ${s.partner} gradually got used ________ without hesitation.",
                correct = "to ${s.habitIng}",
                distractors = listOf(
                    "to ${s.habitInf}",
                    s.habitInf,
                    "for ${s.habitIng}",
                ),
                reason = "'Used to + base verb' expresses a discontinued past habit, whereas 'be/get used to + V-ing' means becoming accustomed to an activity ('got used to ${s.habitIng}').",
                meaning = "Although night shifts were hard initially, ${s.partner} became comfortable with ${s.habitIng}.",
            ),
            // 18. regret + not having V3 (past remorse vs formal regret to V)
            rotatedChoice(
                id = startId + 2,
                category = ExamCategory.MEANING_AND_CAUSATIVE,
                tags = listOf("regret + having V3", "regret + to V", s.domain),
                sentenceWithBlank = "${s.contextClause} ${s.actor} bitterly regretted not ________ before the window closed.",
                correct = "having ${s.actV3}",
                distractors = listOf(
                    "to ${s.actInf}",
                    "to have ${s.actV3}",
                    "for ${s.actIng}",
                ),
                reason = "'Regret + to-infinitive' is reserved for announcing bad news politely ('regret to inform'). Expressing remorse over a past failure takes V-ing or perfect gerund ('not having ${s.actV3}').",
                meaning = "${cap(s.actor)} felt deep remorse for failing to ${s.actInf} in time.",
            ),
            // 19. mean + V-ing (involve/entail) vs mean + to inf (intend)
            rotatedChoice(
                id = startId + 3,
                category = ExamCategory.MEANING_AND_CAUSATIVE,
                tags = listOf("mean + V-ing", "mean + to V", s.domain),
                sentenceWithBlank = "Accepting lead responsibility in ${s.domain.lowercase()} inevitably means ________ every single night.",
                correct = s.habitIng,
                distractors = listOf(
                    "to ${s.habitInf}",
                    s.habitInf,
                    "for ${s.habitIng}",
                ),
                reason = "'Mean + to-infinitive' means 'intend', whereas 'mean + V-ing' means 'entail or involve as an unavoidable consequence'. Here accepting the post entails ${s.habitIng}.",
                meaning = "Taking the senior post unavoidably involves ${s.habitIng} every evening.",
            ),
            // 20. prevent + O + from + V-ing
            rotatedChoice(
                id = startId + 4,
                category = ExamCategory.MEANING_AND_CAUSATIVE,
                tags = listOf("prevent + O + from + V-ing", s.domain),
                sentenceWithBlank = "${s.contextClause} nothing could prevent ${s.partner} ________ once the alarm sounded.",
                correct = "from ${s.actIng}",
                distractors = listOf(
                    "to ${s.actInf}",
                    "against ${s.actIng}",
                    s.actInf,
                ),
                reason = "Unlike 'force/persuade/urge someone to do', 'prevent' never takes a to-infinitive (*prevent someone to do* is invalid); it requires 'prevent + object + (from) + V-ing'.",
                meaning = "Nothing was able to stop ${s.partner} from ${s.actIng} after the alert triggered.",
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
        val uniquePool = (listOf(correct) + distractors).distinct()
        require(uniquePool.size == 4) { "Question $id must have 4 unique options, got $uniquePool" }
        val shift = id % 4
        val rotated = ArrayList<String>(4)
        for (i in 0 until 4) {
            rotated += uniquePool[(i - shift + 4) % 4]
        }
        val correctIdx = rotated.indexOf(correct)
        return VerbQuestion(
            id = id,
            format = QuestionFormat.CHOICE,
            category = category,
            verbsInvolved = tags,
            sentenceWithBlank = sentenceWithBlank,
            options = rotated,
            correctIndex = correctIdx,
            reason = reason,
            englishMeaning = meaning,
        )
    }

    private fun cap(text: String): String =
        text.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}
