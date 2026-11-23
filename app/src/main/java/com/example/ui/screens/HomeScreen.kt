package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.VerbQuestionRepository
import com.example.model.ExamCategory
import com.example.model.ExamLength
import com.example.model.ExamSuite
import com.example.model.UserProfile
import com.example.ui.theme.LocalSemanticColors
import com.example.viewmodel.VerbCraftUiState
import java.util.Locale

@Composable
fun HomeScreen(
    uiState: VerbCraftUiState,
    profile: UserProfile?,
    onSelectSuite: (ExamSuite) -> Unit,
    onSelectCategory: (ExamCategory) -> Unit,
    onSelectLength: (ExamLength) -> Unit,
    onStartExam: () -> Unit,
    onOpenCodex: () -> Unit,
    onOpenRating: () -> Unit,
    onOpenPrizes: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val semantic = LocalSemanticColors.current
    val availableCount = VerbQuestionRepository.countForSuite(
        suite = uiState.selectedSuite,
        category = uiState.selectedCategory,
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 680.dp),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Top Status Strip: Coins, Rating, and Verb Codex
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Surface(
                        color = semantic.brassContainer,
                        shape = RoundedCornerShape(999.dp),
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .clip(RoundedCornerShape(999.dp))
                            .clickable(onClick = onOpenPrizes)
                            .testTag("home_coins_pill"),
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.MonetizationOn,
                                contentDescription = "Coins balance",
                                tint = semantic.brass,
                                modifier = Modifier.size(18.dp),
                            )
                            Text(
                                text = "${profile?.coins ?: 0} Coins",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = semantic.onBrassContainer,
                            )
                        }
                    }

                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(999.dp),
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .clip(RoundedCornerShape(999.dp))
                            .clickable(onClick = onOpenRating)
                            .testTag("home_rating_pill"),
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Star,
                                contentDescription = "My rating out of 10",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp),
                            )
                            val ratingText = if ((profile?.totalAttempted ?: 0L) > 0L) {
                                String.format(Locale.US, "%.1f/10", profile?.ratingOutOfTen ?: 0.0)
                            } else {
                                "Unrated"
                            }
                            Text(
                                text = ratingText,
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    OutlinedButton(
                        onClick = onOpenCodex,
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(999.dp),
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("open_codex_button"),
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.MenuBook,
                            contentDescription = "Open Verb Codex",
                            modifier = Modifier.size(16.dp),
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Codex",
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                }
            }

            // Editorial Hero Banner
            item {
                Card(
                    shape = MaterialTheme.shapes.large,
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(176.dp),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_oxford_banner_1795177415811),
                            contentDescription = "VerbCraft academic banner illustration",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize(),
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color(0x550E1A2B), Color(0xEA102036)),
                                    ),
                                ),
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(18.dp),
                            verticalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Surface(
                                color = Color(0x33FFFFFF),
                                shape = RoundedCornerShape(999.dp),
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.School,
                                        contentDescription = null,
                                        tint = Color(0xFFFBE0AD),
                                        modifier = Modifier.size(15.dp),
                                    )
                                    Text(
                                        text = "C2 GRAMMAR & REWARDS STUDIO",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color(0xFFFBE0AD),
                                    )
                                }
                            }
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = stringResource(R.string.app_name),
                                    style = MaterialTheme.typography.headlineLarge,
                                    color = Color.White,
                                )
                                Text(
                                    text = stringResource(R.string.hero_subtitle),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFFE2E8F0),
                                )
                            }
                        }
                    }
                }
            }

            // Suite Picker
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Choose Exam Arena",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    ExamSuite.entries.forEach { suite ->
                        val isSelected = uiState.selectedSuite == suite
                        Card(
                            shape = MaterialTheme.shapes.medium,
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected) {
                                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.58f)
                                } else {
                                    MaterialTheme.colorScheme.surface
                                },
                            ),
                            border = BorderStroke(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.outlineVariant
                                },
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .minimumInteractiveComponentSize()
                                .clip(MaterialTheme.shapes.medium)
                                .clickable { onSelectSuite(suite) }
                                .testTag("suite_card_${suite.name.lowercase()}"),
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f),
                                    verticalArrangement = Arrangement.spacedBy(4.dp),
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    ) {
                                        Text(
                                            text = suite.title,
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface,
                                        )
                                        Surface(
                                            color = semantic.brassContainer,
                                            shape = RoundedCornerShape(6.dp),
                                        ) {
                                            Text(
                                                text = suite.badge,
                                                style = MaterialTheme.typography.labelSmall,
                                                color = semantic.onBrassContainer,
                                                modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp),
                                            )
                                        }
                                    }
                                    Text(
                                        text = suite.subtitle,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
                                }
                                if (isSelected) {
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Icon(
                                        imageVector = Icons.Filled.CheckCircle,
                                        contentDescription = "Selected exam arena",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(22.dp),
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Category Filter Chips within Suite
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Topic Focus",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(uiState.availableCategories) { category ->
                            val isSelected = uiState.selectedCategory == category
                            val catCount = VerbQuestionRepository.countForSuite(uiState.selectedSuite, category)
                            FilterChip(
                                selected = isSelected,
                                onClick = { onSelectCategory(category) },
                                label = {
                                    Text(
                                        text = "${category.shortTitle} ($catCount)",
                                        style = MaterialTheme.typography.labelLarge,
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                                ),
                                modifier = Modifier
                                    .minimumInteractiveComponentSize()
                                    .testTag("category_chip_${category.name.lowercase()}"),
                            )
                        }
                    }
                }
            }

            // Exam Length
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Questions Per Session",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(ExamLength.entries) { length ->
                            val isSelected = uiState.selectedLength == length
                            val sessionCount = if (length == ExamLength.ALL) {
                                minOf(100, availableCount)
                            } else {
                                minOf(length.count, availableCount)
                            }
                            if (length != ExamLength.TWENTY || availableCount >= 12) {
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { onSelectLength(length) },
                                    label = {
                                        Text(
                                            text = "$sessionCount Qs",
                                            style = MaterialTheme.typography.labelLarge,
                                        )
                                    },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                                    ),
                                    modifier = Modifier
                                        .minimumInteractiveComponentSize()
                                        .testTag("length_chip_${length.name.lowercase()}"),
                                )
                            }
                        }
                    }
                }
            }

            // Start CTA
            item {
                Button(
                    onClick = onStartExam,
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .testTag("start_exam_button"),
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AutoStories,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(R.string.start_exam),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
