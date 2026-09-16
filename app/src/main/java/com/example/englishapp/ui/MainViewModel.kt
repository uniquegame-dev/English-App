package com.example.englishapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.englishapp.data.LanguageMode
import com.example.englishapp.data.PreferencesManager
import com.example.englishapp.data.QuizQuestion
import com.example.englishapp.data.SentenceProgress
import com.example.englishapp.data.SentenceRepository
import com.example.englishapp.data.SentenceType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class AppDestination {
    ONBOARDING,
    HOME,
    SENTENCE_STRUCTURE
}

enum class SentenceTab {
    LEARN,
    PRACTICE
}

data class QuizState(
    val currentIndex: Int = 0,
    val selectedOption: SentenceType? = null,
    val hasAnsweredCurrent: Boolean = false,
    val isCorrect: Boolean = false,
    val currentScore: Int = 0,
    val isFinished: Boolean = false
) {
    val currentQuestion: QuizQuestion?
        get() = if (currentIndex in SentenceRepository.practiceQuestions.indices) {
            SentenceRepository.practiceQuestions[currentIndex]
        } else null

    val totalQuestions: Int = SentenceRepository.practiceQuestions.size
}

data class MainUiState(
    val currentDestination: AppDestination = AppDestination.ONBOARDING,
    val username: String = "",
    val languageMode: LanguageMode = LanguageMode.ENGLISH,
    val sentenceProgress: SentenceProgress = SentenceProgress(),
    val currentSentenceTab: SentenceTab = SentenceTab.LEARN,
    val quizState: QuizState = QuizState()
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = PreferencesManager(application.applicationContext)

    private val _uiState = MutableStateFlow(
        MainUiState(
            currentDestination = if (prefs.getUsername().isNotBlank()) AppDestination.HOME else AppDestination.ONBOARDING,
            username = prefs.getUsername(),
            languageMode = prefs.getLanguageMode(),
            sentenceProgress = prefs.getSentenceProgress()
        )
    )
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun toggleLanguageMode() {
        val nextMode = if (_uiState.value.languageMode == LanguageMode.ENGLISH) {
            LanguageMode.HINGLISH
        } else {
            LanguageMode.ENGLISH
        }
        setLanguageMode(nextMode)
    }

    fun setLanguageMode(mode: LanguageMode) {
        prefs.saveLanguageMode(mode)
        _uiState.update { it.copy(languageMode = mode) }
    }

    fun saveNameAndContinue(name: String) {
        val trimmed = name.trim()
        if (trimmed.isNotBlank()) {
            prefs.saveUsername(trimmed)
            _uiState.update {
                it.copy(
                    username = trimmed,
                    currentDestination = AppDestination.HOME
                )
            }
        }
    }

    fun navigateToHome() {
        _uiState.update { it.copy(currentDestination = AppDestination.HOME) }
    }

    fun openSentenceStructure(initialTab: SentenceTab = SentenceTab.LEARN) {
        _uiState.update {
            it.copy(
                currentDestination = AppDestination.SENTENCE_STRUCTURE,
                currentSentenceTab = initialTab
            )
        }
    }

    fun switchSentenceTab(tab: SentenceTab) {
        _uiState.update { it.copy(currentSentenceTab = tab) }
    }

    fun selectQuizOption(option: SentenceType) {
        val currentQuiz = _uiState.value.quizState
        if (currentQuiz.hasAnsweredCurrent || currentQuiz.isFinished) return

        val question = currentQuiz.currentQuestion ?: return
        val isCorrect = (option == question.correctType)
        val newScore = if (isCorrect) currentQuiz.currentScore + 1 else currentQuiz.currentScore

        _uiState.update { state ->
            state.copy(
                quizState = currentQuiz.copy(
                    selectedOption = option,
                    hasAnsweredCurrent = true,
                    isCorrect = isCorrect,
                    currentScore = newScore
                )
            )
        }
    }

    fun nextQuestion() {
        val currentQuiz = _uiState.value.quizState
        val nextIndex = currentQuiz.currentIndex + 1

        if (nextIndex >= currentQuiz.totalQuestions) {
            // Finished quiz! Save progress
            val finalScore = currentQuiz.currentScore
            val total = currentQuiz.totalQuestions
            prefs.saveSentenceProgress(finalScore, total)
            val updatedProgress = prefs.getSentenceProgress()

            _uiState.update { state ->
                state.copy(
                    sentenceProgress = updatedProgress,
                    quizState = currentQuiz.copy(
                        isFinished = true,
                        hasAnsweredCurrent = false,
                        selectedOption = null
                    )
                )
            }
        } else {
            _uiState.update { state ->
                state.copy(
                    quizState = currentQuiz.copy(
                        currentIndex = nextIndex,
                        selectedOption = null,
                        hasAnsweredCurrent = false,
                        isCorrect = false
                    )
                )
            }
        }
    }

    fun restartQuiz() {
        _uiState.update { state ->
            state.copy(
                quizState = QuizState()
            )
        }
    }
}
