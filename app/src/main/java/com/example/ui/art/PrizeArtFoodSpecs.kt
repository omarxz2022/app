package com.example.ui.art

import androidx.compose.ui.graphics.Color

internal val foodArtSpecs: Map<String, CollectableArtSpec> = mapOf(
    "espresso_shot" to CollectableArtSpec(
        CollectableShape.ESPRESSO_CUP,
        Color(0xFF4B2C20), Color(0xFFD6A063), Color(0xFFF5EFE6),
        Color(0xFF3E2723), Color(0xFF1F120E), ArtPattern.STEAM_WAVES, "ES"
    ),
    "butter_croissant" to CollectableArtSpec(
        CollectableShape.CROISSANT,
        Color(0xFFE09A3E), Color(0xFFA65D1B), Color(0xFFFFE0A3),
        Color(0xFF4A311D), Color(0xFF2B1A0D), ArtPattern.NONE, "CR"
    ),
    "neapolitan_pizza" to CollectableArtSpec(
        CollectableShape.PIZZA_SLICE,
        Color(0xFFD93829), Color(0xFFF4C542), Color(0xFF3D8B40),
        Color(0xFF461D17), Color(0xFF230C09), ArtPattern.HERB_GARNISH, "PZ"
    ),
    "pretzel_bavarian" to CollectableArtSpec(
        CollectableShape.PRETZEL,
        Color(0xFF9E5422), Color(0xFF6B3410), Color(0xFFF8FAFC),
        Color(0xFF362419), Color(0xFF1E130B), ArtPattern.NONE, "BV"
    ),
    "tonkotsu_ramen" to CollectableArtSpec(
        CollectableShape.RAMEN_BOWL,
        Color(0xFF2A324B), Color(0xFFF3D58D), Color(0xFFD9534F),
        Color(0xFF20253A), Color(0xFF10131F), ArtPattern.STEAM_WAVES, "RM"
    ),
    "double_smash_burger" to CollectableArtSpec(
        CollectableShape.BURGER,
        Color(0xFFDF9236), Color(0xFF6B3A24), Color(0xFFF6C229),
        Color(0xFF452116), Color(0xFF240F09), ArtPattern.HERB_GARNISH, "BG"
    ),
    "street_taco_trio" to CollectableArtSpec(
        CollectableShape.TACO,
        Color(0xFFF0B43A), Color(0xFFC94B2B), Color(0xFF56A63D),
        Color(0xFF3D2912), Color(0xFF211408), ArtPattern.HERB_GARNISH, "TC"
    ),
    "falafel_pita" to CollectableArtSpec(
        CollectableShape.WRAP_PITA,
        Color(0xFFE3C28D), Color(0xFF6A8A37), Color(0xFFE05248),
        Color(0xFF2C361E), Color(0xFF151C0E), ArtPattern.HERB_GARNISH, "FL"
    ),
    "gelato_pistachio" to CollectableArtSpec(
        CollectableShape.GELATO_CONE,
        Color(0xFF83C57E), Color(0xFFD59B52), Color(0xFFBFE8B4),
        Color(0xFF1F3824), Color(0xFF0E1D12), ArtPattern.NONE, "GL"
    ),
    "shawarma_wrap" to CollectableArtSpec(
        CollectableShape.WRAP_PITA,
        Color(0xFFD6A86E), Color(0xFF8F3E28), Color(0xFFF8EEDB),
        Color(0xFF3F231B), Color(0xFF1E0F0A), ArtPattern.STEAM_WAVES, "SH"
    ),
    "pad_thai_prawn" to CollectableArtSpec(
        CollectableShape.PASTA_PLATE,
        Color(0xFFD47635), Color(0xFFF48C68), Color(0xFF72A83B),
        Color(0xFF3E2212), Color(0xFF211007), ArtPattern.HERB_GARNISH, "PT"
    ),
    "egg_benedict" to CollectableArtSpec(
        CollectableShape.EGGS_BENEDICT,
        Color(0xFFF6C945), Color(0xFFE97960), Color(0xFFFDFBF5),
        Color(0xFF3E3019), Color(0xFF20170A), ArtPattern.HERB_GARNISH, "EB"
    ),
    "chicken_katsu_curry" to CollectableArtSpec(
        CollectableShape.CURRY_RICE,
        Color(0xFFAD611D), Color(0xFFD99838), Color(0xFFF7F4EC),
        Color(0xFF3D2512), Color(0xFF201207), ArtPattern.NONE, "KT"
    ),
    "pho_bo_special" to CollectableArtSpec(
        CollectableShape.RAMEN_BOWL,
        Color(0xFF1A4B42), Color(0xFFC58A4A), Color(0xFF66B859),
        Color(0xFF12302B), Color(0xFF071815), ArtPattern.STEAM_WAVES, "PH"
    ),
    "fish_and_chips" to CollectableArtSpec(
        CollectableShape.FISH_CHIPS,
        Color(0xFFDDA137), Color(0xFFF4D35E), Color(0xFF5FA850),
        Color(0xFF1F3242), Color(0xFF0D1821), ArtPattern.HERB_GARNISH, "FC"
    ),
    "cubano_sandwich" to CollectableArtSpec(
        CollectableShape.SANDWICH_SUB,
        Color(0xFFD19B52), Color(0xFFB85449), Color(0xFFF2CC4F),
        Color(0xFF3D2C16), Color(0xFF1D1308), ArtPattern.NONE, "CB"
    ),
    "butter_chicken_naan" to CollectableArtSpec(
        CollectableShape.CURRY_RICE,
        Color(0xFFD95826), Color(0xFFF2C879), Color(0xFFFAF5E8),
        Color(0xFF491E10), Color(0xFF240D05), ArtPattern.HERB_GARNISH, "BC"
    ),
    "poke_bowl_tuna" to CollectableArtSpec(
        CollectableShape.POKE_BOWL,
        Color(0xFF1C354D), Color(0xFFE1535B), Color(0xFF7CB852),
        Color(0xFF12263A), Color(0xFF07121E), ArtPattern.HERB_GARNISH, "PK"
    ),
    "bibimbap_stone" to CollectableArtSpec(
        CollectableShape.POKE_BOWL,
        Color(0xFF2D2A2E), Color(0xFFD64531), Color(0xFFF2C14E),
        Color(0xFF311916), Color(0xFF170B09), ArtPattern.STEAM_WAVES, "BB"
    ),
    "carbonara_romana" to CollectableArtSpec(
        CollectableShape.PASTA_PLATE,
        Color(0xFFEBBF4E), Color(0xFF9C3D2D), Color(0xFF3B3530),
        Color(0xFF3B3014), Color(0xFF1E1708), ArtPattern.NONE, "CR"
    ),
    "gyoza_platter" to CollectableArtSpec(
        CollectableShape.DUMPLING_BASKET,
        Color(0xFFE9BA6E), Color(0xFFA85B27), Color(0xFF478A4E),
        Color(0xFF382717), Color(0xFF1C1209), ArtPattern.STEAM_WAVES, "GY"
    ),
    "bagel_lox" to CollectableArtSpec(
        CollectableShape.EGGS_BENEDICT,
        Color(0xFFCF924B), Color(0xFFEB7D67), Color(0xFFF8F9FA),
        Color(0xFF312A22), Color(0xFF181410), ArtPattern.HERB_GARNISH, "BL"
    ),
    "philly_cheesesteak" to CollectableArtSpec(
        CollectableShape.SANDWICH_SUB,
        Color(0xFFCA8E4B), Color(0xFF6B3E2B), Color(0xFFF6CF52),
        Color(0xFF362219), Color(0xFF1B0F0A), ArtPattern.STEAM_WAVES, "PC"
    ),
    "tagliatelle_ragu" to CollectableArtSpec(
        CollectableShape.PASTA_PLATE,
        Color(0xFFB33F2B), Color(0xFFE4B458), Color(0xFF4D8C45),
        Color(0xFF421812), Color(0xFF210A06), ArtPattern.HERB_GARNISH, "TR"
    ),
    "biryani_hyderabadi" to CollectableArtSpec(
        CollectableShape.CURRY_RICE,
        Color(0xFFE59D2E), Color(0xFF964123), Color(0xFF6BB052),
        Color(0xFF3E2610), Color(0xFF211306), ArtPattern.STEAM_WAVES, "BY"
    ),
    "laksa_seafood" to CollectableArtSpec(
        CollectableShape.RAMEN_BOWL,
        Color(0xFFBF4724), Color(0xFFF29B4B), Color(0xFFF7E7CE),
        Color(0xFF421B11), Color(0xFF210B05), ArtPattern.HERB_GARNISH, "LK"
    ),
    "tiramisu_classico" to CollectableArtSpec(
        CollectableShape.TIRAMISU_CAKE,
        Color(0xFF5A3724), Color(0xFFF3E5CF), Color(0xFFA06A42),
        Color(0xFF2C1B12), Color(0xFF140B06), ArtPattern.NONE, "TM"
    ),
    "moussaka_baked" to CollectableArtSpec(
        CollectableShape.CASSEROLE_TAGINE,
        Color(0xFFA84733), Color(0xFFF0D290), Color(0xFF5D3B6E),
        Color(0xFF3B1F24), Color(0xFF1C0D10), ArtPattern.STEAM_WAVES, "MS"
    ),
    "chicken_parmigiana" to CollectableArtSpec(
        CollectableShape.PIZZA_SLICE,
        Color(0xFFCA4931), Color(0xFFF7D774), Color(0xFF499141),
        Color(0xFF411B14), Color(0xFF200B08), ArtPattern.NONE, "CP"
    ),
    "poutine_smoked" to CollectableArtSpec(
        CollectableShape.FISH_CHIPS,
        Color(0xFFE6B64D), Color(0xFF6A3F23), Color(0xFFF4F1E8),
        Color(0xFF352414), Color(0xFF1A1108), ArtPattern.STEAM_WAVES, "PO"
    ),
    "dim_sum_basket" to CollectableArtSpec(
        CollectableShape.DUMPLING_BASKET,
        Color(0xFFC29050), Color(0xFFF5ECE0), Color(0xFFE06D53),
        Color(0xFF3A2A1A), Color(0xFF1B130B), ArtPattern.STEAM_WAVES, "DS"
    ),
    "korean_fried_chicken" to CollectableArtSpec(
        CollectableShape.FRIED_CHICKEN,
        Color(0xFFA93622), Color(0xFFD6782D), Color(0xFFF8F4DC),
        Color(0xFF3E1811), Color(0xFF1E0A06), ArtPattern.HERB_GARNISH, "KF"
    ),
    "schnitzel_wiener" to CollectableArtSpec(
        CollectableShape.FRIED_CHICKEN,
        Color(0xFFD9953B), Color(0xFFF2D45C), Color(0xFFB52B3C),
        Color(0xFF3B2814), Color(0xFF1D1308), ArtPattern.HERB_GARNISH, "SN"
    ),
    "paella_valenciana" to CollectableArtSpec(
        CollectableShape.PAELLA_PAN,
        Color(0xFFE4AA2D), Color(0xFFD94C30), Color(0xFF2D2E32),
        Color(0xFF3E2B10), Color(0xFF1E1306), ArtPattern.HERB_GARNISH, "PL"
    ),
    "miso_black_cod" to CollectableArtSpec(
        CollectableShape.OYSTER_SHELL,
        Color(0xFF9C5B2D), Color(0xFFF0D4A8), Color(0xFF49854B),
        Color(0xFF1B2B2D), Color(0xFF0C1517), ArtPattern.HERB_GARNISH, "MC"
    ),
    "duck_confit" to CollectableArtSpec(
        CollectableShape.DUCK_ROAST,
        Color(0xFF9E5026), Color(0xFFD8A04E), Color(0xFF538B43),
        Color(0xFF381E12), Color(0xFF1A0D07), ArtPattern.HERB_GARNISH, "DC"
    ),
    "beef_bourguignon" to CollectableArtSpec(
        CollectableShape.CASSEROLE_TAGINE,
        Color(0xFF6B242B), Color(0xFFA84F32), Color(0xFFD97236),
        Color(0xFF301116), Color(0xFF160609), ArtPattern.STEAM_WAVES, "BB"
    ),
    "lobster_roll_maine" to CollectableArtSpec(
        CollectableShape.SANDWICH_SUB,
        Color(0xFFD93E2F), Color(0xFFEBC06D), Color(0xFF5BA84D),
        Color(0xFF1C3544), Color(0xFF0C1A24), ArtPattern.HERB_GARNISH, "LR"
    ),
    "lasagna_tartufo" to CollectableArtSpec(
        CollectableShape.CASSEROLE_TAGINE,
        Color(0xFFB8432E), Color(0xFFF2C864), Color(0xFF2B2523),
        Color(0xFF341E19), Color(0xFF190C09), ArtPattern.NONE, "LT"
    ),
    "lamb_shank_tagine" to CollectableArtSpec(
        CollectableShape.CASSEROLE_TAGINE,
        Color(0xFFB85A2A), Color(0xFFE09E45), Color(0xFFF2D07D),
        Color(0xFF422211), Color(0xFF200F06), ArtPattern.GOLD_LEAF, "TG"
    ),
    "crab_cakes_maryland" to CollectableArtSpec(
        CollectableShape.BURGER,
        Color(0xFFC47933), Color(0xFFE85D44), Color(0xFFF2D66D),
        Color(0xFF1D313F), Color(0xFF0C1821), ArtPattern.HERB_GARNISH, "CC"
    ),
    "risotto_milanese" to CollectableArtSpec(
        CollectableShape.PAELLA_PAN,
        Color(0xFFF0B932), Color(0xFF8C452B), Color(0xFF549144),
        Color(0xFF3E2F10), Color(0xFF1F1606), ArtPattern.GOLD_LEAF, "RS"
    ),
    "pork_belly_bao" to CollectableArtSpec(
        CollectableShape.DUMPLING_BASKET,
        Color(0xFFF7F2EA), Color(0xFF9C4B2C), Color(0xFF5C9C4E),
        Color(0xFF2D2229), Color(0xFF140E12), ArtPattern.STEAM_WAVES, "PB"
    ),
    "branzino_salt_crusted" to CollectableArtSpec(
        CollectableShape.FISH_CHIPS,
        Color(0xFFB3C2CC), Color(0xFFF2D15B), Color(0xFF5BA362),
        Color(0xFF183142), Color(0xFF091620), ArtPattern.HERB_GARNISH, "BZ"
    ),
    "ceviche_nikkei" to CollectableArtSpec(
        CollectableShape.POKE_BOWL,
        Color(0xFF1F4258), Color(0xFFE86058), Color(0xFFF2DC6B),
        Color(0xFF122B3B), Color(0xFF07141E), ArtPattern.HERB_GARNISH, "CV"
    ),
    "rack_of_lamb" to CollectableArtSpec(
        CollectableShape.DUCK_ROAST,
        Color(0xFF7E3A26), Color(0xFF538E43), Color(0xFFF4EAD8),
        Color(0xFF2C1D19), Color(0xFF130B09), ArtPattern.HERB_GARNISH, "RL"
    ),
    "moules_mariniere" to CollectableArtSpec(
        CollectableShape.PAELLA_PAN,
        Color(0xFF2B3140), Color(0xFFF0C65B), Color(0xFF589446),
        Color(0xFF1B2738), Color(0xFF0B121C), ArtPattern.STEAM_WAVES, "MM"
    ),
    "peking_duck_roast" to CollectableArtSpec(
        CollectableShape.DUCK_ROAST,
        Color(0xFFA8391E), Color(0xFFE29B47), Color(0xFF5E9648),
        Color(0xFF42150D), Color(0xFF210904), ArtPattern.STEAM_WAVES, "PD"
    ),
    "short_rib_barolo" to CollectableArtSpec(
        CollectableShape.STEAK_CUT,
        Color(0xFF6B2823), Color(0xFF9E4438), Color(0xFFF0C973),
        Color(0xFF2E1214), Color(0xFF150709), ArtPattern.HERB_GARNISH, "SR"
    ),
    "filet_mignon_au_poivre" to CollectableArtSpec(
        CollectableShape.STEAK_CUT,
        Color(0xFF7D3428), Color(0xFFCFA56D), Color(0xFF4D423A),
        Color(0xFF331814), Color(0xFF180A08), ArtPattern.NONE, "FM"
    ),
    "bouillabaisse_marseille" to CollectableArtSpec(
        CollectableShape.PAELLA_PAN,
        Color(0xFFD65E2A), Color(0xFFF2AF4B), Color(0xFF304B66),
        Color(0xFF2C2636), Color(0xFF13101A), ArtPattern.STEAM_WAVES, "BM"
    ),
    "sushi_chirashi_deluxe" to CollectableArtSpec(
        CollectableShape.SUSHI_NIGIRI,
        Color(0xFFE8564B), Color(0xFFF6F4EE), Color(0xFF253528),
        Color(0xFF222938), Color(0xFF0F131C), ArtPattern.NONE, "SC"
    ),
    "scallops_truffle_puree" to CollectableArtSpec(
        CollectableShape.OYSTER_SHELL,
        Color(0xFFF2D29B), Color(0xFFFBF8F1), Color(0xFF3B302C),
        Color(0xFF2A262D), Color(0xFF121014), ArtPattern.HERB_GARNISH, "ST"
    ),
    "steak_frites_entrecote" to CollectableArtSpec(
        CollectableShape.STEAK_CUT,
        Color(0xFF8C3B2B), Color(0xFFEEC554), Color(0xFF78A349),
        Color(0xFF361B14), Color(0xFF1A0B08), ArtPattern.NONE, "SF"
    ),
    "cioppino_san_fran" to CollectableArtSpec(
        CollectableShape.PAELLA_PAN,
        Color(0xFFC43D29), Color(0xFFE88D48), Color(0xFF3A434D),
        Color(0xFF381918), Color(0xFF190A09), ArtPattern.STEAM_WAVES, "CI"
    ),
    "foie_gras_brioche" to CollectableArtSpec(
        CollectableShape.TIRAMISU_CAKE,
        Color(0xFFD49D6A), Color(0xFF7C3246), Color(0xFFF5D58E),
        Color(0xFF361C24), Color(0xFF1A0B10), ArtPattern.GOLD_LEAF, "FG"
    ),
    "texas_brisket_feast" to CollectableArtSpec(
        CollectableShape.STEAK_CUT,
        Color(0xFF422820), Color(0xFFB5513A), Color(0xFF63943E),
        Color(0xFF301B14), Color(0xFF160B07), ArtPattern.STEAM_WAVES, "TX"
    ),
    "cacio_e_pepe_wheel" to CollectableArtSpec(
        CollectableShape.CHEESE_TRUFFLE,
        Color(0xFFF0CB62), Color(0xFF3D3631), Color(0xFFD6A844),
        Color(0xFF382E18), Color(0xFF1B150A), ArtPattern.NONE, "CE"
    ),
    "oyster_raw_bar" to CollectableArtSpec(
        CollectableShape.OYSTER_SHELL,
        Color(0xFF8FA1AD), Color(0xFFEBF3F7), Color(0xFFF0CA4F),
        Color(0xFF142A3A), Color(0xFF07131D), ArtPattern.NONE, "OY"
    ),
    "venison_medallions" to CollectableArtSpec(
        CollectableShape.STEAK_CUT,
        Color(0xFF6E242E), Color(0xFF4D243C), Color(0xFF608C45),
        Color(0xFF2D1521), Color(0xFF14080F), ArtPattern.HERB_GARNISH, "VM"
    ),
    "lobster_thermidor" to CollectableArtSpec(
        CollectableShape.LOBSTER_CRAB,
        Color(0xFFD13A28), Color(0xFFF2C868), Color(0xFF4E8C41),
        Color(0xFF3C1914), Color(0xFF1D0A07), ArtPattern.NONE, "LT"
    ),
    "beef_wellington" to CollectableArtSpec(
        CollectableShape.DUCK_ROAST,
        Color(0xFFDCA148), Color(0xFF9C3535), Color(0xFF5B3B2B),
        Color(0xFF3B2219), Color(0xFF1B0E09), ArtPattern.GOLD_LEAF, "BW"
    ),
    "alaskan_king_crab" to CollectableArtSpec(
        CollectableShape.LOBSTER_CRAB,
        Color(0xFFE05232), Color(0xFFF7D468), Color(0xFF89B4C4),
        Color(0xFF182C3C), Color(0xFF0A141E), ArtPattern.NONE, "KC"
    ),
    "porterhouse_for_two" to CollectableArtSpec(
        CollectableShape.STEAK_CUT,
        Color(0xFF853028), Color(0xFFF2DFC4), Color(0xFFD69F42),
        Color(0xFF381814), Color(0xFF1B0A08), ArtPattern.STEAM_WAVES, "PH"
    ),
    "suckling_pig_segovia" to CollectableArtSpec(
        CollectableShape.DUCK_ROAST,
        Color(0xFFC96E30), Color(0xFFEBB665), Color(0xFF648F44),
        Color(0xFF3E2212), Color(0xFF1E0E07), ArtPattern.NONE, "SG"
    ),
    "uni_pasta_hokkaido" to CollectableArtSpec(
        CollectableShape.PASTA_PLATE,
        Color(0xFFF2962E), Color(0xFFF5D06B), Color(0xFF2E3830),
        Color(0xFF382312), Color(0xFF1A0F06), ArtPattern.GOLD_LEAF, "UN"
    ),
    "chateaubriand_flambe" to CollectableArtSpec(
        CollectableShape.STEAK_CUT,
        Color(0xFF7D2E28), Color(0xFFF09837), Color(0xFF634A3B),
        Color(0xFF3E1C10), Color(0xFF1D0A05), ArtPattern.GOLD_LEAF, "CH"
    ),
    "tomahawk_gold_sear" to CollectableArtSpec(
        CollectableShape.STEAK_CUT,
        Color(0xFF6E2722), Color(0xFFF7C948), Color(0xFFE6D5B8),
        Color(0xFF331B0E), Color(0xFF180B04), ArtPattern.GOLD_LEAF, "TH"
    ),
    "abalone_oyster_braise" to CollectableArtSpec(
        CollectableShape.OYSTER_SHELL,
        Color(0xFF9C7E48), Color(0xFF476E48), Color(0xFF34484F),
        Color(0xFF1C2826), Color(0xFF0B1211), ArtPattern.GOLD_LEAF, "AB"
    ),
    "stone_crab_feast" to CollectableArtSpec(
        CollectableShape.LOBSTER_CRAB,
        Color(0xFFD86842), Color(0xFF28282B), Color(0xFFF2C94C),
        Color(0xFF18313E), Color(0xFF0A161F), ArtPattern.NONE, "SC"
    ),
    "shabu_shabu_miyazaki" to CollectableArtSpec(
        CollectableShape.RAMEN_BOWL,
        Color(0xFF422728), Color(0xFFD45C68), Color(0xFF66A358),
        Color(0xFF2C191B), Color(0xFF130A0B), ArtPattern.STEAM_WAVES, "SS"
    ),
    "wagyu_steak" to CollectableArtSpec(
        CollectableShape.STEAK_CUT,
        Color(0xFFA13B42), Color(0xFFFCE6E4), Color(0xFFD9B05B),
        Color(0xFF3B151B), Color(0xFF1B080B), ArtPattern.HERB_GARNISH, "A5"
    ),
    "truffle_tagliolini_alba" to CollectableArtSpec(
        CollectableShape.PASTA_PLATE,
        Color(0xFFF5D576), Color(0xFFEBE1CA), Color(0xFF7D6548),
        Color(0xFF383020), Color(0xFF1B160D), ArtPattern.GOLD_LEAF, "AL"
    ),
    "kobe_sando_wagyu" to CollectableArtSpec(
        CollectableShape.SANDWICH_SUB,
        Color(0xFFF7EEDB), Color(0xFFB5424D), Color(0xFF8C5226),
        Color(0xFF33181C), Color(0xFF180A0D), ArtPattern.NONE, "KS"
    ),
    "lobster_ravioli_michelin" to CollectableArtSpec(
        CollectableShape.PASTA_PLATE,
        Color(0xFFE86A42), Color(0xFFF7D58B), Color(0xFF5BA153),
        Color(0xFF3E1E14), Color(0xFF1E0C07), ArtPattern.HERB_GARNISH, "RV"
    ),
    "osetra_caviar_blini" to CollectableArtSpec(
        CollectableShape.CAVIAR_TIN,
        Color(0xFF23395B), Color(0xFFE8B948), Color(0xFF1A1E24),
        Color(0xFF142136), Color(0xFF070C17), ArtPattern.GOLD_LEAF, "OS"
    ),
    "bresse_chicken_truffle" to CollectableArtSpec(
        CollectableShape.DUCK_ROAST,
        Color(0xFFE0B46E), Color(0xFF262322), Color(0xFFF4E7D0),
        Color(0xFF2D251C), Color(0xFF14100B), ArtPattern.GOLD_LEAF, "BR"
    ),
    "kobe_teppanyaki" to CollectableArtSpec(
        CollectableShape.STEAK_CUT,
        Color(0xFF8D3238), Color(0xFFF7B543), Color(0xFF383A40),
        Color(0xFF2B1C1E), Color(0xFF130B0C), ArtPattern.STEAM_WAVES, "TP"
    ),
    "bluefin_otoro_flight" to CollectableArtSpec(
        CollectableShape.SUSHI_NIGIRI,
        Color(0xFFEB6B7A), Color(0xFFF9ECEF), Color(0xFF69A84F),
        Color(0xFF2B1B24), Color(0xFF140B10), ArtPattern.GOLD_LEAF, "OT"
    ),
    "matsutake_suimono" to CollectableArtSpec(
        CollectableShape.RAMEN_BOWL,
        Color(0xFF5A402E), Color(0xFFE0C494), Color(0xFF4F804B),
        Color(0xFF261E16), Color(0xFF100C08), ArtPattern.STEAM_WAVES, "MT"
    ),
    "seafood_grand_plateau" to CollectableArtSpec(
        CollectableShape.LOBSTER_CRAB,
        Color(0xFF2C5E8A), Color(0xFFE85C42), Color(0xFFF0D58B),
        Color(0xFF12273D), Color(0xFF07111C), ArtPattern.GOLD_LEAF, "MC"
    ),
    "kobe_chateaubriand" to CollectableArtSpec(
        CollectableShape.STEAK_CUT,
        Color(0xFF96343A), Color(0xFFFAD8D4), Color(0xFF3E3836),
        Color(0xFF341418), Color(0xFF160709), ArtPattern.STEAM_WAVES, "KB"
    ),
    "piedmont_truffle_risotto" to CollectableArtSpec(
        CollectableShape.PAELLA_PAN,
        Color(0xFFEBD9AA), Color(0xFF6E2530), Color(0xFFF0C65B),
        Color(0xFF30241A), Color(0xFF160F0A), ArtPattern.GOLD_LEAF, "PM"
    ),
    "tasting_menu_two_star" to CollectableArtSpec(
        CollectableShape.TASTING_PLATE,
        Color(0xFFECEFF4), Color(0xFFD9A648), Color(0xFF4F8755),
        Color(0xFF1F2735), Color(0xFF0D1119), ArtPattern.GOLD_LEAF, "2★"
    ),
    "snow_crab_echizen" to CollectableArtSpec(
        CollectableShape.LOBSTER_CRAB,
        Color(0xFFEB6B4B), Color(0xFFF5EBDC), Color(0xFFC29B54),
        Color(0xFF2E1B1A), Color(0xFF150A0A), ArtPattern.STEAM_WAVES, "EC"
    ),
    "jamon_iberico_reserve" to CollectableArtSpec(
        CollectableShape.STEAK_CUT,
        Color(0xFF9E2B2B), Color(0xFFF2D2BA), Color(0xFFD4A242),
        Color(0xFF381414), Color(0xFF1A0707), ArtPattern.NONE, "5J"
    ),
    "perigord_tournedos" to CollectableArtSpec(
        CollectableShape.STEAK_CUT,
        Color(0xFF7A2D28), Color(0xFF2B2524), Color(0xFFD4A85C),
        Color(0xFF2E1716), Color(0xFF140908), ArtPattern.GOLD_LEAF, "TR"
    ),
    "gold_leaf_wagyu_burger" to CollectableArtSpec(
        CollectableShape.BURGER,
        Color(0xFFF0C246), Color(0xFF7D2E34), Color(0xFFF7E49C),
        Color(0xFF382811), Color(0xFF1A1206), ArtPattern.GOLD_LEAF, "AU"
    ),
    "sushi_omakase_ginza" to CollectableArtSpec(
        CollectableShape.SUSHI_NIGIRI,
        Color(0xFFDE4B43), Color(0xFFF4EFE6), Color(0xFFD4A646),
        Color(0xFF20222B), Color(0xFF0D0E13), ArtPattern.GOLD_LEAF, "OM"
    ),
    "saffron_lobster_feast" to CollectableArtSpec(
        CollectableShape.LOBSTER_CRAB,
        Color(0xFF345E9E), Color(0xFFF4B63F), Color(0xFFF7E7C4),
        Color(0xFF152542), Color(0xFF081020), ArtPattern.GOLD_LEAF, "SL"
    ),
    "three_star_paris" to CollectableArtSpec(
        CollectableShape.TASTING_PLATE,
        Color(0xFFF8F9FA), Color(0xFFE6B442), Color(0xFF3E4C66),
        Color(0xFF1B2336), Color(0xFF0B0F1A), ArtPattern.GOLD_LEAF, "3★"
    ),
    "fugu_kaiseki_tokyo" to CollectableArtSpec(
        CollectableShape.SUSHI_NIGIRI,
        Color(0xFFE3EEF2), Color(0xFF2C4859), Color(0xFF5FA663),
        Color(0xFF14242E), Color(0xFF071016), ArtPattern.HERB_GARNISH, "FG"
    ),
    "vintage_cheese_cellar" to CollectableArtSpec(
        CollectableShape.CHEESE_TRUFFLE,
        Color(0xFFE8B448), Color(0xFF7A283B), Color(0xFFB58E52),
        Color(0xFF311F1B), Color(0xFF170D0B), ArtPattern.NONE, "CH"
    ),
    "kyoto_ryokan_banquet" to CollectableArtSpec(
        CollectableShape.TASTING_PLATE,
        Color(0xFFA63429), Color(0xFFD4A64A), Color(0xFF4F824F),
        Color(0xFF2E1714), Color(0xFF140907), ArtPattern.GOLD_LEAF, "KY"
    ),
    "imperial_beluga_tin" to CollectableArtSpec(
        CollectableShape.CAVIAR_TIN,
        Color(0xFFD9A638), Color(0xFF1E222B), Color(0xFFF9E596),
        Color(0xFF302612), Color(0xFF140F06), ArtPattern.GOLD_LEAF, "AL"
    ),
    "whole_jamon_vintage" to CollectableArtSpec(
        CollectableShape.DUCK_ROAST,
        Color(0xFF8C2828), Color(0xFF5C3E2A), Color(0xFFF0C678),
        Color(0xFF361614), Color(0xFF180807), ArtPattern.GOLD_LEAF, "JM"
    ),
    "noma_foraged_table" to CollectableArtSpec(
        CollectableShape.TASTING_PLATE,
        Color(0xFFD8D0C0), Color(0xFF4E7A48), Color(0xFF8A5C38),
        Color(0xFF1C291E), Color(0xFF0B120C), ArtPattern.HERB_GARNISH, "NM"
    ),
    "modena_extravecchio" to CollectableArtSpec(
        CollectableShape.CHEESE_TRUFFLE,
        Color(0xFF2B1C22), Color(0xFFE6C578), Color(0xFFC29038),
        Color(0xFF28191D), Color(0xFF120A0D), ArtPattern.GOLD_LEAF, "MD"
    ),
    "whole_kobe_strip_rack" to CollectableArtSpec(
        CollectableShape.STEAK_CUT,
        Color(0xFF8A2B36), Color(0xFFFBE3E0), Color(0xFFE6B64B),
        Color(0xFF38141A), Color(0xFF19070B), ArtPattern.GOLD_LEAF, "KR"
    ),
    "private_michelin_chef" to CollectableArtSpec(
        CollectableShape.TASTING_PLATE,
        Color(0xFFFFFFFF), Color(0xFFC99B3B), Color(0xFF243447),
        Color(0xFF1E2836), Color(0xFF0C1119), ArtPattern.GOLD_LEAF, "CH"
    ),
    "truffle_auction_lot" to CollectableArtSpec(
        CollectableShape.CHEESE_TRUFFLE,
        Color(0xFFD9C7A3), Color(0xFF7D6545), Color(0xFFF5D16C),
        Color(0xFF30281C), Color(0xFF15100A), ArtPattern.GOLD_LEAF, "TR"
    ),
    "kopi_luwak_patisserie" to CollectableArtSpec(
        CollectableShape.ESPRESSO_CUP,
        Color(0xFF2C1B15), Color(0xFFE6B44C), Color(0xFFF5E6C8),
        Color(0xFF2E1E16), Color(0xFF130B08), ArtPattern.GOLD_LEAF, "KL"
    ),
    "royal_omakase_charter" to CollectableArtSpec(
        CollectableShape.SUSHI_NIGIRI,
        Color(0xFFC9343B), Color(0xFFFAE6DC), Color(0xFFF0C246),
        Color(0xFF29141C), Color(0xFF11070B), ArtPattern.GOLD_LEAF, "RY"
    ),
    "grand_gastronomy_gala" to CollectableArtSpec(
        CollectableShape.CAVIAR_TIN,
        Color(0xFFE6B43C), Color(0xFF5A1E2E), Color(0xFFFFF4C7),
        Color(0xFF331B1A), Color(0xFF140909), ArtPattern.GOLD_LEAF, "GG"
    ),
)
