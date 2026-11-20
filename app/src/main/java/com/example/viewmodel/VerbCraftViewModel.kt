package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.VerbCodexData
import com.example.data.VerbQuestionRepository
import com.example.model.AnsweredQuestion
import com.example.model.ExamCategory
import com.example.model.ExamLength
import com.example.model.ExamPhase
import com.example.model.MainTab
import com.example.model.VerbCodexEntry
import com.example.model.VerbPatternGroup
import com.example.model.VerbQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class VerbCraftUiState(
    val activeTab: MainTab = MainTab.EXAM,
    val examPhase: ExamPhase = ExamPhase.HOME,
    val selectedCategory: ExamCategory = ExamCategory.ALL,
    val selectedLength: ExamLength = ExamLength.TWENTY,
    val examQuestions: List<VerbQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val answeredQuestions: List<AnsweredQuestion> = emptyList(),
    val currentStreak: Int = 0,
    val bestStreakInSession: Int = 0,
    val totalCorrectLifetime: Int = 0,
    val totalAnsweredLifetime: Int = 0,
    val showExitConfirmDialog: Boolean = false,
    val codexQuery: String = "",
    val selectedCodexGroup: VerbPatternGroup? = null,
) {
    val currentQuestion: VerbQuestion?
        get() = examQuestions.getOrNull(currentIndex)

    val isCurrentAnswered: Boolean
        get() = selectedOptionIndex != null

    val isLastQuestion: Boolean
        get() = currentIndex >= examQuestions.lastIndex

    val sessionCorrectCount: Int
        get() = answeredQuestions.count { it.isCorrect }

    val missedQuestions: List<AnsweredQuestion>
        get() = answeredQuestions.filter { !it.isCorrect }

    val filteredCodexEntries: List<VerbCodexEntry>
        get() {
            val q = codexQuery.trim().lowercase()
            return VerbCodexData.entries.filter { entry ->
                val matchesGroup = selectedCodexGroup == null || entry.group == selectedCodexGroup
                val matchesQuery = q.isEmpty() ||
                    entry.verb.lowercase().contains(q) ||
                    entry.formula.lowercase().contains(q) ||
                    entry.ruleNote.lowercase().contains(q)
                matchesGroup && matchesQuery
            }
        }
}

class VerbCraftViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(VerbCraftUiState())
    val uiState: StateFlow<VerbCraftUiState> = _uiState.asStateFlow()

    fun selectTab(tab: MainTab) {
        _uiState.update { it.copy(activeTab = tab) }
    }

    fun selectCategory(category: ExamCategory) {
        _uiState.update { state ->
            val available = VerbQuestionRepository.countForCategory(category)
            val adjustedLength = if (state.selectedLength == ExamLength.TWENTY && available < 20) {
                ExamLength.ALL
            } else {
                state.selectedLength
            }
            state.copy(selectedCategory = category, selectedLength = adjustedLength)
        }
    }

    fun selectLength(length: ExamLength) {
        _uiState.update { it.copy(selectedLength = length) }
    }

    fun startExam() {
        val state = _uiState.value
        val count = if (state.selectedLength == ExamLength.ALL) {
            VerbQuestionRepository.countForCategory(state.selectedCategory)
        } else {
            state.selectedLength.count
        }
        val questions = VerbQuestionRepository.getQuestionsForExam(state.selectedCategory, count)
        _uiState.update {
            it.copy(
                activeTab = MainTab.EXAM,
                examPhase = ExamPhase.ACTIVE,
                examQuestions = questions,
                currentIndex = 0,
                selectedOptionIndex = null,
                answeredQuestions = emptyList(),
                currentStreak = 0,
                bestStreakInSession = 0,
                showExitConfirmDialog = false,
            )
        }
    }

    fun retryMissedQuestions() {
        val missed = _uiState.value.missedQuestions.map { it.question }.shuffled()
        if (missed.isEmpty()) return
        _uiState.update {
            it.copy(
                examPhase = ExamPhase.ACTIVE,
                examQuestions = missed,
                currentIndex = 0,
                selectedOptionIndex = null,
                answeredQuestions = emptyList(),
                currentStreak = 0,
                bestStreakInSession = 0,
                showExitConfirmDialog = false,
            )
        }
    }

    fun submitAnswer(optionIndex: Int) {
        val state = _uiState.value
        val question = state.currentQuestion ?: return
        if (state.selectedOptionIndex != null) return

        val isCorrect = optionIndex == question.correctIndex
        val newStreak = if (isCorrect) state.currentStreak + 1 else 0
        val newBest = maxOf(state.bestStreakInSession, newStreak)
        val record = AnsweredQuestion(question = question, selectedIndex = optionIndex)

        _uiState.update {
            it.copy(
                selectedOptionIndex = optionIndex,
                answeredQuestions = it.answeredQuestions + record,
                currentStreak = newStreak,
                bestStreakInSession = newBest,
                totalAnsweredLifetime = it.totalAnsweredLifetime + 1,
                totalCorrectLifetime = it.totalCorrectLifetime + if (isCorrect) 1 else 0,
            )
        }
    }

    fun advanceToNextQuestion() {
        val state = _uiState.value
        if (state.selectedOptionIndex == null) return

        if (state.isLastQuestion) {
            _uiState.update {
                it.copy(
                    examPhase = ExamPhase.RESULTS,
                    selectedOptionIndex = null,
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    currentIndex = it.currentIndex + 1,
                    selectedOptionIndex = null,
                )
            }
        }
    }

    fun requestExitExam() {
        _uiState.update { it.copy(showExitConfirmDialog = true) }
    }

    fun dismissExitDialog() {
        _uiState.update { it.copy(showExitConfirmDialog = false) }
    }

    fun confirmExitToHome() {
        _uiState.update {
            it.copy(
                examPhase = ExamPhase.HOME,
                selectedOptionIndex = null,
                showExitConfirmDialog = false,
            )
        }
    }

    fun returnHomeFromResults() {
        _uiState.update {
            it.copy(
                examPhase = ExamPhase.HOME,
                selectedOptionIndex = null,
            )
        }
    }

    fun updateCodexQuery(query: String) {
        _uiState.update { it.copy(codexQuery = query) }
    }

    fun selectCodexGroup(group: VerbPatternGroup?) {
        _uiState.update { state ->
            val next = if (state.selectedCodexGroup == group) null else group
            state.copy(selectedCodexGroup = next)
        }
    }
}
