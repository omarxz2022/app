package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.OfflinePin
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.UserProfile
import com.example.ui.theme.LocalSemanticColors
import com.example.viewmodel.DataState
import com.example.viewmodel.VerbCraftUiState
import java.util.Locale

@Composable
fun RatingScreen(
    uiState: VerbCraftUiState,
    profileState: DataState<UserProfile?>,
    onSaveProfile: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val semantic = LocalSemanticColors.current

    val profile = (profileState as? DataState.Success)?.data
    var nameInput by rememberSaveable { mutableStateOf("") }
    var gradeInput by rememberSaveable { mutableStateOf("") }
    var initialized by remember { mutableStateOf(false) }

    LaunchedEffect(profile?.userId, profile?.displayName, profile?.grade) {
        if (profile != null && !initialized) {
            nameInput = profile.displayName
            gradeInput = profile.grade
            initialized = true
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 680.dp),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(R.string.nav_rating),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Surface(
                        color = semantic.correctContainer,
                        shape = RoundedCornerShape(999.dp),
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.OfflinePin,
                                contentDescription = "Offline local storage",
                                tint = semantic.correct,
                                modifier = Modifier.size(16.dp),
                            )
                            Text(
                                text = "100% Offline",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = semantic.onCorrectContainer,
                            )
                        }
                    }
                }
            }

            if (profileState is DataState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }
            } else {
                val attempted = profile?.totalAttempted ?: 0L
                val correct = profile?.totalCorrect ?: 0L
                val ratingValue = profile?.ratingOutOfTen ?: 0.0
                val ratingDisplay = if (attempted > 0L) {
                    String.format(Locale.US, "%.1f", ratingValue)
                } else {
                    "—"
                }

                // 1–10 Rating Score Hero Card
                item {
                    Card(
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("rating_hero_card"),
                    ) {
                        Column(
                            modifier = Modifier.padding(22.dp),
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
                                        imageVector = Icons.Outlined.Star,
                                        contentDescription = null,
                                        tint = semantic.brassContainer,
                                        modifier = Modifier.size(22.dp),
                                    )
                                    Text(
                                        text = (profile?.scholarRank ?: "Unrated Challenger").uppercase(),
                                        style = MaterialTheme.typography.labelMedium,
                                        color = semantic.brassContainer,
                                    )
                                }
                                Surface(
                                    color = semantic.brassContainer,
                                    shape = RoundedCornerShape(999.dp),
                                ) {
                                    Text(
                                        text = "SCALE 1 TO 10",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = semantic.onBrassContainer,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    )
                                }
                            }

                            Row(
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                            ) {
                                Text(
                                    text = ratingDisplay,
                                    style = MaterialTheme.typography.displayMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.testTag("rating_score_value"),
                                )
                                Text(
                                    text = "/ 10",
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                                    modifier = Modifier.padding(bottom = 6.dp),
                                )
                            }

                            val progress = if (attempted > 0L) (ratingValue / 10.0).toFloat().coerceIn(0f, 1f) else 0f
                            LinearProgressIndicator(
                                progress = { progress },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(10.dp)
                                    .clip(RoundedCornerShape(999.dp)),
                                color = semantic.brassContainer,
                                trackColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f),
                            )

                            Text(
                                text = if (attempted > 0L) {
                                    "$correct right out of $attempted solved (${profile?.accuracyPercent ?: 0}% accuracy)"
                                } else {
                                    "Solve your first exam question to unlock your 1–10 rating."
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f),
                            )
                        }
                    }
                }

                // Breakdown Stats Card
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        StatMiniCard(
                            icon = Icons.Outlined.CheckCircle,
                            label = "Choice Right",
                            value = "${profile?.choiceCorrect ?: 0}",
                            sub = "+1🪙 each",
                            modifier = Modifier.weight(1f),
                        )
                        StatMiniCard(
                            icon = Icons.Outlined.EditNote,
                            label = "Rewrite Right",
                            value = "${profile?.rewriteCorrect ?: 0}",
                            sub = "+5🪙 each",
                            modifier = Modifier.weight(1f),
                        )
                        StatMiniCard(
                            icon = Icons.Outlined.Grade,
                            label = "Coins",
                            value = "${profile?.coins ?: 0}",
                            sub = "Balance",
                            modifier = Modifier.weight(1f),
                        )
                    }
                }

                // Scholar Name & Grade Form
                item {
                    Card(
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp),
                        ) {
                            Text(
                                text = "Learner Identity (Saved on Device)",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                            )

                            OutlinedTextField(
                                value = nameInput,
                                onValueChange = { if (it.length <= 60) nameInput = it },
                                label = { Text("Your Name") },
                                placeholder = { Text("Enter your name") },
                                leadingIcon = {
                                    Icon(Icons.Outlined.Badge, contentDescription = null)
                                },
                                singleLine = true,
                                shape = MaterialTheme.shapes.medium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("profile_name_input"),
                            )

                            OutlinedTextField(
                                value = gradeInput,
                                onValueChange = { if (it.length <= 30) gradeInput = it },
                                label = { Text("Your Grade / Level") },
                                placeholder = { Text("e.g. Grade 11, C2 Prep, Year 2") },
                                leadingIcon = {
                                    Icon(Icons.Outlined.School, contentDescription = null)
                                },
                                singleLine = true,
                                shape = MaterialTheme.shapes.medium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("profile_grade_input"),
                            )

                            if (uiState.statusBannerMessage != null) {
                                Surface(
                                    color = if (uiState.statusBannerIsError) semantic.wrongContainer else semantic.correctContainer,
                                    shape = MaterialTheme.shapes.small,
                                    modifier = Modifier.fillMaxWidth(),
                                ) {
                                    Text(
                                        text = uiState.statusBannerMessage,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = if (uiState.statusBannerIsError) semantic.onWrongContainer else semantic.onCorrectContainer,
                                        modifier = Modifier.padding(10.dp),
                                    )
                                }
                            }

                            Button(
                                onClick = { onSaveProfile(nameInput, gradeInput) },
                                enabled = !uiState.isSavingProfile,
                                shape = MaterialTheme.shapes.medium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .testTag("save_profile_button"),
                            ) {
                                if (uiState.isSavingProfile) {
                                    CircularProgressIndicator(
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        strokeWidth = 2.dp,
                                        modifier = Modifier.size(18.dp),
                                    )
                                } else {
                                    Icon(Icons.Outlined.Save, contentDescription = null, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Save Name & Grade")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatMiniCard(
    icon: ImageVector,
    label: String,
    value: String,
    sub: String,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp),
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = sub,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
    }
}
