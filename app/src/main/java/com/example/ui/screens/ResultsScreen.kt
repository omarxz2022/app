package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Replay
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.AnsweredQuestion
import com.example.model.QuestionFormat
import com.example.ui.theme.LocalSemanticColors
import com.example.viewmodel.VerbCraftUiState

@Composable
fun ResultsScreen(
    uiState: VerbCraftUiState,
    wrongQuestionCount: Int,
    onRetryMissed: () -> Unit,
    onStartFixingExam: () -> Unit,
    onNewExam: () -> Unit,
    onBackHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler(onBack = onBackHome)

    val semantic = LocalSemanticColors.current
    val total = uiState.answeredQuestions.size.coerceAtLeast(1)
    val correct = uiState.sessionCorrectCount
    val percent = (correct * 100) / total
    val missed = uiState.missedQuestions

    val rankTitle = when {
        percent >= 90 -> "Oxford Grammarian"
        percent >= 75 -> "Senior Syntactician"
        percent >= 55 -> "Syntax Challenger"
        else -> "Apprentice Scholar"
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentAlignment = Alignment.TopCenter,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 680.dp),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Card(
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.EmojiEvents,
                                contentDescription = null,
                                tint = semantic.brassContainer,
                                modifier = Modifier.size(22.dp),
                            )
                            Text(
                                text = rankTitle.uppercase(),
                                style = MaterialTheme.typography.labelMedium,
                                color = semantic.brassContainer,
                            )
                        }

                        Text(
                            text = "$percent% Mastery",
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )

                        Text(
                            text = "$correct out of $total correct • Best streak: ${uiState.bestStreakInSession}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f),
                        )

                        Surface(
                            color = semantic.brassContainer,
                            shape = RoundedCornerShape(999.dp),
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.MonetizationOn,
                                    contentDescription = null,
                                    tint = semantic.brass,
                                    modifier = Modifier.size(18.dp),
                                )
                                Text(
                                    text = "+${uiState.sessionCoinsEarned} Coins Earned This Exam",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = semantic.onBrassContainer,
                                )
                            }
                        }
                    }
                }
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    if (missed.isNotEmpty()) {
                        Button(
                            onClick = onRetryMissed,
                            shape = MaterialTheme.shapes.medium,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                contentColor = MaterialTheme.colorScheme.onTertiary,
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("retry_missed_button"),
                        ) {
                            Icon(Icons.Outlined.Replay, contentDescription = null, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${stringResource(R.string.retry_missed)} (${missed.size})",
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                    }

                    if (wrongQuestionCount > 0) {
                        OutlinedButton(
                            onClick = onStartFixingExam,
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("results_fixing_exam_button"),
                        ) {
                            Icon(Icons.Outlined.Replay, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Open All-Time Fixing Exam ($wrongQuestionCount Qs)")
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        OutlinedButton(
                            onClick = onNewExam,
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .testTag("new_exam_button"),
                        ) {
                            Icon(Icons.Outlined.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(stringResource(R.string.new_exam))
                        }

                        OutlinedButton(
                            onClick = onBackHome,
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .testTag("back_home_button"),
                        ) {
                            Icon(Icons.Outlined.Home, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(stringResource(R.string.back_home))
                        }
                    }
                }
            }

            if (missed.isNotEmpty()) {
                item {
                    Text(
                        text = "Missed Questions Review (${missed.size})",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 8.dp),
                    )
                }

                items(missed, key = { it.question.id }) { item ->
                    MissedQuestionReviewCard(item = item)
                }
            } else {
                item {
                    Surface(
                        color = semantic.correctContainer,
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            Text(
                                text = "Flawless Run!",
                                style = MaterialTheme.typography.titleLarge,
                                color = semantic.onCorrectContainer,
                            )
                            Text(
                                text = "Zero missed questions in this gauntlet. Spend your new coins in the Prize Menu!",
                                style = MaterialTheme.typography.bodyMedium,
                                color = semantic.onCorrectContainer,
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MissedQuestionReviewCard(item: AnsweredQuestion) {
    val semantic = LocalSemanticColors.current
    val q = item.question

    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                q.verbsInvolved.forEach { tag ->
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(6.dp),
                    ) {
                        Text(
                            text = tag,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        )
                    }
                }
            }

            Text(
                text = if (q.format == QuestionFormat.REWRITE) {
                    "${q.sentenceWithBlank}\n(${q.rewritePrompt})"
                } else {
                    q.filledSentence(q.correctOption)
                },
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Surface(
                color = semantic.wrongContainer,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = if (q.format == QuestionFormat.REWRITE) {
                        "Your rewrite: ${item.userAnswerDisplay}"
                    } else {
                        "Your pick: ${item.userAnswerDisplay}"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = semantic.onWrongContainer,
                    modifier = Modifier.padding(10.dp),
                )
            }

            Surface(
                color = semantic.correctContainer,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Right answer: ${q.correctOption}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = semantic.onCorrectContainer,
                    modifier = Modifier.padding(10.dp),
                )
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            Text(
                text = q.reason,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Surface(
                color = semantic.brassContainer.copy(alpha = 0.6f),
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Meaning: ${q.englishMeaning}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = semantic.onBrassContainer,
                    modifier = Modifier.padding(10.dp),
                )
            }
        }
    }
}
