package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ProgressStore
import com.example.data.VerbCodexData
import com.example.data.VerbQuestionRepository
import com.example.model.AnsweredQuestion
import com.example.model.CollectableItem
import com.example.model.ExamCategory
import com.example.model.ExamLength
import com.example.model.ExamPhase
import com.example.model.ExamSuite
import com.example.model.MainTab
import com.example.model.PrizeCategory
import com.example.model.PrizeItem
import com.example.model.QuestionFormat
import com.example.model.UserProfile
import com.example.model.VerbCodexEntry
import com.example.model.VerbPatternGroup
import com.example.model.VerbQuestion
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface DataState<out T> {
    data object Loading : DataState<Nothing>
    data class Success<T>(val data: T) : DataState<T>
    data class Error(val message: String) : DataState<Nothing>
}

data class VerbCraftUiState(
    val activeTab: MainTab = MainTab.EXAM,
    val examPhase: ExamPhase = ExamPhase.HOME,
    val selectedSuite: ExamSuite = ExamSuite.IF_MIXED,
    val selectedCategory: ExamCategory = ExamCategory.ALL,
    val selectedLength: ExamLength = ExamLength.TWENTY,
    val examQuestions: List<VerbQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val rewriteInput: String = "",
    val rewriteSubmitted: Boolean = false,
    val answeredQuestions: List<AnsweredQuestion> = emptyList(),
    val currentStreak: Int = 0,
    val bestStreakInSession: Int = 0,
    val sessionCoinsEarned: Int = 0,
    val lastCoinDelta: Int = 0,
    val showExitConfirmDialog: Boolean = false,
    val showCodexSheet: Boolean = false,
    val codexQuery: String = "",
    val selectedCodexGroup: VerbPatternGroup? = null,
    val selectedPrizeCategory: PrizeCategory? = null,
    val purchasingPrizeId: String? = null,
    val isSavingProfile: Boolean = false,
    val statusBannerMessage: String? = null,
    val statusBannerIsError: Boolean = false,
) {
    val currentQuestion: VerbQuestion?
        get() = examQuestions.getOrNull(currentIndex)

    val isCurrentAnswered: Boolean
        get() = when (currentQuestion?.format) {
            QuestionFormat.REWRITE -> rewriteSubmitted
            QuestionFormat.CHOICE -> selectedOptionIndex != null
            null -> false
        }

    val isLastQuestion: Boolean
        get() = currentIndex >= examQuestions.lastIndex

    val sessionCorrectCount: Int
        get() = answeredQuestions.count { it.isCorrect }

    val missedQuestions: List<AnsweredQuestion>
        get() = answeredQuestions.filter { !it.isCorrect }

    val availableCategories: List<ExamCategory>
        get() = VerbQuestionRepository.categoriesForSuite(selectedSuite)

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

class VerbCraftViewModel(
    private val repository: ProgressStore,
    private val currentUserId: String = "local_user",
    private val defaultDisplayName: String = "",
) : ViewModel() {

    private val _uiState = MutableStateFlow(VerbCraftUiState())
    val uiState: StateFlow<VerbCraftUiState> = _uiState.asStateFlow()

    val profileState: StateFlow<DataState<UserProfile?>> =
        repository.observeUserProfile(currentUserId)
            .map<UserProfile?, DataState<UserProfile?>> { DataState.Success(it) }
            .catch { e ->
                if (e is CancellationException) throw e
                emit(DataState.Error(e.localizedMessage ?: "Could not load local profile."))
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), DataState.Loading)

    val collectionState: StateFlow<DataState<List<CollectableItem>>> =
        repository.observeCollection(currentUserId)
            .map<List<CollectableItem>, DataState<List<CollectableItem>>> { items ->
                DataState.Success(items.sortedByDescending { it.price })
            }
            .catch { e ->
                if (e is CancellationException) throw e
                emit(DataState.Error(e.localizedMessage ?: "Could not load local collection."))
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), DataState.Loading)

    init {
        viewModelScope.launch {
            repository.ensureProfileExists(currentUserId, defaultDisplayName)
        }
    }

    fun selectTab(tab: MainTab) {
        _uiState.update { it.copy(activeTab = tab, statusBannerMessage = null) }
    }

    fun selectSuite(suite: ExamSuite) {
        _uiState.update { state ->
            val validCategories = VerbQuestionRepository.categoriesForSuite(suite)
            val nextCategory = if (state.selectedCategory in validCategories) {
                state.selectedCategory
            } else {
                ExamCategory.ALL
            }
            val available = VerbQuestionRepository.countForSuite(suite, nextCategory)
            val nextLength = if (state.selectedLength == ExamLength.TWENTY && available < 20) {
                ExamLength.ALL
            } else {
                state.selectedLength
            }
            state.copy(
                selectedSuite = suite,
                selectedCategory = nextCategory,
                selectedLength = nextLength,
            )
        }
    }

    fun selectCategory(category: ExamCategory) {
        _uiState.update { state ->
            val available = VerbQuestionRepository.countForSuite(state.selectedSuite, category)
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
        val available = VerbQuestionRepository.countForSuite(state.selectedSuite, state.selectedCategory)
        val count = if (state.selectedLength == ExamLength.ALL) {
            if (state.selectedSuite == ExamSuite.VERBS) available else 100
        } else {
            state.selectedLength.count.coerceAtMost(available)
        }
        val questions = VerbQuestionRepository.getQuestionsForSuite(
            suite = state.selectedSuite,
            category = state.selectedCategory,
            count = count,
        )
        _uiState.update {
            it.copy(
                activeTab = MainTab.EXAM,
                examPhase = ExamPhase.ACTIVE,
                examQuestions = questions,
                currentIndex = 0,
                selectedOptionIndex = null,
                rewriteInput = "",
                rewriteSubmitted = false,
                answeredQuestions = emptyList(),
                currentStreak = 0,
                bestStreakInSession = 0,
                sessionCoinsEarned = 0,
                lastCoinDelta = 0,
                showExitConfirmDialog = false,
                statusBannerMessage = null,
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
                rewriteInput = "",
                rewriteSubmitted = false,
                answeredQuestions = emptyList(),
                currentStreak = 0,
                bestStreakInSession = 0,
                sessionCoinsEarned = 0,
                lastCoinDelta = 0,
                showExitConfirmDialog = false,
            )
        }
    }

    fun submitChoiceAnswer(optionIndex: Int) {
        val state = _uiState.value
        val question = state.currentQuestion ?: return
        if (question.format != QuestionFormat.CHOICE || state.selectedOptionIndex != null) return

        val isCorrect = optionIndex == question.correctIndex
        val coinDelta = if (isCorrect) question.coinReward else 0
        val newStreak = if (isCorrect) state.currentStreak + 1 else 0
        val newBest = maxOf(state.bestStreakInSession, newStreak)
        val record = AnsweredQuestion(question = question, selectedIndex = optionIndex)

        _uiState.update {
            it.copy(
                selectedOptionIndex = optionIndex,
                answeredQuestions = it.answeredQuestions + record,
                currentStreak = newStreak,
                bestStreakInSession = newBest,
                sessionCoinsEarned = it.sessionCoinsEarned + coinDelta,
                lastCoinDelta = coinDelta,
            )
        }

        viewModelScope.launch {
            repository.recordAnswer(
                userId = currentUserId,
                isCorrect = isCorrect,
                isRewrite = false,
            )
        }
    }

    fun updateRewriteInput(input: String) {
        if (_uiState.value.rewriteSubmitted) return
        _uiState.update { it.copy(rewriteInput = input) }
    }

    fun submitRewriteAnswer() {
        val state = _uiState.value
        val question = state.currentQuestion ?: return
        if (question.format != QuestionFormat.REWRITE || state.rewriteSubmitted) return
        if (state.rewriteInput.isBlank()) return

        val isCorrect = question.isRewriteCorrect(state.rewriteInput)
        val coinDelta = if (isCorrect) question.coinReward else 0
        val newStreak = if (isCorrect) state.currentStreak + 1 else 0
        val newBest = maxOf(state.bestStreakInSession, newStreak)
        val record = AnsweredQuestion(
            question = question,
            typedAnswer = state.rewriteInput.trim(),
            isCorrectOverride = isCorrect,
        )

        _uiState.update {
            it.copy(
                rewriteSubmitted = true,
                answeredQuestions = it.answeredQuestions + record,
                currentStreak = newStreak,
                bestStreakInSession = newBest,
                sessionCoinsEarned = it.sessionCoinsEarned + coinDelta,
                lastCoinDelta = coinDelta,
            )
        }

        viewModelScope.launch {
            repository.recordAnswer(
                userId = currentUserId,
                isCorrect = isCorrect,
                isRewrite = true,
            )
        }
    }

    fun advanceToNextQuestion() {
        val state = _uiState.value
        if (!state.isCurrentAnswered) return

        if (state.isLastQuestion) {
            _uiState.update {
                it.copy(
                    examPhase = ExamPhase.RESULTS,
                    selectedOptionIndex = null,
                    rewriteInput = "",
                    rewriteSubmitted = false,
                    lastCoinDelta = 0,
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    currentIndex = it.currentIndex + 1,
                    selectedOptionIndex = null,
                    rewriteInput = "",
                    rewriteSubmitted = false,
                    lastCoinDelta = 0,
                )
            }
        }
    }

    fun saveUserProfile(name: String, grade: String) {
        val cleanName = name.trim().take(60)
        val cleanGrade = grade.trim().take(30)
        viewModelScope.launch {
            _uiState.update { it.copy(isSavingProfile = true, statusBannerMessage = null) }
            val result = repository.updateProfile(currentUserId, cleanName, cleanGrade)
            _uiState.update {
                if (result.isSuccess) {
                    it.copy(
                        isSavingProfile = false,
                        statusBannerMessage = "Profile saved!",
                        statusBannerIsError = false,
                    )
                } else {
                    it.copy(
                        isSavingProfile = false,
                        statusBannerMessage = result.exceptionOrNull()?.localizedMessage ?: "Failed to save profile.",
                        statusBannerIsError = true,
                    )
                }
            }
        }
    }

    fun selectPrizeCategory(category: PrizeCategory?) {
        _uiState.update { state ->
            val next = if (state.selectedPrizeCategory == category) null else category
            state.copy(selectedPrizeCategory = next)
        }
    }

    fun purchasePrize(prize: PrizeItem) {
        viewModelScope.launch {
            _uiState.update { it.copy(purchasingPrizeId = prize.id, statusBannerMessage = null) }
            val result = repository.purchasePrize(currentUserId, prize)
            _uiState.update {
                if (result.isSuccess) {
                    it.copy(
                        purchasingPrizeId = null,
                        statusBannerMessage = "${prize.title} added to your Collection!",
                        statusBannerIsError = false,
                    )
                } else {
                    it.copy(
                        purchasingPrizeId = null,
                        statusBannerMessage = result.exceptionOrNull()?.localizedMessage ?: "Purchase failed.",
                        statusBannerIsError = true,
                    )
                }
            }
        }
    }

    fun clearStatusBanner() {
        _uiState.update { it.copy(statusBannerMessage = null) }
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
                rewriteInput = "",
                rewriteSubmitted = false,
                showExitConfirmDialog = false,
            )
        }
    }

    fun returnHomeFromResults() {
        _uiState.update {
            it.copy(
                examPhase = ExamPhase.HOME,
                selectedOptionIndex = null,
                rewriteInput = "",
                rewriteSubmitted = false,
            )
        }
    }

    fun openCodex() {
        _uiState.update { it.copy(showCodexSheet = true) }
    }

    fun closeCodex() {
        _uiState.update { it.copy(showCodexSheet = false) }
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
