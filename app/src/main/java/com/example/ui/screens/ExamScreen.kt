package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.QuestionFormat
import com.example.model.VerbQuestion
import com.example.ui.theme.LocalSemanticColors
import com.example.viewmodel.VerbCraftUiState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExamScreen(
    uiState: VerbCraftUiState,
    onSelectOption: (Int) -> Unit,
    onUpdateRewriteInput: (String) -> Unit,
    onSubmitRewrite: () -> Unit,
    onNextQuestion: () -> Unit,
    onRequestExit: () -> Unit,
    onDismissExit: () -> Unit,
    onConfirmExit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val question = uiState.currentQuestion ?: return
    val semantic = LocalSemanticColors.current
    val scrollState = rememberScrollState()

    BackHandler(onBack = onRequestExit)

    LaunchedEffect(question.id) {
        scrollState.scrollTo(0)
    }

    val totalCount = uiState.examQuestions.size.coerceAtLeast(1)
    val progressTarget = (uiState.currentIndex + if (uiState.isCurrentAnswered) 1 else 0).toFloat() / totalCount
    val animatedProgress by animateFloatAsState(
        targetValue = progressTarget,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "exam_progress",
    )

    if (uiState.showExitConfirmDialog) {
        AlertDialog(
            onDismissRequest = onDismissExit,
            title = { Text(stringResource(R.string.exit_exam_title)) },
            text = { Text(stringResource(R.string.exit_exam_message)) },
            confirmButton = {
                TextButton(
                    onClick = onConfirmExit,
                    modifier = Modifier.testTag("confirm_exit_button"),
                ) {
                    Text(stringResource(R.string.leave), color = semantic.wrong)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismissExit,
                    modifier = Modifier.testTag("dismiss_exit_button"),
                ) {
                    Text(stringResource(R.string.stay))
                }
            },
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Top Header & Progress
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 680.dp)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(
                    onClick = onRequestExit,
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .testTag("exit_exam_button"),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Exit exam",
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }

                Surface(
                    color = if (uiState.isFixingExamSession) semantic.wrongContainer else MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(999.dp),
                ) {
                    Text(
                        text = if (uiState.isFixingExamSession) {
                            "Fixing • Q ${uiState.currentIndex + 1} / $totalCount"
                        } else {
                            "Q ${uiState.currentIndex + 1} / $totalCount"
                        },
                        style = MaterialTheme.typography.labelMedium,
                        color = if (uiState.isFixingExamSession) semantic.onWrongContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    if (uiState.currentStreak > 0) {
                        Surface(
                            color = semantic.brassContainer,
                            shape = RoundedCornerShape(999.dp),
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(3.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.LocalFireDepartment,
                                    contentDescription = "Current streak",
                                    tint = semantic.brass,
                                    modifier = Modifier.size(14.dp),
                                )
                                Text(
                                    text = "${uiState.currentStreak}",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = semantic.onBrassContainer,
                                )
                            }
                        }
                    }

                    Surface(
                        color = semantic.brassContainer,
                        shape = RoundedCornerShape(999.dp),
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.MonetizationOn,
                                contentDescription = "Session coins",
                                tint = semantic.brass,
                                modifier = Modifier.size(15.dp),
                            )
                            Text(
                                text = "+${uiState.sessionCoinsEarned}",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = semantic.onBrassContainer,
                            )
                        }
                    }
                }
            }

            LinearProgressIndicator(
                progress = { animatedProgress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(999.dp)),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }

        // Question Body
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .widthIn(max = 680.dp)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Surface(
                    color = semantic.brassContainer,
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = if (question.format == QuestionFormat.REWRITE) {
                            "REWRITE • +5 COINS"
                        } else {
                            "4-CHOICE • +1 COIN"
                        },
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = semantic.onBrassContainer,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    )
                }

                Surface(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = question.category.shortTitle.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    )
                }

                question.verbsInvolved.forEach { tag ->
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(
                            text = tag,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        )
                    }
                }
            }

            // Question Prompt / Editorial Card
            Card(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    if (question.format == QuestionFormat.CHOICE) {
                        StyledExamSentence(
                            question = question,
                            isAnswered = uiState.isCurrentAnswered,
                        )
                    } else {
                        Text(
                            text = "ORIGINAL SENTENCE",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        Text(
                            text = question.sentenceWithBlank,
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Surface(
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.65f),
                            shape = MaterialTheme.shapes.small,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.EditNote,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp),
                                )
                                Text(
                                    text = question.rewritePrompt,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                )
                            }
                        }
                    }
                }
            }

            // Input Area: 4-Choice vs Sentence Rewrite
            if (question.format == QuestionFormat.CHOICE) {
                ChoiceOptionsList(
                    question = question,
                    selectedOptionIndex = uiState.selectedOptionIndex,
                    isAnswered = uiState.isCurrentAnswered,
                    onSelectOption = onSelectOption,
                )
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    if (question.rewriteStarterHint.isNotBlank()) {
                        Surface(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Text(
                                text = "Starter guide: ${question.rewriteStarterHint}",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            )
                        }
                    }

                    OutlinedTextField(
                        value = uiState.rewriteInput,
                        onValueChange = onUpdateRewriteInput,
                        enabled = !uiState.isCurrentAnswered,
                        label = { Text("Your rewritten sentence") },
                        placeholder = { Text(question.rewriteStarterHint.ifBlank { "Type the complete rewritten sentence…" }) },
                        trailingIcon = {
                            if (!uiState.isCurrentAnswered && uiState.rewriteInput.isNotEmpty()) {
                                IconButton(
                                    onClick = { onUpdateRewriteInput("") },
                                    modifier = Modifier.minimumInteractiveComponentSize(),
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Clear,
                                        contentDescription = "Clear rewrite",
                                    )
                                }
                            }
                        },
                        minLines = 3,
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("rewrite_input"),
                    )

                    if (!uiState.isCurrentAnswered) {
                        Button(
                            onClick = onSubmitRewrite,
                            enabled = uiState.rewriteInput.isNotBlank(),
                            shape = MaterialTheme.shapes.medium,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                contentColor = MaterialTheme.colorScheme.onTertiary,
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("submit_rewrite_button"),
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.MonetizationOn,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(R.string.submit_rewrite),
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                    }
                }
            }

            // Diagnostic Feedback Card (Right Answer/Model Rewrite, Reason & English Meaning)
            AnimatedVisibility(
                visible = uiState.isCurrentAnswered,
                enter = fadeIn() + expandVertically(
                    animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                ),
            ) {
                val isUserCorrect = uiState.answeredQuestions.lastOrNull()?.isCorrect == true

                DiagnosticFeedbackCard(
                    question = question,
                    isUserCorrect = isUserCorrect,
                    coinReward = if (isUserCorrect) question.coinReward else 0,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        // Bottom Next Button once answered
        AnimatedVisibility(visible = uiState.isCurrentAnswered) {
            Surface(
                tonalElevation = 3.dp,
                shadowElevation = 6.dp,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Button(
                        onClick = onNextQuestion,
                        shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .widthIn(max = 680.dp)
                            .height(52.dp)
                            .testTag("next_question_button"),
                    ) {
                        Text(
                            text = if (uiState.isLastQuestion) {
                                stringResource(R.string.finish_exam)
                            } else {
                                stringResource(R.string.next_question)
                            },
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ChoiceOptionsList(
    question: VerbQuestion,
    selectedOptionIndex: Int?,
    isAnswered: Boolean,
    onSelectOption: (Int) -> Unit,
) {
    val semantic = LocalSemanticColors.current
    val letters = listOf("A", "B", "C", "D")

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        question.options.forEachIndexed { index, optionText ->
            val isSelected = selectedOptionIndex == index
            val isCorrectOption = index == question.correctIndex

            val containerColor = when {
                !isAnswered -> MaterialTheme.colorScheme.surface
                isCorrectOption -> semantic.correctContainer
                isSelected -> semantic.wrongContainer
                else -> MaterialTheme.colorScheme.surface.copy(alpha = 0.65f)
            }
            val borderColor = when {
                !isAnswered -> MaterialTheme.colorScheme.outlineVariant
                isCorrectOption -> semantic.correct
                isSelected -> semantic.wrong
                else -> MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
            }
            val badgeBg = when {
                !isAnswered -> MaterialTheme.colorScheme.surfaceVariant
                isCorrectOption -> semantic.correct
                isSelected -> semantic.wrong
                else -> MaterialTheme.colorScheme.surfaceVariant
            }
            val badgeTextColor = when {
                !isAnswered -> MaterialTheme.colorScheme.onSurfaceVariant
                isCorrectOption -> semantic.onCorrect
                isSelected -> semantic.onWrong
                else -> MaterialTheme.colorScheme.onSurfaceVariant
            }

            Card(
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = containerColor),
                border = BorderStroke(if (isAnswered && (isCorrectOption || isSelected)) 2.dp else 1.dp, borderColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .minimumInteractiveComponentSize()
                    .clip(MaterialTheme.shapes.medium)
                    .clickable(enabled = !isAnswered) { onSelectOption(index) }
                    .testTag("option_card_$index"),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Surface(
                        shape = CircleShape,
                        color = badgeBg,
                        modifier = Modifier.size(32.dp),
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = letters.getOrElse(index) { "" },
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = badgeTextColor,
                            )
                        }
                    }

                    Text(
                        text = optionText,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = if (isAnswered && isCorrectOption) FontWeight.SemiBold else FontWeight.Medium,
                        color = when {
                            !isAnswered -> MaterialTheme.colorScheme.onSurface
                            isCorrectOption -> semantic.onCorrectContainer
                            isSelected -> semantic.onWrongContainer
                            else -> MaterialTheme.colorScheme.onSurfaceVariant
                        },
                        modifier = Modifier.weight(1f),
                    )

                    if (isAnswered) {
                        if (isCorrectOption) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = "Right answer",
                                tint = semantic.correct,
                                modifier = Modifier.size(22.dp),
                            )
                        } else if (isSelected) {
                            Icon(
                                imageVector = Icons.Filled.Cancel,
                                contentDescription = "Incorrect choice",
                                tint = semantic.wrong,
                                modifier = Modifier.size(22.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StyledExamSentence(
    question: VerbQuestion,
    isAnswered: Boolean,
) {
    val semantic = LocalSemanticColors.current
    val raw = if (isAnswered) {
        question.filledSentence(question.correctOption)
    } else {
        question.sentenceWithBlank
    }

    if (!isAnswered) {
        val parts = raw.split("________")
        val annotated = buildAnnotatedString {
            parts.forEachIndexed { idx, chunk ->
                append(chunk)
                if (idx < parts.lastIndex) {
                    withStyle(
                        SpanStyle(
                            color = semantic.brass,
                            fontWeight = FontWeight.Bold,
                        ),
                    ) {
                        append(" [ ___ ] ")
                    }
                }
            }
        }
        Text(
            text = annotated,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
    } else {
        val tokens = question.correctOption.split(" / ").map { it.trim() }
        val templateParts = question.sentenceWithBlank.split("________")
        val annotated = buildAnnotatedString {
            templateParts.forEachIndexed { idx, chunk ->
                append(chunk)
                if (idx < templateParts.lastIndex) {
                    val fill = tokens.getOrElse(idx) { question.correctOption }
                    withStyle(
                        SpanStyle(
                            color = semantic.correct,
                            fontWeight = FontWeight.Bold,
                        ),
                    ) {
                        append(fill)
                    }
                }
            }
        }
        Text(
            text = annotated,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
fun DiagnosticFeedbackCard(
    question: VerbQuestion,
    isUserCorrect: Boolean,
    coinReward: Int,
    modifier: Modifier = Modifier,
) {
    val semantic = LocalSemanticColors.current

    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            width = 1.5.dp,
            color = if (isUserCorrect) semantic.correct else semantic.wrong,
        ),
        modifier = modifier
            .fillMaxWidth()
            .testTag("diagnostic_feedback_card"),
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Icon(
                        imageVector = if (isUserCorrect) Icons.Filled.CheckCircle else Icons.Filled.Cancel,
                        contentDescription = null,
                        tint = if (isUserCorrect) semantic.correct else semantic.wrong,
                        modifier = Modifier.size(22.dp),
                    )
                    Text(
                        text = if (isUserCorrect) {
                            stringResource(R.string.correct_label)
                        } else {
                            stringResource(R.string.incorrect_label)
                        },
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (isUserCorrect) semantic.correct else semantic.wrong,
                    )
                }

                if (coinReward > 0) {
                    Surface(
                        color = semantic.brassContainer,
                        shape = RoundedCornerShape(999.dp),
                    ) {
                        Text(
                            text = "+$coinReward Coin${if (coinReward > 1) "s" else ""}",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = semantic.onBrassContainer,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        )
                    }
                }
            }

            if (!isUserCorrect || question.format == QuestionFormat.REWRITE) {
                Surface(
                    color = semantic.correctContainer,
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            text = if (question.format == QuestionFormat.REWRITE) {
                                stringResource(R.string.model_rewrite_label).uppercase()
                            } else {
                                stringResource(R.string.right_answer_label).uppercase()
                            },
                            style = MaterialTheme.typography.labelSmall,
                            color = semantic.onCorrectContainer,
                        )
                        Text(
                            text = question.correctOption,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = semantic.onCorrectContainer,
                        )
                    }
                }
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Lightbulb,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp),
                    )
                    Text(
                        text = stringResource(R.string.reason_label),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
                Text(
                    text = question.reason,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

            Surface(
                color = semantic.brassContainer.copy(alpha = 0.65f),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Translate,
                            contentDescription = null,
                            tint = semantic.brass,
                            modifier = Modifier.size(18.dp),
                        )
                        Text(
                            text = stringResource(R.string.meaning_label),
                            style = MaterialTheme.typography.titleSmall,
                            color = semantic.onBrassContainer,
                        )
                    }
                    Text(
                        text = question.englishMeaning,
                        style = MaterialTheme.typography.bodyMedium,
                        color = semantic.onBrassContainer,
                    )
                }
            }
        }
    }
}
