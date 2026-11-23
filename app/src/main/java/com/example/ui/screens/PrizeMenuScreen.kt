package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.NoFood
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.PrizeCatalog
import com.example.model.CollectableItem
import com.example.model.PrizeCategory
import com.example.model.PrizeItem
import com.example.model.UserProfile
import com.example.ui.theme.LocalSemanticColors
import com.example.viewmodel.DataState
import com.example.viewmodel.VerbCraftUiState

@Composable
fun PrizeMenuScreen(
    uiState: VerbCraftUiState,
    profileState: DataState<UserProfile?>,
    collectionState: DataState<List<CollectableItem>>,
    onSelectCategory: (PrizeCategory?) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onToggleAffordableOnly: () -> Unit,
    onBuyPrize: (PrizeItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val semantic = LocalSemanticColors.current
    val coins = (profileState as? DataState.Success)?.data?.coins ?: 0L
    val ownedCounts = ((collectionState as? DataState.Success)?.data ?: emptyList())
        .associate { it.prizeId to it.quantity }

    val query = uiState.prizeSearchQuery.trim().lowercase()
    val visibleCategories = remember(uiState.fastingMode) {
        if (uiState.fastingMode) {
            PrizeCategory.entries.filterNot { it == PrizeCategory.FOOD }
        } else {
            PrizeCategory.entries
        }
    }
    val categoryCounts = remember {
        PrizeCategory.entries.associateWith { cat ->
            PrizeCatalog.items.count { it.category == cat }
        }
    }
    val totalCatalogCount = remember(uiState.fastingMode) {
        if (uiState.fastingMode) {
            PrizeCatalog.items.count { it.category != PrizeCategory.FOOD }
        } else {
            PrizeCatalog.items.size
        }
    }

    val visibleItems = remember(
        uiState.selectedPrizeCategory,
        query,
        uiState.prizeAffordableOnly,
        uiState.fastingMode,
        coins,
    ) {
        PrizeCatalog.items.filter { item ->
            val allowedInFasting = !uiState.fastingMode || item.category != PrizeCategory.FOOD
            val matchesCat = uiState.selectedPrizeCategory == null || item.category == uiState.selectedPrizeCategory
            val matchesQuery = query.isEmpty() ||
                item.title.lowercase().contains(query) ||
                item.subtitle.lowercase().contains(query) ||
                item.category.label.lowercase().contains(query)
            val matchesAfford = !uiState.prizeAffordableOnly || coins >= item.price
            allowedInFasting && matchesCat && matchesQuery && matchesAfford
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
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = stringResource(R.string.nav_prizes),
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                        Text(
                            text = "+1 coin per choice • +5 per rewrite • $totalCatalogCount unique prizes",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }

                    Surface(
                        color = semantic.brassContainer,
                        shape = RoundedCornerShape(999.dp),
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.MonetizationOn,
                                contentDescription = "Available coins",
                                tint = semantic.brass,
                                modifier = Modifier.size(18.dp),
                            )
                            Text(
                                text = "$coins",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = semantic.onBrassContainer,
                                modifier = Modifier.testTag("prize_wallet_coins"),
                            )
                        }
                    }
                }
            }

            if (uiState.statusBannerMessage != null) {
                item {
                    Surface(
                        color = if (uiState.statusBannerIsError) semantic.wrongContainer else semantic.correctContainer,
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = uiState.statusBannerMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = if (uiState.statusBannerIsError) semantic.onWrongContainer else semantic.onCorrectContainer,
                            modifier = Modifier.padding(12.dp),
                        )
                    }
                }
            }

            if (uiState.fastingMode) {
                item {
                    Surface(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("prize_fasting_banner"),
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 9.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.NoFood,
                                contentDescription = "Fasting mode enabled",
                                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.size(18.dp),
                            )
                            Text(
                                text = "Fasting Mode is ON — all food items are hidden.",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                            )
                        }
                    }
                }
            }

            item {
                OutlinedTextField(
                    value = uiState.prizeSearchQuery,
                    onValueChange = onSearchQueryChange,
                    placeholder = {
                        Text(
                            if (uiState.fastingMode) {
                                "Search normal & racing bikes or cars…"
                            } else {
                                "Search food, normal & racing bikes or cars…"
                            },
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search prizes",
                        )
                    },
                    trailingIcon = {
                        if (uiState.prizeSearchQuery.isNotEmpty()) {
                            IconButton(
                                onClick = { onSearchQueryChange("") },
                                modifier = Modifier.minimumInteractiveComponentSize(),
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Clear search",
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("prize_search_input"),
                )
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    item {
                        FilterChip(
                            selected = uiState.selectedPrizeCategory == null,
                            onClick = { onSelectCategory(null) },
                            label = { Text("All ($totalCatalogCount)") },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                            ),
                            modifier = Modifier.minimumInteractiveComponentSize(),
                        )
                    }
                    items(visibleCategories) { cat ->
                        FilterChip(
                            selected = uiState.selectedPrizeCategory == cat,
                            onClick = { onSelectCategory(cat) },
                            label = { Text("${cat.label} (${categoryCounts[cat] ?: 0})") },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                            ),
                            modifier = Modifier.minimumInteractiveComponentSize(),
                        )
                    }
                    item {
                        FilterChip(
                            selected = uiState.prizeAffordableOnly,
                            onClick = onToggleAffordableOnly,
                            label = { Text("Can Afford") },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = semantic.brass,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                            ),
                            modifier = Modifier
                                .minimumInteractiveComponentSize()
                                .testTag("prize_affordable_chip"),
                        )
                    }
                }
            }

            if (visibleItems.isEmpty()) {
                item {
                    Card(
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text(
                                text = "No matching prizes",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                            Text(
                                text = "Clear your search or turn off 'Can Afford' to browse all $totalCatalogCount prizes.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }
            } else {
                items(visibleItems, key = { it.id }) { prize ->
                    val ownedQty = ownedCounts[prize.id] ?: 0L
                    val canAfford = coins >= prize.price
                    val isBuying = uiState.purchasingPrizeId == prize.id

                    PrizeStoreCard(
                        prize = prize,
                        coins = coins,
                        ownedQty = ownedQty,
                        canAfford = canAfford,
                        isBuying = isBuying,
                        onBuy = { onBuyPrize(prize) },
                    )
                }
            }
        }
    }
}

@Composable
private fun PrizeStoreCard(
    prize: PrizeItem,
    coins: Long,
    ownedQty: Long,
    canAfford: Boolean,
    isBuying: Boolean,
    onBuy: () -> Unit,
) {
    val semantic = LocalSemanticColors.current

    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("prize_card_${prize.id}"),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Image(
                painter = painterResource(id = prize.imageRes),
                contentDescription = "${prize.title} picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(86.dp)
                    .clip(RoundedCornerShape(14.dp)),
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(6.dp),
                    ) {
                        Text(
                            text = prize.category.label.uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp),
                        )
                    }
                    if (ownedQty > 0L) {
                        Surface(
                            color = semantic.correctContainer,
                            shape = RoundedCornerShape(6.dp),
                        ) {
                            Text(
                                text = "Owned ×$ownedQty",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = semantic.onCorrectContainer,
                                modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp),
                            )
                        }
                    }
                }

                Text(
                    text = prize.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = prize.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.MonetizationOn,
                            contentDescription = null,
                            tint = semantic.brass,
                            modifier = Modifier.size(16.dp),
                        )
                        Text(
                            text = "${prize.price} coins",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = semantic.brass,
                        )
                    }

                    Button(
                        onClick = onBuy,
                        enabled = canAfford && !isBuying,
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("buy_prize_${prize.id}"),
                    ) {
                        if (isBuying) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(16.dp),
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.ShoppingBag,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = if (canAfford) "Buy" else "Need ${prize.price - coins}",
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                    }
                }
            }
        }
    }
}
