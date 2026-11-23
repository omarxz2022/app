package com.example.ui.art

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import com.example.R

enum class CollectableShape {
    // Food dishes
    ESPRESSO_CUP,
    CROISSANT,
    PIZZA_SLICE,
    PRETZEL,
    RAMEN_BOWL,
    BURGER,
    TACO,
    WRAP_PITA,
    GELATO_CONE,
    EGGS_BENEDICT,
    CURRY_RICE,
    FISH_CHIPS,
    SANDWICH_SUB,
    POKE_BOWL,
    PASTA_PLATE,
    DUMPLING_BASKET,
    FRIED_CHICKEN,
    PAELLA_PAN,
    STEAK_CUT,
    LOBSTER_CRAB,
    SUSHI_NIGIRI,
    OYSTER_SHELL,
    DUCK_ROAST,
    CASSEROLE_TAGINE,
    TIRAMISU_CAKE,
    CAVIAR_TIN,
    CHEESE_TRUFFLE,
    TASTING_PLATE,

    // Motorbikes
    MINI_MOTO,
    SCOOTER,
    RETRO_ROADSTER,
    CRUISER_CHOPPER,
    BAGGER_TOURER,
    ADVENTURE_ENDURO,
    NAKED_STREETFIGHTER,
    SUPERSPORT_FAIRING,
    MOTOGP_PROTOTYPE,
    CUSTOM_TURBINE,

    // Cars
    CITY_HATCH,
    SEDAN_SALOON,
    WAGON_ESTATE,
    SUV_OFFROADER,
    PICKUP_TRUCK,
    ROADSTER_CONVERTIBLE,
    MUSCLE_COUPE,
    MID_ENGINE_SUPERCAR,
    GT3_RACE_CAR,
    RALLY_CAR,
    LE_MANS_PROTOTYPE,
    FORMULA_OPEN_WHEEL,
    VINTAGE_RACER,
}

enum class ArtPattern {
    NONE,
    RACING_STRIPE,
    TWO_TONE,
    CARBON_AERO,
    SIDE_ACCENT,
    CHECKERED,
    HERB_GARNISH,
    GOLD_LEAF,
    STEAM_WAVES,
}

data class CollectableArtSpec(
    val shape: CollectableShape,
    val primary: Color,
    val secondary: Color,
    val accent: Color,
    val backdropTop: Color,
    val backdropBottom: Color,
    val pattern: ArtPattern = ArtPattern.NONE,
    val badge: String = "",
)

internal fun Color.lighten(fraction: Float): Color = lerp(this, Color.White, fraction.coerceIn(0f, 1f))

internal fun Color.darken(fraction: Float): Color = lerp(this, Color(0xFF0E1014), fraction.coerceIn(0f, 1f))

/**
 * 1-to-1 photorealistic studio PNG drawables strictly matched to their exact collectable IDs.
 * Every other collectable uses the custom studio-shaded procedural renderer with its own unique spec.
 */
internal val exactHeroDrawableById: Map<String, Int> = mapOf(
    "neapolitan_pizza" to R.drawable.img_prize_pizza,
    "tonkotsu_ramen" to R.drawable.img_prize_ramen,
    "double_smash_burger" to R.drawable.img_prize_burger,
    "wagyu_steak" to R.drawable.img_prize_wagyu,
    "harley_fatboy" to R.drawable.img_prize_harley_fatboy,
    "yamaha_r1" to R.drawable.img_prize_yamaha_r1,
    "ducati_panigale_v4" to R.drawable.img_prize_ducati_v4,
    "kawasaki_ninja_h2r" to R.drawable.img_prize_kawasaki_h2r,
    "bmw_m3_comp" to R.drawable.img_prize_bmw_m3,
    "porsche_911_gt3" to R.drawable.img_prize_porsche_gt3,
    "lambo_huracan" to R.drawable.img_prize_lambo_huracan,
    "ferrari_f40" to R.drawable.img_prize_ferrari_f40,
)

@DrawableRes
internal fun exactHeroDrawableFor(prizeId: String): Int? = exactHeroDrawableById[prizeId]
