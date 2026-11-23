package com.example.ui.art

import androidx.compose.ui.graphics.Color
import com.example.model.PrizeCategory
import com.example.model.PrizeItem

object PrizeArtRegistry {
    val allSpecs: Map<String, CollectableArtSpec> by lazy {
        foodArtSpecs + motorbikeArtSpecs + carArtSpecs
    }

    fun specFor(prize: PrizeItem): CollectableArtSpec {
        return allSpecs[prize.id] ?: fallbackSpec(prize.category)
    }

    fun specFor(prizeId: String, category: PrizeCategory): CollectableArtSpec {
        return allSpecs[prizeId] ?: fallbackSpec(category)
    }

    private fun fallbackSpec(category: PrizeCategory): CollectableArtSpec {
        return when (category) {
            PrizeCategory.FOOD -> CollectableArtSpec(
                CollectableShape.TASTING_PLATE,
                Color(0xFFECEFF4), Color(0xFFD9A648), Color(0xFF4F8755),
                Color(0xFF1F2735), Color(0xFF0D1119), ArtPattern.NONE, ""
            )
            PrizeCategory.MOTORBIKE -> CollectableArtSpec(
                CollectableShape.SUPERSPORT_FAIRING,
                Color(0xFFD32F2F), Color(0xFF212121), Color(0xFFECEFF1),
                Color(0xFF361214), Color(0xFF180708), ArtPattern.NONE, ""
            )
            PrizeCategory.CAR -> CollectableArtSpec(
                CollectableShape.MID_ENGINE_SUPERCAR,
                Color(0xFF1565C0), Color(0xFFECEFF1), Color(0xFF212121),
                Color(0xFF12233B), Color(0xFF070F1C), ArtPattern.NONE, ""
            )
        }
    }
}
