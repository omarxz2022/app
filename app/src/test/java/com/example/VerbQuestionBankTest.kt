package com.example

import com.example.data.VerbCodexData
import com.example.data.VerbQuestionRepository
import com.example.model.ExamCategory
import com.example.model.ExamLength
import com.example.model.ExamPhase
import com.example.viewmodel.VerbCraftViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class VerbQuestionBankTest {

    @Test
    fun allQuestionsHaveValidStructureAndMatchingBlankCounts() {
        val all = VerbQuestionRepository.allQuestions
        assertEquals(50, all.size)
        assertEquals(50, all.map { it.id }.toSet().size)

        all.forEach { q ->
            assertEquals("Q${q.id} must have 4 options", 4, q.options.size)
            assertEquals("Q${q.id} options must be unique", 4, q.options.toSet().size)
            assertTrue("Q${q.id} correctIndex out of range", q.correctIndex in 0..3)
            assertTrue("Q${q.id} verbsInvolved empty", q.verbsInvolved.isNotEmpty())
            assertTrue("Q${q.id} reason blank", q.reason.isNotBlank())
            assertTrue("Q${q.id} englishMeaning blank", q.englishMeaning.isNotBlank())

            val blankCount = q.sentenceWithBlank.split("________").size - 1
            assertTrue("Q${q.id} must have at least 1 blank", blankCount >= 1)

            q.options.forEach { opt ->
                val parts = opt.split(" / ")
                assertEquals(
                    "Q${q.id} option '$opt' slot count mismatch with $blankCount blanks",
                    blankCount,
                    parts.size,
                )
            }

            val filled = q.filledSentence(q.correctOption)
            assertFalse("Q${q.id} filled sentence still has blank", filled.contains("________"))
        }
    }

    @Test
    fun codexCoversEntriesAndViewModelExamFlowWorks() {
        assertTrue(VerbCodexData.entries.size >= 85)

        val vm = VerbCraftViewModel()
        vm.selectCategory(ExamCategory.MEANING_SHIFTERS)
        vm.selectLength(ExamLength.TEN)
        vm.startExam()

        var state = vm.uiState.value
        assertEquals(ExamPhase.ACTIVE, state.examPhase)
        assertEquals(10, state.examQuestions.size)

        val q1 = state.currentQuestion
        assertNotNull(q1)
        val wrongIndex = (q1!!.correctIndex + 1) % 4
        vm.submitAnswer(wrongIndex)

        state = vm.uiState.value
        assertTrue(state.isCurrentAnswered)
        assertEquals(1, state.missedQuestions.size)
        assertEquals(0, state.currentStreak)

        // Advance through remaining questions
        while (vm.uiState.value.examPhase == ExamPhase.ACTIVE) {
            val current = vm.uiState.value.currentQuestion!!
            if (!vm.uiState.value.isCurrentAnswered) {
                vm.submitAnswer(current.correctIndex)
            }
            vm.advanceToNextQuestion()
        }

        state = vm.uiState.value
        assertEquals(ExamPhase.RESULTS, state.examPhase)
        assertEquals(9, state.sessionCorrectCount)
        assertEquals(1, state.missedQuestions.size)

        vm.retryMissedQuestions()
        state = vm.uiState.value
        assertEquals(ExamPhase.ACTIVE, state.examPhase)
        assertEquals(1, state.examQuestions.size)
        assertEquals(q1.id, state.currentQuestion?.id)
    }
}
