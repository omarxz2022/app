package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PrizeCategory
import com.example.model.PrizeItem
import com.example.ui.art.ArtPattern
import com.example.ui.art.CollectableArtSpec
import com.example.ui.art.PrizeArtRegistry
import com.example.ui.art.drawCarShape
import com.example.ui.art.drawFoodShape
import com.example.ui.art.drawMotorbikeShape

@Composable
fun CollectableArtwork(
    prizeId: String,
    category: PrizeCategory,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    val spec = remember(prizeId, category) {
        PrizeArtRegistry.specFor(prizeId, category)
    }

    Box(
        modifier = modifier
            .semantics { this.contentDescription = contentDescription }
            .clip(RoundedCornerShape(14.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(spec.backdropTop, spec.backdropBottom),
                ),
            )
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.14f),
                shape = RoundedCornerShape(14.dp),
            ),
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawBackdropAccents(spec)
            when (category) {
                PrizeCategory.FOOD -> drawFoodShape(spec)
                PrizeCategory.MOTORBIKE -> drawMotorbikeShape(spec)
                PrizeCategory.CAR -> drawCarShape(spec)
            }
        }

        if (spec.badge.isNotBlank()) {
            Surface(
                color = Color.Black.copy(alpha = 0.56f),
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(5.dp),
            ) {
                Text(
                    text = spec.badge,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 9.sp,
                        fontWeight = FontWeight.ExtraBold,
                    ),
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp),
                )
            }
        }
    }
}

@Composable
fun CollectableArtwork(
    prize: PrizeItem,
    modifier: Modifier = Modifier,
) {
    CollectableArtwork(
        prizeId = prize.id,
        category = prize.category,
        contentDescription = "${prize.title} icon",
        modifier = modifier,
    )
}

private fun DrawScope.drawBackdropAccents(spec: CollectableArtSpec) {
    val w = size.width
    val h = size.height

    // Soft studio glow
    drawCircle(
        color = spec.primary.copy(alpha = 0.18f),
        radius = w * 0.44f,
        center = Offset(w * 0.5f, h * 0.46f),
    )

    when (spec.pattern) {
        ArtPattern.RACING_STRIPE -> {
            val stripe = Path().apply {
                moveTo(w * 0.62f, 0f)
                lineTo(w * 0.74f, 0f)
                lineTo(w * 0.44f, h)
                lineTo(w * 0.32f, h)
                close()
            }
            drawPath(stripe, spec.secondary.copy(alpha = 0.22f))
        }

        ArtPattern.CHECKERED -> {
            val cell = w * 0.08f
            for (r in 0..2) {
                for (c in 0..2) {
                    if ((r + c) % 2 == 0) {
                        drawRect(
                            color = Color.White.copy(alpha = 0.16f),
                            topLeft = Offset(w - (3 - c) * cell, r * cell),
                            size = androidx.compose.ui.geometry.Size(cell, cell),
                        )
                    }
                }
            }
        }

        ArtPattern.GOLD_LEAF -> {
            drawCircle(Color(0xFFFFD54F).copy(alpha = 0.45f), w * 0.025f, Offset(w * 0.80f, h * 0.20f))
            drawCircle(Color(0xFFFFD54F).copy(alpha = 0.35f), w * 0.018f, Offset(w * 0.86f, h * 0.28f))
            drawCircle(Color(0xFFFFD54F).copy(alpha = 0.30f), w * 0.016f, Offset(w * 0.20f, h * 0.80f))
        }

        ArtPattern.STEAM_WAVES -> {
            listOf(0.42f, 0.54f).forEach { xf ->
                drawLine(
                    color = Color.White.copy(alpha = 0.28f),
                    start = Offset(w * xf, h * 0.26f),
                    end = Offset(w * (xf + 0.03f), h * 0.14f),
                    strokeWidth = w * 0.022f,
                    cap = StrokeCap.Round,
                )
            }
        }

        ArtPattern.HERB_GARNISH -> {
            drawCircle(Color(0xFF66BB6A).copy(alpha = 0.38f), w * 0.022f, Offset(w * 0.82f, h * 0.22f))
            drawCircle(Color(0xFF81C784).copy(alpha = 0.32f), w * 0.018f, Offset(w * 0.75f, h * 0.18f))
        }

        ArtPattern.CARBON_AERO -> {
            drawLine(
                color = Color.White.copy(alpha = 0.08f),
                start = Offset(0f, h * 0.82f),
                end = Offset(w, h * 0.62f),
                strokeWidth = w * 0.03f,
            )
        }

        else -> Unit
    }
}
