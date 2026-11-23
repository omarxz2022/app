package com.example.data

import android.content.Context
import com.example.data.local.VerbCraftDao
import com.example.data.local.VerbCraftDatabase
import com.example.model.CollectableItem
import com.example.model.PrizeItem
import com.example.model.UserProfile
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ProgressStore {
    fun observeUserProfile(userId: String): Flow<UserProfile?>
    fun observeCollection(userId: String): Flow<List<CollectableItem>>
    suspend fun ensureProfileExists(userId: String, defaultName: String): Result<Unit>
    suspend fun updateProfile(userId: String, displayName: String, grade: String): Result<Unit>
    suspend fun recordAnswer(userId: String, isCorrect: Boolean, isRewrite: Boolean): Result<Unit>
    suspend fun purchasePrize(userId: String, prize: PrizeItem): Result<Unit>
}

class LocalProgressRepository(
    private val dao: VerbCraftDao,
) : ProgressStore {

    constructor(context: Context) : this(
        VerbCraftDatabase.getInstance(context).verbCraftDao()
    )

    override fun observeUserProfile(userId: String): Flow<UserProfile?> =
        dao.observeUserProfile().map { it?.toDomain() }

    override fun observeCollection(userId: String): Flow<List<CollectableItem>> =
        dao.observeCollection().map { list -> list.map { it.toDomain() } }

    override suspend fun ensureProfileExists(userId: String, defaultName: String): Result<Unit> {
        return try {
            dao.ensureProfileExistsTx(defaultName)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateProfile(userId: String, displayName: String, grade: String): Result<Unit> {
        return try {
            dao.updateProfileTx(displayName, grade)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun recordAnswer(
        userId: String,
        isCorrect: Boolean,
        isRewrite: Boolean,
    ): Result<Unit> {
        return try {
            dao.recordAnswerTx(isCorrect = isCorrect, isRewrite = isRewrite)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun purchasePrize(userId: String, prize: PrizeItem): Result<Unit> {
        return try {
            dao.purchasePrizeTx(prize)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
