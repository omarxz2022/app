package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.model.PrizeItem
import kotlinx.coroutines.flow.Flow

@Dao
interface VerbCraftDao {

    @Query("SELECT * FROM user_profile WHERE id = 'local_user' LIMIT 1")
    fun observeUserProfile(): Flow<UserProfileEntity?>

    @Query("SELECT * FROM user_profile WHERE id = 'local_user' LIMIT 1")
    suspend fun getUserProfileOnce(): UserProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertUserProfile(profile: UserProfileEntity)

    @Query("SELECT * FROM collectables ORDER BY price DESC")
    fun observeCollection(): Flow<List<CollectableEntity>>

    @Query("SELECT * FROM collectables WHERE prizeId = :prizeId LIMIT 1")
    suspend fun getCollectableById(prizeId: String): CollectableEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCollectable(item: CollectableEntity)

    @Transaction
    suspend fun ensureProfileExistsTx(defaultName: String) {
        if (getUserProfileOnce() == null) {
            upsertUserProfile(
                UserProfileEntity(
                    id = "local_user",
                    displayName = defaultName.trim().take(60),
                    grade = "",
                )
            )
        }
    }

    @Transaction
    suspend fun updateProfileTx(displayName: String, grade: String) {
        val current = getUserProfileOnce() ?: UserProfileEntity(id = "local_user")
        upsertUserProfile(
            current.copy(
                displayName = displayName.trim().take(60),
                grade = grade.trim().take(30),
            )
        )
    }

    @Transaction
    suspend fun recordAnswerTx(isCorrect: Boolean, isRewrite: Boolean) {
        val current = getUserProfileOnce() ?: UserProfileEntity(id = "local_user")
        val coinDelta = when {
            !isCorrect -> 0L
            isRewrite -> 5L
            else -> 1L
        }
        upsertUserProfile(
            current.copy(
                coins = current.coins + coinDelta,
                totalAttempted = current.totalAttempted + 1L,
                totalCorrect = current.totalCorrect + if (isCorrect) 1L else 0L,
                choiceCorrect = current.choiceCorrect + if (isCorrect && !isRewrite) 1L else 0L,
                rewriteCorrect = current.rewriteCorrect + if (isCorrect && isRewrite) 1L else 0L,
            )
        )
    }

    @Transaction
    suspend fun purchasePrizeTx(prize: PrizeItem) {
        val current = getUserProfileOnce() ?: UserProfileEntity(id = "local_user")
        if (current.coins < prize.price) {
            throw IllegalStateException("Not enough coins for ${prize.title}.")
        }
        upsertUserProfile(
            current.copy(coins = current.coins - prize.price.toLong())
        )
        val existing = getCollectableById(prize.id)
        if (existing == null) {
            upsertCollectable(
                CollectableEntity(
                    prizeId = prize.id,
                    title = prize.title,
                    category = prize.category.name,
                    price = prize.price.toLong(),
                    quantity = 1L,
                )
            )
        } else {
            upsertCollectable(existing.copy(quantity = existing.quantity + 1L))
        }
    }
}
