package com.pavesid.niksl.data.local

import com.pavesid.niksl.data.model.Achievement
import javax.inject.Inject

class LocalSourceImpl @Inject constructor(private val database: AppDatabase) : LocalSource {

    override suspend fun getAllAchievements(): List<Achievement> = database.achievementDao().getAll()

    override suspend fun getNotViewedAchievements(): List<Achievement> = database.achievementDao().getNotViewed()

    override suspend fun getDoneAchievements(): List<Achievement> = database.achievementDao().getDone()

    override suspend fun getNotYetAchievements(): List<Achievement> = database.achievementDao().getNotYet()

    override suspend fun updateAchievement(done: Boolean, id: Long) = database.achievementDao().updateAchievement(done, id)
}
