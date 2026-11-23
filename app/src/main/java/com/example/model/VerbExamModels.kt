package com.example.model

import androidx.annotation.DrawableRes
import kotlin.math.roundToInt

enum class QuestionFormat {
    CHOICE,
    REWRITE,
}

enum class ExamSuite(
    val title: String,
    val subtitle: String,
    val badge: String,
) {
    VERBS_EXPRESSIONS(
        title = "Verbs & Expressions Gauntlet",
        subtitle = "1,000 different & difficult questions on verbs and expressions with to + inf vs V + ing.",
        badge = "1,000 Qs • +1🪙",
    ),
    IF_MIXED(
        title = "If-Clauses Grand Gauntlet",
        subtitle = "2,000 C2 questions mixing 4-choice (+1 coin) & rewrites (+5 coins) across all conditions.",
        badge = "2,000 Qs",
    ),
    IF_CHOICE(
        title = "If-Clauses: 4-Choice",
        subtitle = "1,000 hardest 4-choice questions covering Zero, 1st, 2nd, 3rd, Mixed, Inverted & But for.",
        badge = "1,000 Qs • +1🪙",
    ),
    IF_REWRITE(
        title = "If-Clauses: Sentence Rewrite",
        subtitle = "1,000 advanced transformation & inversion rewrites. +5 coins per exact rewrite.",
        badge = "1,000 Qs • +5🪙",
    ),
    VERBS(
        title = "Verb Complement Mastery",
        subtitle = "50 stacked V+ing, to+inf, causative & meaning-shift questions (+1 coin each).",
        badge = "50 Qs • +1🪙",
    ),
    FIXING(
        title = "Fixing Exam",
        subtitle = "All questions you got wrong across exams in one session, question by question until mastered.",
        badge = "Mistakes",
    ),
}

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
    IF_MIXED_AND_INVERSION(
        title = "Mixed & Inverted If-Clauses",
        shortTitle = "Mixed & Inverted",
        description = "Counterfactual mixed timelines, Had/Were/Should inversions, and But for nominalizations.",
    ),
    IF_SPECIAL_CONJUNCTIONS(
        title = "Special Conditions & Modals",
        shortTitle = "Unless / Even if / In case",
        description = "Unless, Provided that, Supposing, Even if, In case, Otherwise, Zero/1st/2nd/3rd nuances.",
    ),
    EXPRESSIONS_PREP_TO(
        title = "Preposition 'to' Expressions",
        shortTitle = "Prep 'to' Exprs",
        description = "look forward to, object to, resort to, with a view to, committed to, get round to, accustomed to + V-ing.",
    ),
    FIXED_GERUND_EXPRESSIONS(
        title = "Idiomatic + V-ing Expressions",
        shortTitle = "Idioms + V-ing",
        description = "it's no use, there's no point in, can't help, be worth, on the verge of, confess to having V3.",
    ),
    INFINITIVE_AND_PREFERENCE(
        title = "Infinitive & Preference Traps",
        shortTitle = "Inf & Preference",
        description = "would prefer to V vs prefer V-ing, had better + bare V, be made to V, prevent from V-ing.",
    ),
    MEANING_AND_CAUSATIVE(
        title = "Meaning Shifts & Aspect",
        shortTitle = "Meaning & Aspect",
        description = "regret (not) having V3 vs regret to V, mean V-ing vs mean to V, get used to V-ing vs used to V.",
    ),
}

enum class ExamLength(val count: Int, val label: String) {
    TEN(10, "10 Qs"),
    TWENTY(20, "20 Qs"),
    FIFTY(50, "50 Qs"),
    ALL(100, "100 Qs"),
}

enum class MainTab {
    EXAM,
    RATING,
    PRIZES,
    COLLECTION,
}

enum class ExamPhase {
    HOME,
    ACTIVE,
    RESULTS,
}

