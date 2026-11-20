package com.example.data

import com.example.model.ExamCategory
import com.example.model.VerbQuestion

object VerbQuestionRepository {
    val allQuestions: List<VerbQuestion> = questionBankPart1 + questionBankPart2

    fun getQuestionsForExam(category: ExamCategory, count: Int): List<VerbQuestion> {
        val pool = if (category == ExamCategory.ALL) {
            allQuestions
        } else {
            allQuestions.filter { it.category == category }
        }
        return pool.shuffled().take(count.coerceAtMost(pool.size))
    }

    fun countForCategory(category: ExamCategory): Int =
        if (category == ExamCategory.ALL) allQuestions.size
        else allQuestions.count { it.category == category }
}
