package com.example.data

import com.example.R
import com.example.model.PrizeCategory
import com.example.model.PrizeItem

object PrizeCatalog {
    val items: List<PrizeItem> = listOf(
        // Gourmet Food (accessible early rewards)
        PrizeItem(
            id = "neapolitan_pizza",
            title = "Neapolitan Pizza",
            subtitle = "Wood-fired Margherita",
            category = PrizeCategory.FOOD,
            price = 3,
            imageRes = R.drawable.img_prize_pizza,
        ),
        PrizeItem(
            id = "tonkotsu_ramen",
            title = "Tonkotsu Ramen",
            subtitle = "Rich broth & chashu",
            category = PrizeCategory.FOOD,
            price = 5,
            imageRes = R.drawable.img_prize_ramen,
        ),
        PrizeItem(
            id = "double_smash_burger",
            title = "Double Smash Meal",
            subtitle = "Brioche burger & fries",
            category = PrizeCategory.FOOD,
            price = 6,
            imageRes = R.drawable.img_prize_burger,
        ),
        PrizeItem(
            id = "wagyu_steak",
            title = "A5 Wagyu Ribeye",
            subtitle = "Rosemary seared plate",
            category = PrizeCategory.FOOD,
            price = 10,
            imageRes = R.drawable.img_prize_wagyu,
        ),

        // Known Motorbikes
        PrizeItem(
            id = "yamaha_r1",
            title = "Yamaha YZF-R1",
            subtitle = "998cc Crossplane Superbike",
            category = PrizeCategory.MOTORBIKE,
            price = 18,
            imageRes = R.drawable.img_prize_yamaha_r1,
        ),
        PrizeItem(
            id = "harley_fatboy",
            title = "Harley-Davidson Fat Boy",
            subtitle = "Milwaukee-Eight Cruiser",
            category = PrizeCategory.MOTORBIKE,
            price = 22,
            imageRes = R.drawable.img_prize_harley_fatboy,
        ),
        PrizeItem(
            id = "ducati_panigale_v4",
            title = "Ducati Panigale V4",
            subtitle = "Italian V4 Track Legend",
            category = PrizeCategory.MOTORBIKE,
            price = 28,
            imageRes = R.drawable.img_prize_ducati_v4,
        ),
        PrizeItem(
            id = "kawasaki_ninja_h2r",
            title = "Kawasaki Ninja H2R",
            subtitle = "Supercharged Hyperbike",
            category = PrizeCategory.MOTORBIKE,
            price = 35,
            imageRes = R.drawable.img_prize_kawasaki_h2r,
        ),

        // Known Cars
        PrizeItem(
            id = "bmw_m3_comp",
            title = "BMW M3 Competition",
            subtitle = "Twin-Turbo Inline-6 Sedan",
            category = PrizeCategory.CAR,
            price = 45,
            imageRes = R.drawable.img_prize_bmw_m3,
        ),
        PrizeItem(
            id = "porsche_911_gt3",
            title = "Porsche 911 GT3",
            subtitle = "9,000 RPM Flat-6 Coupe",
            category = PrizeCategory.CAR,
            price = 60,
            imageRes = R.drawable.img_prize_porsche_gt3,
        ),
        PrizeItem(
            id = "lambo_huracan",
            title = "Lamborghini Huracán",
            subtitle = "Naturally Aspirated V10",
            category = PrizeCategory.CAR,
            price = 75,
            imageRes = R.drawable.img_prize_lambo_huracan,
        ),
        PrizeItem(
            id = "ferrari_f40",
            title = "Ferrari F40",
            subtitle = "Twin-Turbo V8 Icon",
            category = PrizeCategory.CAR,
            price = 95,
            imageRes = R.drawable.img_prize_ferrari_f40,
        ),
    )

    fun findById(id: String): PrizeItem? = items.firstOrNull { it.id == id }
}
