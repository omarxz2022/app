package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserProfileEntity::class, CollectableEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class VerbCraftDatabase : RoomDatabase() {
    abstract fun verbCraftDao(): VerbCraftDao

    companion object {
        @Volatile
        private var instance: VerbCraftDatabase? = null

        fun getInstance(context: Context): VerbCraftDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    VerbCraftDatabase::class.java,
                    "verbcraft_offline.db",
                ).build().also { instance = it }
            }
        }
    }
}
