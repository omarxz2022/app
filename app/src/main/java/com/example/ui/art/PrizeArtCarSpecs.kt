package com.example.ui.art

import androidx.compose.ui.graphics.Color

internal val carArtSpecs: Map<String, CollectableArtSpec> = mapOf(
    "dacia_sandero" to CollectableArtSpec(
        CollectableShape.CITY_HATCH,
        Color(0xFF546E7A), Color(0xFFECEFF1), Color(0xFF263238),
        Color(0xFF1F272B), Color(0xFF0D1214), ArtPattern.NONE, "DS"
    ),
    "kia_picanto" to CollectableArtSpec(
        CollectableShape.CITY_HATCH,
        Color(0xFFAFB42B), Color(0xFF263238), Color(0xFFECEFF1),
        Color(0xFF282B14), Color(0xFF111307), ArtPattern.SIDE_ACCENT, "KP"
    ),
    "nissan_versa" to CollectableArtSpec(
        CollectableShape.SEDAN_SALOON,
        Color(0xFF1E88E5), Color(0xFFCFD8DC), Color(0xFF263238),
        Color(0xFF122336), Color(0xFF070F1A), ArtPattern.NONE, "VS"
    ),
    "hyundai_i20" to CollectableArtSpec(
        CollectableShape.CITY_HATCH,
        Color(0xFF00ACC1), Color(0xFF212121), Color(0xFFCFD8DC),
        Color(0xFF11292E), Color(0xFF071316), ArtPattern.TWO_TONE, "i20"
    ),
    "suzuki_swift" to CollectableArtSpec(
        CollectableShape.CITY_HATCH,
        Color(0xFFE53935), Color(0xFF212121), Color(0xFFECEFF1),
        Color(0xFF331416), Color(0xFF170708), ArtPattern.TWO_TONE, "SW"
    ),
    "fiat_500_hybrid" to CollectableArtSpec(
        CollectableShape.CITY_HATCH,
        Color(0xFF80CBC4), Color(0xFFF5F5F5), Color(0xFFCFD8DC),
        Color(0xFF182E2C), Color(0xFF0A1615), ArtPattern.NONE, "500"
    ),
    "toyota_corolla_le" to CollectableArtSpec(
        CollectableShape.SEDAN_SALOON,
        Color(0xFFB0BEC5), Color(0xFF37474F), Color(0xFF4FC3F7),
        Color(0xFF1F262B), Color(0xFF0D1114), ArtPattern.NONE, "HY"
    ),
    "honda_civic_sport" to CollectableArtSpec(
        CollectableShape.SEDAN_SALOON,
        Color(0xFF455A64), Color(0xFF212121), Color(0xFFECEFF1),
        Color(0xFF1E2429), Color(0xFF0D1014), ArtPattern.SIDE_ACCENT, "CV"
    ),
    "mazda3_hatch" to CollectableArtSpec(
        CollectableShape.CITY_HATCH,
        Color(0xFFA81C24), Color(0xFF263238), Color(0xFFB0BEC5),
        Color(0xFF331012), Color(0xFF170607), ArtPattern.NONE, "M3"
    ),
    "volkswagen_polo_gti" to CollectableArtSpec(
        CollectableShape.CITY_HATCH,
        Color(0xFFECEFF1), Color(0xFFD32F2F), Color(0xFF212121),
        Color(0xFF2B1C1E), Color(0xFF130B0C), ArtPattern.SIDE_ACCENT, "GTI"
    ),
    "subaru_crosstrek" to CollectableArtSpec(
        CollectableShape.SUV_OFFROADER,
        Color(0xFF4DB6AC), Color(0xFF263238), Color(0xFFFF7043),
        Color(0xFF162C2B), Color(0xFF091414), ArtPattern.SIDE_ACCENT, "AWD"
    ),
    "mazda_mx5_miata" to CollectableArtSpec(
        CollectableShape.ROADSTER_CONVERTIBLE,
        Color(0xFFC62828), Color(0xFF212121), Color(0xFFCFD8DC),
        Color(0xFF361214), Color(0xFF180708), ArtPattern.NONE, "MX5"
    ),
    "toyota_camry_xse" to CollectableArtSpec(
        CollectableShape.SEDAN_SALOON,
        Color(0xFFECEFF1), Color(0xFF212121), Color(0xFFD32F2F),
        Color(0xFF22252A), Color(0xFF0E1014), ArtPattern.TWO_TONE, "XSE"
    ),
    "honda_accord_touring" to CollectableArtSpec(
        CollectableShape.SEDAN_SALOON,
        Color(0xFF283593), Color(0xFFCFD8DC), Color(0xFF263238),
        Color(0xFF131938), Color(0xFF070A1B), ArtPattern.NONE, "AC"
    ),
    "subaru_brz_ts" to CollectableArtSpec(
        CollectableShape.MUSCLE_COUPE,
        Color(0xFF1565C0), Color(0xFFD4AF37), Color(0xFF263238),
        Color(0xFF10223B), Color(0xFF060E1C), ArtPattern.SIDE_ACCENT, "BRZ"
    ),
    "toyota_gr86" to CollectableArtSpec(
        CollectableShape.MUSCLE_COUPE,
        Color(0xFFE53935), Color(0xFF212121), Color(0xFFECEFF1),
        Color(0xFF361214), Color(0xFF180708), ArtPattern.RACING_STRIPE, "86"
    ),
    "volkswagen_golf_gti" to CollectableArtSpec(
        CollectableShape.CITY_HATCH,
        Color(0xFFD32F2F), Color(0xFF212121), Color(0xFFECEFF1),
        Color(0xFF361416), Color(0xFF170708), ArtPattern.RACING_STRIPE, "VW"
    ),
    "hyundai_elantra_n" to CollectableArtSpec(
        CollectableShape.SEDAN_SALOON,
        Color(0xFF64B5F6), Color(0xFF212121), Color(0xFFE53935),
        Color(0xFF152638), Color(0xFF08111C), ArtPattern.SIDE_ACCENT, "EN"
    ),
    "ford_mustang_ecoboost" to CollectableArtSpec(
        CollectableShape.MUSCLE_COUPE,
        Color(0xFF1E88E5), Color(0xFFECEFF1), Color(0xFF263238),
        Color(0xFF12243B), Color(0xFF070F1C), ArtPattern.RACING_STRIPE, "EB"
    ),
    "mini_cooper_jcw" to CollectableArtSpec(
        CollectableShape.CITY_HATCH,
        Color(0xFF1B5E20), Color(0xFFD32F2F), Color(0xFFECEFF1),
        Color(0xFF132E15), Color(0xFF071408), ArtPattern.CHECKERED, "JCW"
    ),
    "toyota_rav4_prime" to CollectableArtSpec(
        CollectableShape.SUV_OFFROADER,
        Color(0xFFB71C1C), Color(0xFF212121), Color(0xFFB0BEC5),
        Color(0xFF331214), Color(0xFF160708), ArtPattern.TWO_TONE, "RV4"
    ),
    "honda_civic_type_r" to CollectableArtSpec(
        CollectableShape.CITY_HATCH,
        Color(0xFFF5F2EB), Color(0xFFD50000), Color(0xFF212121),
        Color(0xFF331618), Color(0xFF16080A), ArtPattern.RACING_STRIPE, "FL5"
    ),
    "toyota_gr_corolla" to CollectableArtSpec(
        CollectableShape.CITY_HATCH,
        Color(0xFF212121), Color(0xFFD50000), Color(0xFFB0BEC5),
        Color(0xFF291416), Color(0xFF120708), ArtPattern.CARBON_AERO, "GRC"
    ),
    "volkswagen_golf_r" to CollectableArtSpec(
        CollectableShape.CITY_HATCH,
        Color(0xFF1565C0), Color(0xFFCFD8DC), Color(0xFF263238),
        Color(0xFF10223A), Color(0xFF060E1B), ArtPattern.SIDE_ACCENT, "R"
    ),
    "ford_f150_lariat" to CollectableArtSpec(
        CollectableShape.PICKUP_TRUCK,
        Color(0xFF1E4478), Color(0xFFB0BEC5), Color(0xFF263238),
        Color(0xFF122136), Color(0xFF070E1A), ArtPattern.NONE, "F150"
    ),
    "jeep_wrangler_rubicon" to CollectableArtSpec(
        CollectableShape.SUV_OFFROADER,
        Color(0xFFC29B61), Color(0xFF212121), Color(0xFFD32F2F),
        Color(0xFF2E2417), Color(0xFF141009), ArtPattern.NONE, "4X4"
    ),
    "tesla_model_3_perf" to CollectableArtSpec(
        CollectableShape.SEDAN_SALOON,
        Color(0xFFD32F2F), Color(0xFF212121), Color(0xFFECEFF1),
        Color(0xFF361416), Color(0xFF170708), ArtPattern.CARBON_AERO, "EV"
    ),
    "bmw_m240i_xdrive" to CollectableArtSpec(
        CollectableShape.MUSCLE_COUPE,
        Color(0xFF5E35B1), Color(0xFF263238), Color(0xFFCFD8DC),
        Color(0xFF221638), Color(0xFF0E091B), ArtPattern.NONE, "240"
    ),
    "audi_s4_quattro" to CollectableArtSpec(
        CollectableShape.SEDAN_SALOON,
        Color(0xFF607D8B), Color(0xFFCFD8DC), Color(0xFFD32F2F),
        Color(0xFF20262B), Color(0xFF0D1014), ArtPattern.SIDE_ACCENT, "S4"
    ),
    "nissan_z_nismo" to CollectableArtSpec(
        CollectableShape.MUSCLE_COUPE,
        Color(0xFFB0BEC5), Color(0xFFD50000), Color(0xFF212121),
        Color(0xFF2B1C1E), Color(0xFF120B0C), ArtPattern.SIDE_ACCENT, "Z"
    ),
    "lexus_rx_500h" to CollectableArtSpec(
        CollectableShape.SUV_OFFROADER,
        Color(0xFF37474F), Color(0xFF8D3B2E), Color(0xFFCFD8DC),
        Color(0xFF1F2429), Color(0xFF0D1014), ArtPattern.NONE, "RX"
    ),
    "ford_mustang_dark_horse" to CollectableArtSpec(
        CollectableShape.MUSCLE_COUPE,
        Color(0xFF1B3554), Color(0xFF212121), Color(0xFF4FC3F7),
        Color(0xFF101D30), Color(0xFF060C16), ArtPattern.CARBON_AERO, "DH"
    ),
    "toyota_land_cruiser" to CollectableArtSpec(
        CollectableShape.SUV_OFFROADER,
        Color(0xFFF5EFE0), Color(0xFF455A64), Color(0xFF263238),
        Color(0xFF2B2820), Color(0xFF13110D), ArtPattern.TWO_TONE, "LC"
    ),
    "alfa_romeo_giulia_qv" to CollectableArtSpec(
        CollectableShape.SEDAN_SALOON,
        Color(0xFF9E1B24), Color(0xFF2E7D32), Color(0xFFECEFF1),
        Color(0xFF361014), Color(0xFF180608), ArtPattern.NONE, "QV"
    ),
    "chevrolet_corvette_c8" to CollectableArtSpec(
        CollectableShape.MID_ENGINE_SUPERCAR,
        Color(0xFFFF6D00), Color(0xFF212121), Color(0xFFCFD8DC),
        Color(0xFF381D0A), Color(0xFF1A0C04), ArtPattern.CARBON_AERO, "C8"
    ),
    "bmw_m2_g87" to CollectableArtSpec(
        CollectableShape.MUSCLE_COUPE,
        Color(0xFF80DEEA), Color(0xFF212121), Color(0xFFD32F2F),
        Color(0xFF162B30), Color(0xFF081417), ArtPattern.CARBON_AERO, "M2"
    ),
    "cadillac_ct5v_blackwing" to CollectableArtSpec(
        CollectableShape.SEDAN_SALOON,
        Color(0xFF212121), Color(0xFFD4AF37), Color(0xFFCFD8DC),
        Color(0xFF242118), Color(0xFF0F0D09), ArtPattern.GOLD_LEAF, "BW"
    ),
    "porsche_718_cayman_gts" to CollectableArtSpec(
        CollectableShape.MID_ENGINE_SUPERCAR,
        Color(0xFFFBC02D), Color(0xFF212121), Color(0xFFB0BEC5),
        Color(0xFF33290E), Color(0xFF161105), ArtPattern.SIDE_ACCENT, "718"
    ),
    "porsche_macan_gts" to CollectableArtSpec(
        CollectableShape.SUV_OFFROADER,
        Color(0xFFD32F2F), Color(0xFF212121), Color(0xFFB0BEC5),
        Color(0xFF331214), Color(0xFF170708), ArtPattern.SIDE_ACCENT, "MG"
    ),
    "rivian_r1s_quad" to CollectableArtSpec(
        CollectableShape.SUV_OFFROADER,
        Color(0xFF2E6F4E), Color(0xFFFBC02D), Color(0xFF263238),
        Color(0xFF132B1E), Color(0xFF07130D), ArtPattern.NONE, "R1S"
    ),
    "bmw_m3_comp" to CollectableArtSpec(
        CollectableShape.SEDAN_SALOON,
        Color(0xFF1B5E20), Color(0xFFD4AF37), Color(0xFF212121),
        Color(0xFF122B14), Color(0xFF061308), ArtPattern.CARBON_AERO, "M3"
    ),
    "mercedes_amg_c63s" to CollectableArtSpec(
        CollectableShape.SEDAN_SALOON,
        Color(0xFF78909C), Color(0xFFD50000), Color(0xFF212121),
        Color(0xFF24262B), Color(0xFF0F1014), ArtPattern.SIDE_ACCENT, "63"
    ),
    "bmw_m4_csl" to CollectableArtSpec(
        CollectableShape.MUSCLE_COUPE,
        Color(0xFFCFD8DC), Color(0xFFD50000), Color(0xFF212121),
        Color(0xFF2B1C1E), Color(0xFF120B0C), ArtPattern.RACING_STRIPE, "CSL"
    ),
    "audi_rs6_avant" to CollectableArtSpec(
        CollectableShape.WAGON_ESTATE,
        Color(0xFF546E7A), Color(0xFF212121), Color(0xFFD50000),
        Color(0xFF1E262B), Color(0xFF0C1014), ArtPattern.CARBON_AERO, "RS6"
    ),
    "nissan_gtr_nismo" to CollectableArtSpec(
        CollectableShape.MUSCLE_COUPE,
        Color(0xFFECEFF1), Color(0xFFD50000), Color(0xFF212121),
        Color(0xFF2D1B1D), Color(0xFF140A0C), ArtPattern.CARBON_AERO, "GTR"
    ),
    "porsche_911_carrera_gts" to CollectableArtSpec(
        CollectableShape.MUSCLE_COUPE,
        Color(0xFF9E1C22), Color(0xFF212121), Color(0xFFCFD8DC),
        Color(0xFF331114), Color(0xFF170608), ArtPattern.NONE, "911"
    ),
    "chevrolet_corvette_z06" to CollectableArtSpec(
        CollectableShape.MID_ENGINE_SUPERCAR,
        Color(0xFF00B8D4), Color(0xFF212121), Color(0xFFE53935),
        Color(0xFF102B33), Color(0xFF061318), ArtPattern.RACING_STRIPE, "Z06"
    ),
    "mercedes_amg_gt63" to CollectableArtSpec(
        CollectableShape.MUSCLE_COUPE,
        Color(0xFF2E6F4E), Color(0xFFD4AF37), Color(0xFF212121),
        Color(0xFF12291C), Color(0xFF06120B), ArtPattern.GOLD_LEAF, "GT"
    ),
    "aston_martin_vantage" to CollectableArtSpec(
        CollectableShape.MUSCLE_COUPE,
        Color(0xFF1B4D3E), Color(0xFFC6FF00), Color(0xFF212121),
        Color(0xFF102B22), Color(0xFF06130F), ArtPattern.SIDE_ACCENT, "AM"
    ),
    "maserati_mc20" to CollectableArtSpec(
        CollectableShape.MID_ENGINE_SUPERCAR,
        Color(0xFFECEFF1), Color(0xFF1565C0), Color(0xFF212121),
        Color(0xFF172438), Color(0xFF080F1A), ArtPattern.CARBON_AERO, "MC"
    ),
    "audi_r8_v10_gt" to CollectableArtSpec(
        CollectableShape.MID_ENGINE_SUPERCAR,
        Color(0xFF607D8B), Color(0xFFE53935), Color(0xFF212121),
        Color(0xFF22252A), Color(0xFF0E1014), ArtPattern.CARBON_AERO, "V10"
    ),
    "bentley_continental_gt" to CollectableArtSpec(
        CollectableShape.MUSCLE_COUPE,
        Color(0xFF1F3B2C), Color(0xFFD4AF37), Color(0xFFF5EFE6),
        Color(0xFF14241B), Color(0xFF07100B), ArtPattern.GOLD_LEAF, "GT"
    ),
    "porsche_911_gt3" to CollectableArtSpec(
        CollectableShape.GT3_RACE_CAR,
        Color(0xFF00ACC1), Color(0xFF212121), Color(0xFFCFD8DC),
        Color(0xFF102A33), Color(0xFF061217), ArtPattern.NONE, "GT3"
    ),
    "lambo_huracan" to CollectableArtSpec(
        CollectableShape.MID_ENGINE_SUPERCAR,
        Color(0xFF76FF03), Color(0xFF212121), Color(0xFFECEFF1),
        Color(0xFF1B3310), Color(0xFF0A1606), ArtPattern.SIDE_ACCENT, "HR"
    ),
    "mclaren_artura" to CollectableArtSpec(
        CollectableShape.ROADSTER_CONVERTIBLE,
        Color(0xFFFF6D00), Color(0xFF212121), Color(0xFFB0BEC5),
        Color(0xFF381E0A), Color(0xFF180C04), ArtPattern.CARBON_AERO, "AR"
    ),
    "ferrari_roma_spider" to CollectableArtSpec(
        CollectableShape.ROADSTER_CONVERTIBLE,
        Color(0xFF546E7A), Color(0xFF8D5524), Color(0xFFECEFF1),
        Color(0xFF1E262B), Color(0xFF0C1014), ArtPattern.NONE, "RM"
    ),
    "aston_martin_dbs_770" to CollectableArtSpec(
        CollectableShape.MUSCLE_COUPE,
        Color(0xFF5C1E26), Color(0xFFD4AF37), Color(0xFF212121),
        Color(0xFF2D1216), Color(0xFF130709), ArtPattern.GOLD_LEAF, "770"
    ),
    "mclaren_750s" to CollectableArtSpec(
        CollectableShape.MID_ENGINE_SUPERCAR,
        Color(0xFF1E88E5), Color(0xFFFF6D00), Color(0xFF212121),
        Color(0xFF14243B), Color(0xFF070F1C), ArtPattern.SIDE_ACCENT, "750"
    ),
    "porsche_911_gt3_rs" to CollectableArtSpec(
        CollectableShape.GT3_RACE_CAR,
        Color(0xFFECEFF1), Color(0xFFD50000), Color(0xFF212121),
        Color(0xFF361719), Color(0xFF18090A), ArtPattern.RACING_STRIPE, "RS"
    ),
    "ferrari_296_gtb" to CollectableArtSpec(
        CollectableShape.MID_ENGINE_SUPERCAR,
        Color(0xFFD50000), Color(0xFFFBC02D), Color(0xFF212121),
        Color(0xFF3E1010), Color(0xFF1B0606), ArtPattern.RACING_STRIPE, "296"
    ),
    "tcr_civic_race" to CollectableArtSpec(
        CollectableShape.CITY_HATCH,
        Color(0xFFD32F2F), Color(0xFFECEFF1), Color(0xFF212121),
        Color(0xFF381416), Color(0xFF180708), ArtPattern.CHECKERED, "TCR"
    ),
    "bmw_m4_gt4_race" to CollectableArtSpec(
        CollectableShape.GT3_RACE_CAR,
        Color(0xFFECEFF1), Color(0xFF1565C0), Color(0xFFD50000),
        Color(0xFF182438), Color(0xFF080F1A), ArtPattern.RACING_STRIPE, "GT4"
    ),
    "porsche_911_cup" to CollectableArtSpec(
        CollectableShape.GT3_RACE_CAR,
        Color(0xFFF5F5F5), Color(0xFFFF6D00), Color(0xFF212121),
        Color(0xFF332114), Color(0xFF160E07), ArtPattern.CHECKERED, "992"
    ),
    "nascar_nextgen_cup" to CollectableArtSpec(
        CollectableShape.GT3_RACE_CAR,
        Color(0xFF1565C0), Color(0xFFFFD600), Color(0xFFD32F2F),
        Color(0xFF12233B), Color(0xFF070F1C), ArtPattern.CHECKERED, "22"
    ),
    "rolls_royce_ghost" to CollectableArtSpec(
        CollectableShape.SEDAN_SALOON,
        Color(0xFF212121), Color(0xFFB0BEC5), Color(0xFFD4AF37),
        Color(0xFF1F2229), Color(0xFF0B0C10), ArtPattern.TWO_TONE, "RR"
    ),
    "lambo_huracan_sto" to CollectableArtSpec(
        CollectableShape.GT3_RACE_CAR,
        Color(0xFF0288D1), Color(0xFFFF6D00), Color(0xFF212121),
        Color(0xFF12283B), Color(0xFF07111B), ArtPattern.TWO_TONE, "STO"
    ),
    "rolls_royce_phantom" to CollectableArtSpec(
        CollectableShape.SEDAN_SALOON,
        Color(0xFF1A237E), Color(0xFFE0E0E0), Color(0xFFD4AF37),
        Color(0xFF141938), Color(0xFF07091A), ArtPattern.GOLD_LEAF, "VIII"
    ),
    "aston_martin_vantage_gt3" to CollectableArtSpec(
        CollectableShape.GT3_RACE_CAR,
        Color(0xFF1B5E20), Color(0xFFC6FF00), Color(0xFF212121),
        Color(0xFF122E15), Color(0xFF061408), ArtPattern.RACING_STRIPE, "007"
    ),
    "bmw_m4_gt3_evo" to CollectableArtSpec(
        CollectableShape.GT3_RACE_CAR,
        Color(0xFF212121), Color(0xFF00B0FF), Color(0xFFD50000),
        Color(0xFF162436), Color(0xFF080F1A), ArtPattern.CARBON_AERO, "32"
    ),
    "mercedes_amg_gt3_evo" to CollectableArtSpec(
        CollectableShape.GT3_RACE_CAR,
        Color(0xFFB0BEC5), Color(0xFFFBC02D), Color(0xFF212121),
        Color(0xFF262621), Color(0xFF10100D), ArtPattern.SIDE_ACCENT, "4"
    ),
    "corvette_z06_gt3r" to CollectableArtSpec(
        CollectableShape.GT3_RACE_CAR,
        Color(0xFFFFD600), Color(0xFF212121), Color(0xFFD50000),
        Color(0xFF332C0D), Color(0xFF161305), ArtPattern.RACING_STRIPE, "3"
    ),
    "porsche_911_gt3r" to CollectableArtSpec(
        CollectableShape.GT3_RACE_CAR,
        Color(0xFF76FF03), Color(0xFF212121), Color(0xFFECEFF1),
        Color(0xFF1A3310), Color(0xFF0A1606), ArtPattern.CHECKERED, "911"
    ),
    "lambo_huracan_gt3_evo2" to CollectableArtSpec(
        CollectableShape.GT3_RACE_CAR,
        Color(0xFF4CAF50), Color(0xFFECEFF1), Color(0xFFD50000),
        Color(0xFF142E16), Color(0xFF071408), ArtPattern.RACING_STRIPE, "63"
    ),
    "ferrari_296_gt3" to CollectableArtSpec(
        CollectableShape.GT3_RACE_CAR,
        Color(0xFFD50000), Color(0xFFECEFF1), Color(0xFFFFD600),
        Color(0xFF3E1010), Color(0xFF1B0606), ArtPattern.CHECKERED, "51"
    ),
    "mclaren_720s_gt3_evo" to CollectableArtSpec(
        CollectableShape.GT3_RACE_CAR,
        Color(0xFFFF6D00), Color(0xFF00ACC1), Color(0xFF212121),
        Color(0xFF361F10), Color(0xFF170D06), ArtPattern.TWO_TONE, "720"
    ),
    "ferrari_sf90_xx" to CollectableArtSpec(
        CollectableShape.GT3_RACE_CAR,
        Color(0xFF8E1622), Color(0xFFFBC02D), Color(0xFF212121),
        Color(0xFF361014), Color(0xFF170608), ArtPattern.CARBON_AERO, "XX"
    ),
    "toyota_gr_yaris_rally1" to CollectableArtSpec(
        CollectableShape.RALLY_CAR,
        Color(0xFF212121), Color(0xFFD50000), Color(0xFFECEFF1),
        Color(0xFF301616), Color(0xFF140808), ArtPattern.RACING_STRIPE, "69"
    ),
    "hyundai_i20_rally1" to CollectableArtSpec(
        CollectableShape.RALLY_CAR,
        Color(0xFF64B5F6), Color(0xFFFF5722), Color(0xFF1A237E),
        Color(0xFF18263B), Color(0xFF0A111D), ArtPattern.TWO_TONE, "11"
    ),
    "lambo_revuelto" to CollectableArtSpec(
        CollectableShape.MID_ENGINE_SUPERCAR,
        Color(0xFFFF5722), Color(0xFF212121), Color(0xFFCFD8DC),
        Color(0xFF381A10), Color(0xFF190A05), ArtPattern.CARBON_AERO, "V12"
    ),
    "mclaren_p1" to CollectableArtSpec(
        CollectableShape.MID_ENGINE_SUPERCAR,
        Color(0xFFFFC107), Color(0xFF212121), Color(0xFFFF6D00),
        Color(0xFF382A0A), Color(0xFF191204), ArtPattern.CARBON_AERO, "P1"
    ),
    "porsche_918_spyder" to CollectableArtSpec(
        CollectableShape.ROADSTER_CONVERTIBLE,
        Color(0xFFB0BEC5), Color(0xFF76FF03), Color(0xFF212121),
        Color(0xFF212A24), Color(0xFF0D120F), ArtPattern.SIDE_ACCENT, "918"
    ),
    "ford_gt_mk_iv" to CollectableArtSpec(
        CollectableShape.GT3_RACE_CAR,
        Color(0xFF1565C0), Color(0xFFECEFF1), Color(0xFFD50000),
        Color(0xFF12233B), Color(0xFF070F1C), ArtPattern.RACING_STRIPE, "67"
    ),
    "ferrari_f40" to CollectableArtSpec(
        CollectableShape.MID_ENGINE_SUPERCAR,
        Color(0xFFE53935), Color(0xFFCFD8DC), Color(0xFF212121),
        Color(0xFF401010), Color(0xFF1C0505), ArtPattern.NONE, "F40"
    ),
    "ferrari_laferrari" to CollectableArtSpec(
        CollectableShape.MID_ENGINE_SUPERCAR,
        Color(0xFFC62828), Color(0xFF1C1F24), Color(0xFFFFD600),
        Color(0xFF3B1012), Color(0xFF190607), ArtPattern.TWO_TONE, "LF"
    ),
    "rimac_nevera_r" to CollectableArtSpec(
        CollectableShape.MID_ENGINE_SUPERCAR,
        Color(0xFF00ACC1), Color(0xFFECEFF1), Color(0xFF212121),
        Color(0xFF102B33), Color(0xFF061318), ArtPattern.CARBON_AERO, "NV"
    ),
    "pagani_utopia" to CollectableArtSpec(
        CollectableShape.MID_ENGINE_SUPERCAR,
        Color(0xFFD7C8B0), Color(0xFF5D4037), Color(0xFFD4AF37),
        Color(0xFF2E261F), Color(0xFF14100C), ArtPattern.GOLD_LEAF, "UT"
    ),
    "aston_martin_valkyrie_amr" to CollectableArtSpec(
        CollectableShape.LE_MANS_PROTOTYPE,
        Color(0xFF1B5E20), Color(0xFF00E676), Color(0xFF212121),
        Color(0xFF102B16), Color(0xFF061309), ArtPattern.CARBON_AERO, "AMR"
    ),
    "koenigsegg_jesko_absolut" to CollectableArtSpec(
        CollectableShape.MID_ENGINE_SUPERCAR,
        Color(0xFF37474F), Color(0xFFFF6D00), Color(0xFFCFD8DC),
        Color(0xFF26201C), Color(0xFF100D0B), ArtPattern.CARBON_AERO, "330"
    ),
    "bugatti_chiron_ss" to CollectableArtSpec(
        CollectableShape.MID_ENGINE_SUPERCAR,
        Color(0xFF1C1F24), Color(0xFFFF6D00), Color(0xFF00B0FF),
        Color(0xFF291D16), Color(0xFF120C08), ArtPattern.RACING_STRIPE, "300"
    ),
    "indycar_dallara_dw12" to CollectableArtSpec(
        CollectableShape.FORMULA_OPEN_WHEEL,
        Color(0xFFE53935), Color(0xFFFFD600), Color(0xFF1565C0),
        Color(0xFF361614), Color(0xFF170807), ArtPattern.RACING_STRIPE, "10"
    ),
    "porsche_963_lmdh" to CollectableArtSpec(
        CollectableShape.LE_MANS_PROTOTYPE,
        Color(0xFFD50000), Color(0xFFECEFF1), Color(0xFF212121),
        Color(0xFF3B1214), Color(0xFF190608), ArtPattern.RACING_STRIPE, "5"
    ),
    "toyota_gr010_hybrid" to CollectableArtSpec(
        CollectableShape.LE_MANS_PROTOTYPE,
        Color(0xFF1C1F24), Color(0xFFD50000), Color(0xFFECEFF1),
        Color(0xFF2E1416), Color(0xFF140708), ArtPattern.CHECKERED, "7"
    ),
    "ferrari_499p_lemans" to CollectableArtSpec(
        CollectableShape.LE_MANS_PROTOTYPE,
        Color(0xFFD50000), Color(0xFFFFD600), Color(0xFF212121),
        Color(0xFF401010), Color(0xFF1C0505), ArtPattern.RACING_STRIPE, "51"
    ),
    "bugatti_bolide" to CollectableArtSpec(
        CollectableShape.LE_MANS_PROTOTYPE,
        Color(0xFF0091EA), Color(0xFF1C1F24), Color(0xFFECEFF1),
        Color(0xFF102438), Color(0xFF060F1A), ArtPattern.CARBON_AERO, "16"
    ),
    "pagani_zonda_hp" to CollectableArtSpec(
        CollectableShape.ROADSTER_CONVERTIBLE,
        Color(0xFF1565C0), Color(0xFFECEFF1), Color(0xFFD50000),
        Color(0xFF122138), Color(0xFF070E1A), ArtPattern.CARBON_AERO, "HP"
    ),
    "mclaren_f1_gtr" to CollectableArtSpec(
        CollectableShape.VINTAGE_RACER,
        Color(0xFF263238), Color(0xFFCFD8DC), Color(0xFFFF6D00),
        Color(0xFF212529), Color(0xFF0D0F12), ArtPattern.CHECKERED, "59"
    ),
    "mclaren_mcl38_f1" to CollectableArtSpec(
        CollectableShape.FORMULA_OPEN_WHEEL,
        Color(0xFFFF6D00), Color(0xFF212121), Color(0xFF00E5FF),
        Color(0xFF381F0C), Color(0xFF180D04), ArtPattern.CARBON_AERO, "4"
    ),
    "ferrari_sf24_f1" to CollectableArtSpec(
        CollectableShape.FORMULA_OPEN_WHEEL,
        Color(0xFFD50000), Color(0xFFFFD600), Color(0xFFECEFF1),
        Color(0xFF3E1010), Color(0xFF1A0505), ArtPattern.RACING_STRIPE, "16"
    ),
    "redbull_rb19_f1" to CollectableArtSpec(
        CollectableShape.FORMULA_OPEN_WHEEL,
        Color(0xFF1A237E), Color(0xFFFFD600), Color(0xFFD50000),
        Color(0xFF13183B), Color(0xFF07091C), ArtPattern.RACING_STRIPE, "1"
    ),
    "mercedes_w11_f1" to CollectableArtSpec(
        CollectableShape.FORMULA_OPEN_WHEEL,
        Color(0xFF1C1F24), Color(0xFF00BFA5), Color(0xFFD50000),
        Color(0xFF102826), Color(0xFF061211), ArtPattern.SIDE_ACCENT, "44"
    ),
    "ferrari_f2004_f1" to CollectableArtSpec(
        CollectableShape.FORMULA_OPEN_WHEEL,
        Color(0xFFE53935), Color(0xFFECEFF1), Color(0xFF212121),
        Color(0xFF3B1212), Color(0xFF190606), ArtPattern.CHECKERED, "1"
    ),
    "ferrari_330_p4" to CollectableArtSpec(
        CollectableShape.VINTAGE_RACER,
        Color(0xFFD50000), Color(0xFFECEFF1), Color(0xFFD4AF37),
        Color(0xFF3E1010), Color(0xFF1B0606), ArtPattern.GOLD_LEAF, "23"
    ),
    "ferrari_250_gto" to CollectableArtSpec(
        CollectableShape.VINTAGE_RACER,
        Color(0xFFB71C1C), Color(0xFFECEFF1), Color(0xFF1565C0),
        Color(0xFF381012), Color(0xFF180506), ArtPattern.RACING_STRIPE, "19"
    ),
    "mercedes_300_slr" to CollectableArtSpec(
        CollectableShape.VINTAGE_RACER,
        Color(0xFFCFD8DC), Color(0xFF1565C0), Color(0xFFD32F2F),
        Color(0xFF212830), Color(0xFF0D1116), ArtPattern.GOLD_LEAF, "722"
    ),
)
