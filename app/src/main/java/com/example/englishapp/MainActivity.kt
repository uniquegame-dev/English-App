package com.example.englishapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.englishapp.ui.AppDestination
import com.example.englishapp.ui.MainViewModel
import com.example.englishapp.ui.screens.HomeScreen
import com.example.englishapp.ui.screens.OnboardingScreen
import com.example.englishapp.ui.screens.SentenceStructureScreen
import com.example.englishapp.ui.theme.EnglishAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EnglishAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    EnglishAppMain(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun EnglishAppMain(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    // Handle system back press
    BackHandler(enabled = uiState.currentDestination == AppDestination.SENTENCE_STRUCTURE) {
        viewModel.navigateToHome()
    }

    AnimatedContent(
        targetState = uiState.currentDestination,
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        },
        label = "screen_transition",
        modifier = modifier.fillMaxSize()
    ) { destination ->
        when (destination) {
            AppDestination.ONBOARDING -> {
                OnboardingScreen(
                    currentLanguageMode = uiState.languageMode,
                    onLanguageToggle = { viewModel.setLanguageMode(it) },
                    onContinue = { viewModel.saveNameAndContinue(it) },
                    initialName = uiState.username
                )
            }
            AppDestination.HOME -> {
                HomeScreen(
                    username = uiState.username,
                    currentLanguageMode = uiState.languageMode,
                    sentenceProgress = uiState.sentenceProgress,
                    onLanguageToggle = { viewModel.setLanguageMode(it) },
                    onOpenSentenceStructure = { viewModel.openSentenceStructure() }
                )
            }
            AppDestination.SENTENCE_STRUCTURE -> {
                SentenceStructureScreen(
                    currentLanguageMode = uiState.languageMode,
                    currentTab = uiState.currentSentenceTab,
                    quizState = uiState.quizState,
                    onLanguageToggle = { viewModel.setLanguageMode(it) },
                    onTabSelected = { viewModel.switchSentenceTab(it) },
                    onBack = { viewModel.navigateToHome() },
                    onOptionSelected = { viewModel.selectQuizOption(it) },
                    onNextQuestion = { viewModel.nextQuestion() },
                    onRestartQuiz = { viewModel.restartQuiz() }
                )
            }
        }
    }
}
