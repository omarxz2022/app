package com.example.data

import com.example.model.PrizeItem

object PrizeCatalog {
    val items: List<PrizeItem> by lazy {
        (foodPrizeItems + motorbikePrizeItems + carPrizeItems).sortedBy { it.price }
    }

    private val itemsById: Map<String, PrizeItem> by lazy {
        items.associateBy { it.id }
    }

    fun findById(id: String): PrizeItem? = itemsById[id]
}
