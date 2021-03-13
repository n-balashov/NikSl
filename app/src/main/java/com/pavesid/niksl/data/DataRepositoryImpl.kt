package com.pavesid.niksl.data

import androidx.lifecycle.LiveData
import com.pavesid.niksl.data.local.LocalSource
import com.pavesid.niksl.data.model.Achievement
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val localSource: LocalSource
) : DataRepository {

    override fun getAllAchievements(): LiveData<List<Achievement>> =
        localSource.getAllAchievements()

    override fun getNotViewedAchievements(): LiveData<List<Achievement>> =
        localSource.getNotViewedAchievements()

    override fun getDoneAchievements(): LiveData<List<Achievement>> =
        localSource.getDoneAchievements()

    override fun getNotYetAchievements(): LiveData<List<Achievement>> =
        localSource.getNotYetAchievements()

    override suspend fun updateAchievement(done: Boolean, id: Long) =
        localSource.updateAchievement(done, id)
}
