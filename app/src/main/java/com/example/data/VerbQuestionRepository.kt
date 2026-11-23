package com.example.data

import com.example.model.ExamCategory
import com.example.model.ExamSuite
import com.example.model.VerbQuestion

object VerbQuestionRepository {
    private val allScenarios: List<ConditionalScenario> by lazy {
        conditionalScenariosPart1 + conditionalScenariosPart2
    }

    val verbQuestions: List<VerbQuestion> by lazy {
        questionBankPart1 + questionBankPart2
    }

    val verbExpressionQuestions: List<VerbQuestion> by lazy {
        VerbExpressionGenerator.generateAll(verbExpressionScenarios)
    }

    val conditionalChoiceQuestions: List<VerbQuestion> by lazy {
        ConditionalChoiceGenerator.generateAll(allScenarios)
    }

    val conditionalRewriteQuestions: List<VerbQuestion> by lazy {
        ConditionalRewriteGenerator.generateAll(allScenarios)
    }

    val allConditionalQuestions: List<VerbQuestion> by lazy {
        conditionalChoiceQuestions + conditionalRewriteQuestions
    }

    val allQuestions: List<VerbQuestion> by lazy {
        verbQuestions + verbExpressionQuestions + allConditionalQuestions
    }

    private val questionsById: Map<Int, VerbQuestion> by lazy {
        allQuestions.associateBy { it.id }
    }

    fun getQuestionsByIds(ids: List<Int>): List<VerbQuestion> =
        ids.distinct().mapNotNull { questionsById[it] }

    fun categoriesForSuite(suite: ExamSuite): List<ExamCategory> = when (suite) {
        ExamSuite.VERBS_EXPRESSIONS -> listOf(
            ExamCategory.ALL,
            ExamCategory.EXPRESSIONS_PREP_TO,
            ExamCategory.FIXED_GERUND_EXPRESSIONS,
            ExamCategory.INFINITIVE_AND_PREFERENCE,
            ExamCategory.MEANING_AND_CAUSATIVE,
        )
        ExamSuite.VERBS -> listOf(
            ExamCategory.ALL,
            ExamCategory.MULTI_VERB_CHAINS,
            ExamCategory.MEANING_SHIFTERS,
            ExamCategory.PASSIVE_CAUSATIVE,
            ExamCategory.PREPOSITION_AND_OBJECT_TRAPS,
        )
        ExamSuite.IF_MIXED,
        ExamSuite.IF_CHOICE,
        ExamSuite.IF_REWRITE,
        -> listOf(
            ExamCategory.ALL,
            ExamCategory.IF_MIXED_AND_INVERSION,
            ExamCategory.IF_SPECIAL_CONJUNCTIONS,
        )
        ExamSuite.FIXING -> listOf(ExamCategory.ALL)
    }

    fun poolForSuite(suite: ExamSuite, category: ExamCategory): List<VerbQuestion> {
        val basePool = when (suite) {
            ExamSuite.VERBS_EXPRESSIONS -> verbExpressionQuestions
            ExamSuite.IF_MIXED -> allConditionalQuestions
            ExamSuite.IF_CHOICE -> conditionalChoiceQuestions
            ExamSuite.IF_REWRITE -> conditionalRewriteQuestions
            ExamSuite.VERBS -> verbQuestions
            ExamSuite.FIXING -> emptyList()
        }
        return if (category == ExamCategory.ALL) {
            basePool
        } else {
            basePool.filter { it.category == category }
        }
    }

    fun countForSuite(suite: ExamSuite, category: ExamCategory): Int =
        poolForSuite(suite, category).size

    fun getQuestionsForSuite(
        suite: ExamSuite,
        category: ExamCategory,
        count: Int,
    ): List<VerbQuestion> {
        if (suite == ExamSuite.IF_MIXED) {
            val choices = conditionalChoiceQuestions.let { list ->
                if (category == ExamCategory.ALL) list else list.filter { it.category == category }
            }.shuffled()
            val rewrites = conditionalRewriteQuestions.let { list ->
                if (category == ExamCategory.ALL) list else list.filter { it.category == category }
            }.shuffled()
            val limit = count.coerceAtMost(choices.size + rewrites.size)
            val halfChoices = (limit / 2).coerceAtMost(choices.size)
            val remainingRewrites = (limit - halfChoices).coerceAtMost(rewrites.size)
            return (choices.take(halfChoices) + rewrites.take(remainingRewrites)).shuffled()
        }
        val pool = poolForSuite(suite, category)
        return pool.shuffled().take(count.coerceAtMost(pool.size))
    }

    fun countForCategory(category: ExamCategory): Int =
        if (category == ExamCategory.ALL) allQuestions.size
        else allQuestions.count { it.category == category }
}
