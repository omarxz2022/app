package com.example.ui.art

import androidx.compose.ui.graphics.Color

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
