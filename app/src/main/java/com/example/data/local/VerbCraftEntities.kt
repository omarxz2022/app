package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.CollectableItem
import com.example.model.UserProfile

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: String = "local_user",
    val displayName: String = "",
    val grade: String = "",
    val coins: Long = 0L,
    val totalAttempted: Long = 0L,
    val totalCorrect: Long = 0L,
    val choiceCorrect: Long = 0L,
    val rewriteCorrect: Long = 0L,
) {
    fun toDomain(): UserProfile = UserProfile(
        id = id,
        userId = id,
        displayName = displayName,
        grade = grade,
        coins = coins,
        totalAttempted = totalAttempted,
        totalCorrect = totalCorrect,
        choiceCorrect = choiceCorrect,
        rewriteCorrect = rewriteCorrect,
    )
}

@Entity(tableName = "collectables")
data class CollectableEntity(
    @PrimaryKey val prizeId: String,
    val title: String,
    val category: String,
    val price: Long,
    val quantity: Long = 1L,
) {
    fun toDomain(): CollectableItem = CollectableItem(
        id = prizeId,
        prizeId = prizeId,
        userId = "local_user",
        title = title,
        category = category,
        price = price,
        quantity = quantity,
    )
}
