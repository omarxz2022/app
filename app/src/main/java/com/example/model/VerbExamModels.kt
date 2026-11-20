package com.example.model

enum class ExamCategory(
    val title: String,
    val shortTitle: String,
    val description: String,
) {
    ALL(
        title = "Complete Mastery Mix",
        shortTitle = "All Hardest",
        description = "Every trap combined: stacked verb chains, meaning shifts, causatives, passive complements, and preposition 'to' puzzles.",
    ),
    MULTI_VERB_CHAINS(
        title = "Multi-Verb Chains",
        shortTitle = "Stacked Verbs",
        description = "Two to four verbs from your list chained in a single sentence, testing nested gerunds, infinitives, and voice shifts.",
    ),
    MEANING_SHIFTERS(
        title = "Meaning Shifters",
        shortTitle = "Meaning Shifts",
        description = "Context-driven contrasts for stop, remember, forget, try, regret, go on, used to, take to, and would prefer.",
    ),
    PASSIVE_CAUSATIVE(
        title = "Passive & Causative Traps",
        shortTitle = "Passive / Causative",
        description = "Make vs. be made to, let vs. be permitted to, get someone to/V-ing/V3, passive gerunds, and need/deserve/want + V-ing.",
    ),
    PREPOSITION_AND_OBJECT_TRAPS(
        title = "Preposition 'to' & Object Traps",
        shortTitle = "Prep 'to' & Objects",
        description = "Accustomed to, be used to, take to, suggest vs. advise, explain/know + wh-, forgive/prevent/mind + possessive gerunds.",
    ),
}

enum class ExamLength(val count: Int, val label: String) {
    TEN(10, "10 Qs"),
    TWENTY(20, "20 Qs"),
    ALL(50, "All 50 Qs"),
}

enum class MainTab {
    EXAM,
    CODEX,
}

enum class ExamPhase {
    HOME,
    ACTIVE,
    RESULTS,
}

data class VerbQuestion(
    val id: Int,
    val category: ExamCategory,
    val verbsInvolved: List<String>,
    val sentenceWithBlank: String,
    val options: List<String>,
    val correctIndex: Int,
    val reason: String,
    val englishMeaning: String,
) {
    val correctOption: String
        get() = options[correctIndex]

    fun filledSentence(selectedChoice: String): String {
        val parts = selectedChoice.split(" / ").map { it.trim() }
        var result = sentenceWithBlank
        if (parts.size > 1) {
            for (part in parts) {
                result = result.replaceFirst("________", part)
            }
            return result
        }
        return result.replaceFirst("________", selectedChoice)
    }
}

data class AnsweredQuestion(
    val question: VerbQuestion,
    val selectedIndex: Int,
) {
    val isCorrect: Boolean
        get() = selectedIndex == question.correctIndex
}

enum class VerbPatternGroup(val label: String) {
    GERUND("V + -ing"),
    INFINITIVE("V + to-infinitive"),
    OBJECT_INFINITIVE("V + O + to-infinitive"),
    MEANING_SHIFT("Meaning-Shift Verbs"),
    CAUSATIVE_BARE("Causative & Bare Infinitive"),
    PREPOSITION_TO("Preposition 'to' + V-ing"),
}

data class VerbCodexEntry(
    val verb: String,
    val group: VerbPatternGroup,
    val formula: String,
    val ruleNote: String,
    val example: String,
)
