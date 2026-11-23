package com.example.ui.art

import androidx.compose.ui.graphics.Color

internal val motorbikeArtSpecs: Map<String, CollectableArtSpec> = mapOf(
    "honda_navi" to CollectableArtSpec(
        CollectableShape.MINI_MOTO,
        Color(0xFFE53935), Color(0xFF263238), Color(0xFFECEFF1),
        Color(0xFF2B1B1D), Color(0xFF120A0C), ArtPattern.SIDE_ACCENT, "110"
    ),
    "bajaj_boxer_150" to CollectableArtSpec(
        CollectableShape.RETRO_ROADSTER,
        Color(0xFF1E3A5F), Color(0xFFB0BEC5), Color(0xFF263238),
        Color(0xFF142235), Color(0xFF080F19), ArtPattern.NONE, "150"
    ),
    "hero_splendor_plus" to CollectableArtSpec(
        CollectableShape.RETRO_ROADSTER,
        Color(0xFF262930), Color(0xFFD32F2F), Color(0xFFCFD8DC),
        Color(0xFF23252C), Color(0xFF0E1014), ArtPattern.SIDE_ACCENT, "SP"
    ),
    "tvs_apache_rtr160" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFFECEFF1), Color(0xFFD32F2F), Color(0xFF212121),
        Color(0xFF242830), Color(0xFF101318), ArtPattern.RACING_STRIPE, "160"
    ),
    "honda_dax_125" to CollectableArtSpec(
        CollectableShape.MINI_MOTO,
        Color(0xFF607D8B), Color(0xFFDADFE3), Color(0xFF5D4037),
        Color(0xFF222D33), Color(0xFF0E1418), ArtPattern.TWO_TONE, "DX"
    ),
    "honda_grom_125" to CollectableArtSpec(
        CollectableShape.MINI_MOTO,
        Color(0xFFFBC02D), Color(0xFF212121), Color(0xFFCFD8DC),
        Color(0xFF33290F), Color(0xFF161105), ArtPattern.TWO_TONE, "GR"
    ),
    "suzuki_burgman_125" to CollectableArtSpec(
        CollectableShape.SCOOTER,
        Color(0xFF37474F), Color(0xFF90A4AE), Color(0xFFECEFF1),
        Color(0xFF1D262B), Color(0xFF0C1114), ArtPattern.NONE, "BG"
    ),
    "yamaha_nmax_155" to CollectableArtSpec(
        CollectableShape.SCOOTER,
        Color(0xFF1565C0), Color(0xFF212121), Color(0xFF90CAF9),
        Color(0xFF11243E), Color(0xFF07101E), ArtPattern.SIDE_ACCENT, "NM"
    ),
    "royal_enfield_hunter" to CollectableArtSpec(
        CollectableShape.RETRO_ROADSTER,
        Color(0xFF455A64), Color(0xFFECEFF1), Color(0xFF1565C0),
        Color(0xFF1C262B), Color(0xFF0B1014), ArtPattern.TWO_TONE, "350"
    ),
    "honda_super_cub" to CollectableArtSpec(
        CollectableShape.SCOOTER,
        Color(0xFF284B7E), Color(0xFFF5EFE6), Color(0xFF8D3B2B),
        Color(0xFF192840), Color(0xFF09111E), ArtPattern.TWO_TONE, "C125"
    ),
    "honda_monkey_125" to CollectableArtSpec(
        CollectableShape.MINI_MOTO,
        Color(0xFFFFB300), Color(0xFFF5F5F5), Color(0xFFD8D8D8),
        Color(0xFF332609), Color(0xFF171003), ArtPattern.TWO_TONE, "MK"
    ),
    "vespa_primavera_150" to CollectableArtSpec(
        CollectableShape.SCOOTER,
        Color(0xFF66BB9A), Color(0xFFD7CCC8), Color(0xFFECEFF1),
        Color(0xFF18362B), Color(0xFF091913), ArtPattern.NONE, "VP"
    ),
    "suzuki_gixxer_250" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFF1976D2), Color(0xFFB0BEC5), Color(0xFFD4E157),
        Color(0xFF12273D), Color(0xFF07111C), ArtPattern.SIDE_ACCENT, "250"
    ),
    "royal_enfield_classic350" to CollectableArtSpec(
        CollectableShape.RETRO_ROADSTER,
        Color(0xFF2E4D36), Color(0xFFD4AF37), Color(0xFF6D4C41),
        Color(0xFF15271A), Color(0xFF08120B), ArtPattern.GOLD_LEAF, "CL"
    ),
    "kawasaki_klx230" to CollectableArtSpec(
        CollectableShape.ADVENTURE_ENDURO,
        Color(0xFF43A047), Color(0xFF212121), Color(0xFFECEFF1),
        Color(0xFF163319), Color(0xFF08180A), ArtPattern.SIDE_ACCENT, "230"
    ),
    "yamaha_mt03" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFF263238), Color(0xFF00ACC1), Color(0xFFD32F2F),
        Color(0xFF142429), Color(0xFF081014), ArtPattern.SIDE_ACCENT, "MT3"
    ),
    "bmw_g310r" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFFECEFF1), Color(0xFF1E88E5), Color(0xFFE53935),
        Color(0xFF1B2433), Color(0xFF0B1018), ArtPattern.RACING_STRIPE, "310"
    ),
    "ktm_duke_390" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFFFF6D00), Color(0xFF1565C0), Color(0xFF212121),
        Color(0xFF381E0A), Color(0xFF1A0D04), ArtPattern.TWO_TONE, "390"
    ),
    "honda_cb300r" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFF1E2D40), Color(0xFFB78B46), Color(0xFFCFD8DC),
        Color(0xFF121D2B), Color(0xFF070C14), ArtPattern.NONE, "CB3"
    ),
    "kawasaki_ninja_400" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFF4CAF50), Color(0xFF1C1F24), Color(0xFFE53935),
        Color(0xFF163018), Color(0xFF081509), ArtPattern.RACING_STRIPE, "400"
    ),
    "yamaha_yzf_r3" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFF1E44B8), Color(0xFF00ACC1), Color(0xFF212121),
        Color(0xFF101D3D), Color(0xFF060C1E), ArtPattern.SIDE_ACCENT, "R3"
    ),
    "honda_crf300l" to CollectableArtSpec(
        CollectableShape.ADVENTURE_ENDURO,
        Color(0xFFD32F2F), Color(0xFFF5F5F5), Color(0xFF1565C0),
        Color(0xFF381515), Color(0xFF190808), ArtPattern.RACING_STRIPE, "CRF"
    ),
    "honda_rebel_500" to CollectableArtSpec(
        CollectableShape.CRUISER_CHOPPER,
        Color(0xFF2B2D33), Color(0xFF757B87), Color(0xFF8D6E63),
        Color(0xFF1E2025), Color(0xFF0B0C0F), ArtPattern.NONE, "500"
    ),
    "kawasaki_eliminator_450" to CollectableArtSpec(
        CollectableShape.CRUISER_CHOPPER,
        Color(0xFFCFD8DC), Color(0xFF212121), Color(0xFF4CAF50),
        Color(0xFF22282B), Color(0xFF0E1214), ArtPattern.SIDE_ACCENT, "450"
    ),
    "triumph_speed_400" to CollectableArtSpec(
        CollectableShape.RETRO_ROADSTER,
        Color(0xFFA82026), Color(0xFFCFD8DC), Color(0xFF1F2329),
        Color(0xFF331114), Color(0xFF170608), ArtPattern.TWO_TONE, "T4"
    ),
    "royal_enfield_int650" to CollectableArtSpec(
        CollectableShape.RETRO_ROADSTER,
        Color(0xFFD96426), Color(0xFFE0E0E0), Color(0xFF212121),
        Color(0xFF361E10), Color(0xFF190C05), ArtPattern.TWO_TONE, "650"
    ),
    "suzuki_sv650" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFF2B313B), Color(0xFFC62828), Color(0xFFB0BEC5),
        Color(0xFF1F2127), Color(0xFF0B0C10), ArtPattern.SIDE_ACCENT, "SV"
    ),
    "kawasaki_z650" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFF37474F), Color(0xFF64DD17), Color(0xFF212121),
        Color(0xFF192420), Color(0xFF09100E), ArtPattern.SIDE_ACCENT, "Z6"
    ),
    "vespa_gts_300" to CollectableArtSpec(
        CollectableShape.SCOOTER,
        Color(0xFF1A3A6E), Color(0xFF8D5524), Color(0xFFCFD8DC),
        Color(0xFF12213B), Color(0xFF070E1C), ArtPattern.NONE, "GTS"
    ),
    "yamaha_mt07" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFF1E2229), Color(0xFF00B8D4), Color(0xFF455A64),
        Color(0xFF132229), Color(0xFF070F14), ArtPattern.TWO_TONE, "MT7"
    ),
    "suzuki_vstrom_650" to CollectableArtSpec(
        CollectableShape.ADVENTURE_ENDURO,
        Color(0xFFFBC02D), Color(0xFF263238), Color(0xFFB0BEC5),
        Color(0xFF33290F), Color(0xFF161105), ArtPattern.TWO_TONE, "VS"
    ),
    "honda_cb650r" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFF8E1B24), Color(0xFFC59B43), Color(0xFF212121),
        Color(0xFF331114), Color(0xFF160608), ArtPattern.NONE, "650"
    ),
    "triumph_trident_660" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFFECEFF1), Color(0xFFEF5350), Color(0xFF263238),
        Color(0xFF252A30), Color(0xFF0F1216), ArtPattern.SIDE_ACCENT, "660"
    ),
    "kawasaki_versys_650" to CollectableArtSpec(
        CollectableShape.ADVENTURE_ENDURO,
        Color(0xFF388E3C), Color(0xFF263238), Color(0xFF90A4AE),
        Color(0xFF152918), Color(0xFF08120A), ArtPattern.TWO_TONE, "VR"
    ),
    "yamaha_tenere_700" to CollectableArtSpec(
        CollectableShape.ADVENTURE_ENDURO,
        Color(0xFF1958C7), Color(0xFFF5F5F5), Color(0xFF212121),
        Color(0xFF10203E), Color(0xFF060E1E), ArtPattern.RACING_STRIPE, "T7"
    ),
    "harley_iron_883" to CollectableArtSpec(
        CollectableShape.CRUISER_CHOPPER,
        Color(0xFF212121), Color(0xFFD88424), Color(0xFF616161),
        Color(0xFF241E1A), Color(0xFF0E0B09), ArtPattern.NONE, "883"
    ),
    "kawasaki_z900" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFF21252B), Color(0xFFC62828), Color(0xFF76FF03),
        Color(0xFF23181A), Color(0xFF0F090A), ArtPattern.RACING_STRIPE, "Z9"
    ),
    "yamaha_mt09" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFF1565C0), Color(0xFF00E5FF), Color(0xFF1C1F24),
        Color(0xFF0F2138), Color(0xFF060E1A), ArtPattern.SIDE_ACCENT, "MT9"
    ),
    "honda_transalp_750" to CollectableArtSpec(
        CollectableShape.ADVENTURE_ENDURO,
        Color(0xFFF5F5F5), Color(0xFF1565C0), Color(0xFFD32F2F),
        Color(0xFF192438), Color(0xFF0A101B), ArtPattern.RACING_STRIPE, "750"
    ),
    "triumph_bonneville_t120" to CollectableArtSpec(
        CollectableShape.RETRO_ROADSTER,
        Color(0xFF1B3B2B), Color(0xFFE8D8B8), Color(0xFFCFD8DC),
        Color(0xFF13261C), Color(0xFF07110B), ArtPattern.TWO_TONE, "T120"
    ),
    "kawasaki_ninja_zx6r" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFF43A047), Color(0xFF1B1E23), Color(0xFFFFD600),
        Color(0xFF132E15), Color(0xFF071408), ArtPattern.RACING_STRIPE, "636"
    ),
    "honda_cbr600rr" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFFD50000), Color(0xFF1976D2), Color(0xFFF5F5F5),
        Color(0xFF3A1214), Color(0xFF190608), ArtPattern.RACING_STRIPE, "600"
    ),
    "yamaha_yzf_r7" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFF1E3FA8), Color(0xFF4FC3F7), Color(0xFF212121),
        Color(0xFF101B3A), Color(0xFF060B1A), ArtPattern.NONE, "R7"
    ),
    "ducati_scrambler_800" to CollectableArtSpec(
        CollectableShape.RETRO_ROADSTER,
        Color(0xFFFFC107), Color(0xFF212121), Color(0xFFB0BEC5),
        Color(0xFF382C0C), Color(0xFF1A1405), ArtPattern.TWO_TONE, "800"
    ),
    "ktm_890_duke_r" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFFF5F5F5), Color(0xFFFF6D00), Color(0xFF212121),
        Color(0xFF38210E), Color(0xFF190E05), ArtPattern.RACING_STRIPE, "890"
    ),
    "aprilia_rs660" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFFE65100), Color(0xFF6A1B9A), Color(0xFF212121),
        Color(0xFF381A22), Color(0xFF180A0E), ArtPattern.TWO_TONE, "660"
    ),
    "indian_scout_bobber" to CollectableArtSpec(
        CollectableShape.CRUISER_CHOPPER,
        Color(0xFF7F2A22), Color(0xFF212121), Color(0xFF8D6E63),
        Color(0xFF301412), Color(0xFF150806), ArtPattern.NONE, "SC"
    ),
    "bmw_f900gs" to CollectableArtSpec(
        CollectableShape.ADVENTURE_ENDURO,
        Color(0xFFFBC02D), Color(0xFF263238), Color(0xFF1E88E5),
        Color(0xFF2E2914), Color(0xFF141208), ArtPattern.SIDE_ACCENT, "900"
    ),
    "triumph_street_triple_rs" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFFFFCA28), Color(0xFF263238), Color(0xFFE53935),
        Color(0xFF33290E), Color(0xFF171205), ArtPattern.RACING_STRIPE, "765"
    ),
    "honda_africa_twin" to CollectableArtSpec(
        CollectableShape.ADVENTURE_ENDURO,
        Color(0xFFECEFF1), Color(0xFF1565C0), Color(0xFFD32F2F),
        Color(0xFF162438), Color(0xFF08101C), ArtPattern.GOLD_LEAF, "AT"
    ),
    "ducati_monster_plus" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFFC62828), Color(0xFF212121), Color(0xFFB0BEC5),
        Color(0xFF361010), Color(0xFF180606), ArtPattern.NONE, "MN"
    ),
    "suzuki_hayabusa" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFF212121), Color(0xFFC59B38), Color(0xFFB0BEC5),
        Color(0xFF262116), Color(0xFF100E09), ArtPattern.GOLD_LEAF, "HY"
    ),
    "suzuki_gsxr_750" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFF1565C0), Color(0xFFF5F5F5), Color(0xFFD4E157),
        Color(0xFF10243E), Color(0xFF060F1D), ArtPattern.RACING_STRIPE, "750"
    ),
    "yamaha_yzf_r6_race" to CollectableArtSpec(
        CollectableShape.MOTOGP_PROTOTYPE,
        Color(0xFF1537A8), Color(0xFFCFD8DC), Color(0xFFFFCA28),
        Color(0xFF101C3B), Color(0xFF060B1A), ArtPattern.CHECKERED, "R6"
    ),
    "ducati_panigale_v2" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFFF5F5F5), Color(0xFFD50000), Color(0xFF212121),
        Color(0xFF36181A), Color(0xFF18090B), ArtPattern.SIDE_ACCENT, "V2"
    ),
    "suzuki_gsxr_1000r" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFF00ACC1), Color(0xFF1565C0), Color(0xFFDCE775),
        Color(0xFF0E2936), Color(0xFF05131B), ArtPattern.RACING_STRIPE, "1K"
    ),
    "kawasaki_ninja_zx10r" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFF388E3C), Color(0xFF212121), Color(0xFFECEFF1),
        Color(0xFF132D15), Color(0xFF071308), ArtPattern.CHECKERED, "10R"
    ),
    "bmw_s1000rr" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFFECEFF1), Color(0xFF1565C0), Color(0xFFD32F2F),
        Color(0xFF1A2538), Color(0xFF090F1A), ArtPattern.RACING_STRIPE, "RR"
    ),
    "yamaha_r1" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFF193CA8), Color(0xFF263238), Color(0xFF00E5FF),
        Color(0xFF101D3C), Color(0xFF060C1C), ArtPattern.TWO_TONE, "R1"
    ),
    "honda_cbr1000rr_sp" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFFD32F2F), Color(0xFF1565C0), Color(0xFFFFD54F),
        Color(0xFF3A1414), Color(0xFF1A0707), ArtPattern.RACING_STRIPE, "SP"
    ),
    "bmw_r1300gs" to CollectableArtSpec(
        CollectableShape.ADVENTURE_ENDURO,
        Color(0xFF1E56A0), Color(0xFFECEFF1), Color(0xFFD32F2F),
        Color(0xFF12233B), Color(0xFF070F1C), ArtPattern.RACING_STRIPE, "GS"
    ),
    "harley_fatboy" to CollectableArtSpec(
        CollectableShape.CRUISER_CHOPPER,
        Color(0xFFCFD8DC), Color(0xFF212121), Color(0xFFB0BEC5),
        Color(0xFF22272D), Color(0xFF0E1115), ArtPattern.TWO_TONE, "114"
    ),
    "ktm_1390_super_duke" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFFFF6D00), Color(0xFF212121), Color(0xFFECEFF1),
        Color(0xFF3B1D08), Color(0xFF1B0C03), ArtPattern.CARBON_AERO, "1390"
    ),
    "triumph_rocket_3r" to CollectableArtSpec(
        CollectableShape.CRUISER_CHOPPER,
        Color(0xFF212121), Color(0xFFC62828), Color(0xFFCFD8DC),
        Color(0xFF2B1416), Color(0xFF120708), ArtPattern.SIDE_ACCENT, "R3"
    ),
    "ducati_multistrada_v4s" to CollectableArtSpec(
        CollectableShape.ADVENTURE_ENDURO,
        Color(0xFFC62828), Color(0xFF37474F), Color(0xFFCFD8DC),
        Color(0xFF361214), Color(0xFF180708), ArtPattern.SIDE_ACCENT, "V4S"
    ),
    "harley_road_glide" to CollectableArtSpec(
        CollectableShape.BAGGER_TOURER,
        Color(0xFF263238), Color(0xFFD84315), Color(0xFFCFD8DC),
        Color(0xFF2A1D1B), Color(0xFF130B0A), ArtPattern.SIDE_ACCENT, "RG"
    ),
    "indian_challenger_dark" to CollectableArtSpec(
        CollectableShape.BAGGER_TOURER,
        Color(0xFF212121), Color(0xFF795548), Color(0xFFB0BEC5),
        Color(0xFF211C1A), Color(0xFF0D0A09), ArtPattern.NONE, "CH"
    ),
    "honda_gold_wing_tour" to CollectableArtSpec(
        CollectableShape.BAGGER_TOURER,
        Color(0xFFECEFF1), Color(0xFF37474F), Color(0xFFC62828),
        Color(0xFF222830), Color(0xFF0E1216), ArtPattern.TWO_TONE, "GW"
    ),
    "kawasaki_ninja_h2_sx" to CollectableArtSpec(
        CollectableShape.BAGGER_TOURER,
        Color(0xFF2E7D32), Color(0xFF1B1E23), Color(0xFFB0BEC5),
        Color(0xFF112914), Color(0xFF061308), ArtPattern.CARBON_AERO, "SX"
    ),
    "yamaha_r1m" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFF37474F), Color(0xFF1565C0), Color(0xFFE6C256),
        Color(0xFF172230), Color(0xFF090F17), ArtPattern.CARBON_AERO, "R1M"
    ),
    "aprilia_rsv4_factory" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFF1C1F24), Color(0xFFE53935), Color(0xFF7E57C2),
        Color(0xFF281726), Color(0xFF110911), ArtPattern.RACING_STRIPE, "V4"
    ),
    "ducati_streetfighter_v4s" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFFD50000), Color(0xFF1C1F24), Color(0xFFCFD8DC),
        Color(0xFF3A1012), Color(0xFF190607), ArtPattern.CARBON_AERO, "SF"
    ),
    "ducati_panigale_v4" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFFD50000), Color(0xFF212121), Color(0xFFE5C158),
        Color(0xFF3E1010), Color(0xFF1B0606), ArtPattern.TWO_TONE, "V4S"
    ),
    "kawasaki_zx10rr" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFF64DD17), Color(0xFF1C1F24), Color(0xFFECEFF1),
        Color(0xFF193311), Color(0xFF091606), ArtPattern.RACING_STRIPE, "10RR"
    ),
    "kawasaki_ninja_h2" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFF455A64), Color(0xFF1B1E23), Color(0xFF4CAF50),
        Color(0xFF192420), Color(0xFF0A100E), ArtPattern.CARBON_AERO, "H2"
    ),
    "bmw_m1000r" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFF212121), Color(0xFF1E88E5), Color(0xFFE53935),
        Color(0xFF162238), Color(0xFF080E1A), ArtPattern.RACING_STRIPE, "MR"
    ),
    "bmw_k1600_gtl" to CollectableArtSpec(
        CollectableShape.BAGGER_TOURER,
        Color(0xFF1E3A5F), Color(0xFFB0BEC5), Color(0xFFCFD8DC),
        Color(0xFF132236), Color(0xFF070F1A), ArtPattern.NONE, "K16"
    ),
    "mv_agusta_rush_1000" to CollectableArtSpec(
        CollectableShape.NAKED_STREETFIGHTER,
        Color(0xFF455A64), Color(0xFFFFD54F), Color(0xFFD32F2F),
        Color(0xFF2A211C), Color(0xFF130E0B), ArtPattern.GOLD_LEAF, "RS"
    ),
    "bmw_m1000rr" to CollectableArtSpec(
        CollectableShape.MOTOGP_PROTOTYPE,
        Color(0xFFECEFF1), Color(0xFF1E88E5), Color(0xFFD32F2F),
        Color(0xFF182438), Color(0xFF080F1A), ArtPattern.RACING_STRIPE, "50"
    ),
    "harley_cvo_road_glide" to CollectableArtSpec(
        CollectableShape.BAGGER_TOURER,
        Color(0xFFB71C1C), Color(0xFF212121), Color(0xFFD4AF37),
        Color(0xFF361212), Color(0xFF180606), ArtPattern.GOLD_LEAF, "CVO"
    ),
    "ducati_panigale_v4r" to CollectableArtSpec(
        CollectableShape.MOTOGP_PROTOTYPE,
        Color(0xFFD50000), Color(0xFFB0BEC5), Color(0xFF212121),
        Color(0xFF3E1010), Color(0xFF1B0606), ArtPattern.TWO_TONE, "V4R"
    ),
    "bimota_tesi_h2" to CollectableArtSpec(
        CollectableShape.CUSTOM_TURBINE,
        Color(0xFFECEFF1), Color(0xFFD32F2F), Color(0xFF388E3C),
        Color(0xFF242429), Color(0xFF101014), ArtPattern.CARBON_AERO, "TH2"
    ),
    "mv_agusta_f4_rc" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFFC62828), Color(0xFFECEFF1), Color(0xFF2E7D32),
        Color(0xFF331418), Color(0xFF17070A), ArtPattern.RACING_STRIPE, "37"
    ),
    "kawasaki_ninja_h2r" to CollectableArtSpec(
        CollectableShape.MOTOGP_PROTOTYPE,
        Color(0xFF21262D), Color(0xFF76FF03), Color(0xFFCFD8DC),
        Color(0xFF122816), Color(0xFF061209), ArtPattern.CARBON_AERO, "H2R"
    ),
    "arch_krgt1" to CollectableArtSpec(
        CollectableShape.CRUISER_CHOPPER,
        Color(0xFF546E7A), Color(0xFFD4A646), Color(0xFF212121),
        Color(0xFF21262B), Color(0xFF0E1013), ArtPattern.NONE, "KR"
    ),
    "ducati_superleggera_v4" to CollectableArtSpec(
        CollectableShape.MOTOGP_PROTOTYPE,
        Color(0xFF262629), Color(0xFFFF1744), Color(0xFFECEFF1),
        Color(0xFF381018), Color(0xFF190609), ArtPattern.CARBON_AERO, "SL"
    ),
    "norton_v4sv_manx" to CollectableArtSpec(
        CollectableShape.SUPERSPORT_FAIRING,
        Color(0xFFB0BEC5), Color(0xFFD4AF37), Color(0xFF212121),
        Color(0xFF24262B), Color(0xFF101114), ArtPattern.GOLD_LEAF, "TT"
    ),
    "suter_mmx_500" to CollectableArtSpec(
        CollectableShape.MOTOGP_PROTOTYPE,
        Color(0xFFECEFF1), Color(0xFFB71C1C), Color(0xFF37474F),
        Color(0xFF2E1719), Color(0xFF14080A), ArtPattern.CHECKERED, "500"
    ),
    "kramer_gp2_890rr" to CollectableArtSpec(
        CollectableShape.MOTOGP_PROTOTYPE,
        Color(0xFFFF6D00), Color(0xFFECEFF1), Color(0xFF263238),
        Color(0xFF381F0C), Color(0xFF190D04), ArtPattern.RACING_STRIPE, "89"
    ),
    "kalex_moto2_factory" to CollectableArtSpec(
        CollectableShape.MOTOGP_PROTOTYPE,
        Color(0xFF1565C0), Color(0xFFFFD600), Color(0xFFECEFF1),
        Color(0xFF10223B), Color(0xFF060E1C), ArtPattern.CHECKERED, "M2"
    ),
    "bos_hos_v8_cruiser" to CollectableArtSpec(
        CollectableShape.CRUISER_CHOPPER,
        Color(0xFF1976D2), Color(0xFFCFD8DC), Color(0xFF212121),
        Color(0xFF142236), Color(0xFF070F1A), ArtPattern.RACING_STRIPE, "V8"
    ),
    "aston_martin_amb001" to CollectableArtSpec(
        CollectableShape.CUSTOM_TURBINE,
        Color(0xFF1B4D3E), Color(0xFFCDDC39), Color(0xFFCFD8DC),
        Color(0xFF0E2921), Color(0xFF05130F), ArtPattern.CARBON_AERO, "AM"
    ),
    "honda_rc213v_s" to CollectableArtSpec(
        CollectableShape.MOTOGP_PROTOTYPE,
        Color(0xFFECEFF1), Color(0xFFD50000), Color(0xFF1565C0),
        Color(0xFF34161A), Color(0xFF17080A), ArtPattern.TWO_TONE, "213"
    ),
    "combat_wraith_p51" to CollectableArtSpec(
        CollectableShape.CUSTOM_TURBINE,
        Color(0xFF78909C), Color(0xFF263238), Color(0xFFB0BEC5),
        Color(0xFF20262B), Color(0xFF0D1014), ArtPattern.NONE, "P51"
    ),
    "mtt_420rr_turbine" to CollectableArtSpec(
        CollectableShape.CUSTOM_TURBINE,
        Color(0xFF263238), Color(0xFF00B0FF), Color(0xFFCFD8DC),
        Color(0xFF112333), Color(0xFF060F18), ArtPattern.CARBON_AERO, "420"
    ),
    "suzuki_gsx_rr_motogp" to CollectableArtSpec(
        CollectableShape.MOTOGP_PROTOTYPE,
        Color(0xFF1E88E5), Color(0xFFB0BEC5), Color(0xFFECEFF1),
        Color(0xFF12253B), Color(0xFF07101C), ArtPattern.TWO_TONE, "36"
    ),
    "ktm_rc16_motogp" to CollectableArtSpec(
        CollectableShape.MOTOGP_PROTOTYPE,
        Color(0xFF1A237E), Color(0xFFFF6D00), Color(0xFFFFD600),
        Color(0xFF1B1B3B), Color(0xFF0A0A1C), ArtPattern.RACING_STRIPE, "33"
    ),
    "aprilia_rs_gp_factory" to CollectableArtSpec(
        CollectableShape.MOTOGP_PROTOTYPE,
        Color(0xFF1C1F24), Color(0xFF8E24AA), Color(0xFFE53935),
        Color(0xFF29162E), Color(0xFF120815), ArtPattern.CARBON_AERO, "41"
    ),
    "yamaha_yzr_m1_motogp" to CollectableArtSpec(
        CollectableShape.MOTOGP_PROTOTYPE,
        Color(0xFF153DA8), Color(0xFF212121), Color(0xFF76FF03),
        Color(0xFF101D3B), Color(0xFF060C1C), ArtPattern.SIDE_ACCENT, "20"
    ),
    "honda_rc213v_factory" to CollectableArtSpec(
        CollectableShape.MOTOGP_PROTOTYPE,
        Color(0xFFFF6F00), Color(0xFFD50000), Color(0xFFECEFF1),
        Color(0xFF3B1B0C), Color(0xFF1B0B04), ArtPattern.RACING_STRIPE, "93"
    ),
    "ducati_desmosedici_gp" to CollectableArtSpec(
        CollectableShape.MOTOGP_PROTOTYPE,
        Color(0xFFD50000), Color(0xFF212121), Color(0xFFFFD600),
        Color(0xFF420D0D), Color(0xFF1D0404), ArtPattern.CARBON_AERO, "1"
    ),
    "mv_agusta_superveloce_1000" to CollectableArtSpec(
        CollectableShape.RETRO_ROADSTER,
        Color(0xFFB71C1C), Color(0xFFCFD8DC), Color(0xFFD4AF37),
        Color(0xFF331416), Color(0xFF170708), ArtPattern.GOLD_LEAF, "1000"
    ),
    "bimota_kb4_rc" to CollectableArtSpec(
        CollectableShape.RETRO_ROADSTER,
        Color(0xFFECEFF1), Color(0xFFC62828), Color(0xFF2E7D32),
        Color(0xFF24292B), Color(0xFF0F1214), ArtPattern.TWO_TONE, "KB4"
    ),
    "vincent_black_lightning" to CollectableArtSpec(
        CollectableShape.RETRO_ROADSTER,
        Color(0xFF18191C), Color(0xFFD4AF37), Color(0xFFCFD8DC),
        Color(0xFF221E18), Color(0xFF0E0C09), ArtPattern.GOLD_LEAF, "1951"
    ),
)
