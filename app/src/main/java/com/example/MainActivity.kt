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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.data.LocalProgressRepository
import com.example.model.ExamPhase
import com.example.model.MainTab
import com.example.ui.screens.CodexScreen
import com.example.ui.screens.CollectionScreen
import com.example.ui.screens.ExamScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.PrizeMenuScreen
import com.example.ui.screens.RatingScreen
import com.example.ui.screens.ResultsScreen
import com.example.ui.theme.VerbCraftTheme
import com.example.viewmodel.DataState
import com.example.viewmodel.VerbCraftViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VerbCraftTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    val context = LocalContext.current
    val repository = remember(context) { LocalProgressRepository(context) }
    val vm: VerbCraftViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                VerbCraftViewModel(
                    repository = repository,
                    currentUserId = "local_user",
                    defaultDisplayName = "",
                )
            }
        },
    )
    VerbCraftApp(viewModel = vm)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerbCraftApp(
    viewModel: VerbCraftViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val profileState by viewModel.profileState.collectAsStateWithLifecycle()
    val collectionState by viewModel.collectionState.collectAsStateWithLifecycle()

    val currentProfile = (profileState as? DataState.Success)?.data
    val showBottomBar = uiState.examPhase == ExamPhase.HOME

    if (uiState.showCodexSheet) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ModalBottomSheet(
            onDismissRequest = viewModel::closeCodex,
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            CodexScreen(
                uiState = uiState,
                onQueryChange = viewModel::updateCodexQuery,
                onSelectGroup = viewModel::selectCodexGroup,
                modifier = Modifier.fillMaxHeight(0.88f),
            )
        }
    }

    when (uiState.examPhase) {
        ExamPhase.ACTIVE -> {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.background,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                modifier = Modifier.fillMaxSize(),
            ) { innerPadding ->
                ExamScreen(
                    uiState = uiState,
                    onSelectOption = viewModel::submitChoiceAnswer,
                    onUpdateRewriteInput = viewModel::updateRewriteInput,
                    onSubmitRewrite = viewModel::submitRewriteAnswer,
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
                                        imageVector = if (uiState.activeTab == MainTab.EXAM) Icons.Filled.Quiz else Icons.Outlined.Quiz,
                                        contentDescription = stringResource(R.string.nav_exam),
                                    )
                                },
                                label = { Text(stringResource(R.string.nav_exam)) },
                                modifier = Modifier.testTag("nav_tab_exam"),
                            )
                            NavigationBarItem(
                                selected = uiState.activeTab == MainTab.RATING,
                                onClick = { viewModel.selectTab(MainTab.RATING) },
                                icon = {
                                    Icon(
                                        imageVector = if (uiState.activeTab == MainTab.RATING) Icons.Filled.Star else Icons.Outlined.StarOutline,
                                        contentDescription = stringResource(R.string.nav_rating),
                                    )
                                },
                                label = { Text(stringResource(R.string.nav_rating)) },
                                modifier = Modifier.testTag("nav_tab_rating"),
                            )
                            NavigationBarItem(
                                selected = uiState.activeTab == MainTab.PRIZES,
                                onClick = { viewModel.selectTab(MainTab.PRIZES) },
                                icon = {
                                    Icon(
                                        imageVector = if (uiState.activeTab == MainTab.PRIZES) Icons.Filled.ShoppingBag else Icons.Outlined.ShoppingBag,
                                        contentDescription = stringResource(R.string.nav_prizes),
                                    )
                                },
                                label = { Text(stringResource(R.string.nav_prizes)) },
                                modifier = Modifier.testTag("nav_tab_prizes"),
                            )
                            NavigationBarItem(
                                selected = uiState.activeTab == MainTab.COLLECTION,
                                onClick = { viewModel.selectTab(MainTab.COLLECTION) },
                                icon = {
                                    Icon(
                                        imageVector = if (uiState.activeTab == MainTab.COLLECTION) Icons.Filled.CollectionsBookmark else Icons.Outlined.CollectionsBookmark,
                                        contentDescription = stringResource(R.string.nav_collection),
                                    )
                                },
                                label = { Text(stringResource(R.string.nav_collection)) },
                                modifier = Modifier.testTag("nav_tab_collection"),
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
                            profile = currentProfile,
                            onSelectSuite = viewModel::selectSuite,
                            onSelectCategory = viewModel::selectCategory,
                            onSelectLength = viewModel::selectLength,
                            onStartExam = viewModel::startExam,
                            onOpenCodex = viewModel::openCodex,
                            onOpenRating = { viewModel.selectTab(MainTab.RATING) },
                            onOpenPrizes = { viewModel.selectTab(MainTab.PRIZES) },
                        )

                        MainTab.RATING -> RatingScreen(
                            uiState = uiState,
                            profileState = profileState,
                            onSaveProfile = viewModel::saveUserProfile,
                        )

                        MainTab.PRIZES -> PrizeMenuScreen(
                            uiState = uiState,
                            profileState = profileState,
                            collectionState = collectionState,
                            onSelectCategory = viewModel::selectPrizeCategory,
                            onBuyPrize = viewModel::purchasePrize,
                        )

                        MainTab.COLLECTION -> CollectionScreen(
                            collectionState = collectionState,
                            onOpenPrizeMenu = { viewModel.selectTab(MainTab.PRIZES) },
                        )
                    }
                }
            }
        }
    }
}
