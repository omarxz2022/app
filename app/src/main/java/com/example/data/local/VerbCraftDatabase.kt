package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        UserProfileEntity::class,
        CollectableEntity::class,
        WrongQuestionEntity::class,
    ],
    version = 3,
    exportSchema = false,
)
abstract class VerbCraftDatabase : RoomDatabase() {
    abstract fun verbCraftDao(): VerbCraftDao

    companion object {
        @Volatile
        private var instance: VerbCraftDatabase? = null

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `wrong_questions` (
                        `questionId` INTEGER NOT NULL,
                        `wrongCount` INTEGER NOT NULL DEFAULT 1,
                        `updatedAt` INTEGER NOT NULL,
                        PRIMARY KEY(`questionId`)
                    )
                    """.trimIndent()
                )
            }
        }

        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE `user_profile` ADD COLUMN `fastingMode` INTEGER NOT NULL DEFAULT 0"
                )
            }
        }

        fun getInstance(context: Context): VerbCraftDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    VerbCraftDatabase::class.java,
                    "verbcraft_offline.db",
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build()
                    .also { instance = it }
            }
        }
    }
}