data class VerbQuestion(
    val id: Int,
    val format: QuestionFormat = QuestionFormat.CHOICE,
    val category: ExamCategory,
    val verbsInvolved: List<String>,
    val sentenceWithBlank: String,
    val options: List<String> = emptyList(),
    val correctIndex: Int = 0,
    val rewritePrompt: String = "",
    val rewriteStarterHint: String = "",
    val acceptableRewrites: List<String> = emptyList(),
    val reason: String,
    val englishMeaning: String,
) {
    val coinReward: Int
        get() = if (format == QuestionFormat.REWRITE) 5 else 1

    val correctOption: String
        get() = if (format == QuestionFormat.REWRITE) {
            acceptableRewrites.firstOrNull().orEmpty()
        } else {
            options.getOrElse(correctIndex) { "" }
        }

    fun filledSentence(selectedChoice: String): String {
        if (format == QuestionFormat.REWRITE) {
            return selectedChoice
        }
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

    fun isRewriteCorrect(userInput: String): Boolean {
        val normalizedInput = normalizeRewrite(userInput)
        if (normalizedInput.isBlank()) return false
        return acceptableRewrites.any { normalizeRewrite(it) == normalizedInput }
    }

    companion object {
        fun normalizeRewrite(raw: String): String {
            return raw
                .trim()
                .replace('\u2019', '\'')
                .replace('\u2018', '\'')
                .replace(",", " ")
                .replace(".", "")
                .replace("!", "")
                .replace("?", "")
                .replace(";", " ")
                .lowercase()
                .replace(Regex("\\s+"), " ")
                .trim()
        }
    }
}

data class AnsweredQuestion(
    val question: VerbQuestion,
    val selectedIndex: Int = -1,
    val typedAnswer: String = "",
    val isCorrectOverride: Boolean? = null,
) {
    val isCorrect: Boolean
        get() = isCorrectOverride ?: (selectedIndex == question.correctIndex)

    val userAnswerDisplay: String
        get() = if (question.format == QuestionFormat.REWRITE) {
            typedAnswer.ifBlank { "(No answer)" }
        } else {
            question.options.getOrElse(selectedIndex) { "" }
        }
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

enum class PrizeCategory(val label: String) {
    CAR("Cars"),
    MOTORBIKE("Motorbikes"),
    FOOD("Food"),
}

data class PrizeItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: PrizeCategory,
    val price: Int,
    @DrawableRes val imageRes: Int,
)

data class UserProfile(
    val id: String = "local_user",
    val userId: String = "local_user",
    val displayName: String = "",
    val grade: String = "",
    val coins: Long = 0L,
    val totalAttempted: Long = 0L,
    val totalCorrect: Long = 0L,
    val choiceCorrect: Long = 0L,
    val rewriteCorrect: Long = 0L,
    val fastingMode: Boolean = false,
) {
    val accuracyPercent: Int
        get() = if (totalAttempted <= 0L) 0 else ((totalCorrect * 100.0) / totalAttempted).roundToInt().coerceIn(0, 100)

    val ratingOutOfTen: Double
        get() = if (totalAttempted <= 0L) 0.0 else 1.0 + (totalCorrect.toDouble() / totalAttempted.toDouble()) * 9.0

    val ratingTier: Int
        get() = if (totalAttempted <= 0L) 0 else ratingOutOfTen.roundToInt().coerceIn(1, 10)

    val scholarRank: String
        get() = when {
            totalAttempted <= 0L -> "Unrated Challenger"
            ratingTier >= 9 -> "Oxford Grandmaster"
            ratingTier >= 7 -> "Senior Syntactician"
            ratingTier >= 5 -> "Rising Grammarian"
            ratingTier >= 3 -> "Focused Contender"
            else -> "Apprentice Scholar"
        }
}

data class CollectableItem(
    val id: String = "",
    val prizeId: String = "",
    val userId: String = "local_user",
    val title: String = "",
    val category: String = PrizeCategory.CAR.name,
    val price: Long = 0L,
    val quantity: Long = 1L,
) {
    val categoryEnum: PrizeCategory
        get() = PrizeCategory.entries.firstOrNull { it.name == category } ?: PrizeCategory.CAR
}
