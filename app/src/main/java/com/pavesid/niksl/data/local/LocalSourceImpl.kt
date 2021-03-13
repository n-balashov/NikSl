package com.pavesid.niksl.data.local

import com.pavesid.niksl.data.model.Achievement
import javax.inject.Inject

class LocalSourceImpl @Inject constructor(private val database: AppDatabase) : LocalSource {

    override suspend fun getAllAchievements(): List<Achievement> {
        return database.achievementDao().getAll()
    }
}
