package com.pavesid.niksl.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pavesid.niksl.data.model.Achievement

@Database(
    entities = [Achievement::class],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun achievementDao(): AchievementDao
}
