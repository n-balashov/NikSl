package com.pavesid.niksl.data

import com.pavesid.niksl.data.local.LocalSource
import com.pavesid.niksl.data.model.Achievement
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val localSource: LocalSource
) : DataRepository {

    override suspend fun getAllAchievements(): List<Achievement> = localSource.getAllAchievements()

    override suspend fun getNotViewedAchievements(): List<Achievement> = localSource.getNotViewedAchievements()

    override suspend fun getDoneAchievements(): List<Achievement> = localSource.getDoneAchievements()

    override suspend fun getNotYetAchievements(): List<Achievement> = localSource.getNotYetAchievements()

    override suspend fun updateAchievement(done: Boolean, id: Long) = localSource.updateAchievement(done, id)
}
