package com.pavesid.niksl.data.local

import androidx.lifecycle.LiveData
import com.pavesid.niksl.data.model.Achievement
import javax.inject.Inject

class LocalSourceImpl @Inject constructor(private val database: AppDatabase) : LocalSource {

    override fun getAllAchievements(): LiveData<List<Achievement>> = database.achievementDao().getAll()

    override fun getNotViewedAchievements(): LiveData<List<Achievement>> = database.achievementDao().getNotViewed()

    override fun getDoneAchievements(): LiveData<List<Achievement>> = database.achievementDao().getDone()

    override fun getNotYetAchievements(): LiveData<List<Achievement>> = database.achievementDao().getNotYet()

    override suspend fun updateAchievement(done: Boolean, id: Long) = database.achievementDao().updateAchievement(done, id)
}
