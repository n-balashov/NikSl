package com.pavesid.niksl.data

import com.pavesid.niksl.data.local.LocalSource
import com.pavesid.niksl.data.model.Achievement
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val localSource: LocalSource
) : DataRepository {

    override suspend fun getAllAchievements(): List<Achievement> = localSource.getAllAchievements()
}
