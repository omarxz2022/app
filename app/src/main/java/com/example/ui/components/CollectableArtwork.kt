package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.model.PrizeCategory
import com.example.model.PrizeItem
import com.example.ui.art.ArtPattern
import com.example.ui.art.CollectableArtSpec
import com.example.ui.art.PrizeArtRegistry
import com.example.ui.art.drawCarShape
import com.example.ui.art.drawFoodShape
import com.example.ui.art.drawMotorbikeShape
import com.example.ui.art.exactHeroDrawableFor

private val STUDIO_TOP = Color(0xFFF7F3E9)
private val STUDIO_BOTTOM = Color(0xFFEAE3D2)

@Composable
fun CollectableArtwork(
    prizeId: String,
    category: PrizeCategory,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    val exactHeroDrawable = remember(prizeId) { exactHeroDrawableFor(prizeId) }
    val spec = remember(prizeId, category) {
        PrizeArtRegistry.specFor(prizeId, category)
    }

    Box(
        modifier = modifier
            .semantics { this.contentDescription = contentDescription }
            .clip(RoundedCornerShape(14.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(STUDIO_TOP, STUDIO_BOTTOM),
                ),
            )
            .border(
                width = 1.dp,
                color = Color(0x1F1E2228),
                shape = RoundedCornerShape(14.dp),
            ),
    ) {
        if (exactHeroDrawable != null) {
            Image(
                painter = painterResource(id = exactHeroDrawable),
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawStudioBackdrop(spec)
                when (category) {
                    PrizeCategory.FOOD -> drawFoodShape(spec)
                    PrizeCategory.MOTORBIKE -> drawMotorbikeShape(spec)
                    PrizeCategory.CAR -> drawCarShape(spec)
                }
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

private fun DrawScope.drawStudioBackdrop(spec: CollectableArtSpec) {
    val w = size.width
    val h = size.height

    // Subtle soft studio spotlight halo behind product
    drawCircle(
        color = spec.primary.copy(alpha = 0.08f),
        radius = w * 0.42f,
        center = Offset(w * 0.5f, h * 0.48f),
    )

    // Subtle studio floor horizon line
    drawLine(
        color = Color(0x14000000),
        start = Offset(0f, h * 0.72f),
        end = Offset(w, h * 0.72f),
        strokeWidth = 1f,
    )

    if (spec.pattern == ArtPattern.RACING_STRIPE) {
        val stripe = Path().apply {
            moveTo(w * 0.68f, 0f)
            lineTo(w * 0.78f, 0f)
            lineTo(w * 0.52f, h)
            lineTo(w * 0.42f, h)
            close()
        }
        drawPath(stripe, spec.secondary.copy(alpha = 0.11f))
    }
}
