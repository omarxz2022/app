package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.ExamPhase
import com.example.model.MainTab
import com.example.ui.screens.CodexScreen
import com.example.ui.screens.ExamScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ResultsScreen
import com.example.ui.theme.VerbCraftTheme
import com.example.viewmodel.VerbCraftViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VerbCraftTheme {
                VerbCraftApp()
            }
        }
    }
}

@Composable
fun VerbCraftApp(
    viewModel: VerbCraftViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val showBottomBar = uiState.examPhase == ExamPhase.HOME

    when (uiState.examPhase) {
        ExamPhase.ACTIVE -> {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.background,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                modifier = Modifier.fillMaxSize(),
            ) { innerPadding ->
                ExamScreen(
                    uiState = uiState,
                    onSelectOption = viewModel::submitAnswer,
                    onNextQuestion = viewModel::advanceToNextQuestion,
                    onRequestExit = viewModel::requestExitExam,
                    onDismissExit = viewModel::dismissExitDialog,
                    onConfirmExit = viewModel::confirmExitToHome,
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }

        ExamPhase.RESULTS -> {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.background,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                modifier = Modifier.fillMaxSize(),
            ) { innerPadding ->
                ResultsScreen(
                    uiState = uiState,
                    onRetryMissed = viewModel::retryMissedQuestions,
                    onNewExam = viewModel::startExam,
                    onBackHome = viewModel::returnHomeFromResults,
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }

        ExamPhase.HOME -> {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.background,
                contentWindowInsets = WindowInsets.safeDrawing,
                bottomBar = {
                    if (showBottomBar) {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                        ) {
                            NavigationBarItem(
                                selected = uiState.activeTab == MainTab.EXAM,
                                onClick = { viewModel.selectTab(MainTab.EXAM) },
                                icon = {
                                    Icon(
                                        imageVector = if (uiState.activeTab == MainTab.EXAM) {
                                            Icons.Filled.Quiz
                                        } else {
                                            Icons.Outlined.Quiz
                                        },
                                        contentDescription = stringResource(R.string.nav_exam),
                                    )
                                },
                                label = { Text(stringResource(R.string.nav_exam)) },
                                modifier = Modifier.testTag("nav_tab_exam"),
                            )
                            NavigationBarItem(
                                selected = uiState.activeTab == MainTab.CODEX,
                                onClick = { viewModel.selectTab(MainTab.CODEX) },
                                icon = {
                                    Icon(
                                        imageVector = if (uiState.activeTab == MainTab.CODEX) {
                                            Icons.Filled.MenuBook
                                        } else {
                                            Icons.Outlined.MenuBook
                                        },
                                        contentDescription = stringResource(R.string.nav_codex),
                                    )
                                },
                                label = { Text(stringResource(R.string.nav_codex)) },
                                modifier = Modifier.testTag("nav_tab_codex"),
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxSize(),
            ) { innerPadding ->
                AnimatedContent(
                    targetState = uiState.activeTab,
                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                    label = "tab_transition",
                    modifier = Modifier.padding(innerPadding),
                ) { tab ->
                    when (tab) {
                        MainTab.EXAM -> HomeScreen(
                            uiState = uiState,
                            onSelectCategory = viewModel::selectCategory,
                            onSelectLength = viewModel::selectLength,
                            onStartExam = viewModel::startExam,
                        )

                        MainTab.CODEX -> CodexScreen(
                            uiState = uiState,
                            onQueryChange = viewModel::updateCodexQuery,
                            onSelectGroup = viewModel::selectCodexGroup,
                        )
                    }
                }
            }
        }
    }
}
