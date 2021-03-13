package com.pavesid.niksl.data

import com.pavesid.niksl.data.model.Achievement

interface DataRepository {

    suspend fun getAllAchievements(): List<Achievement>

    suspend fun getNotViewedAchievements(): List<Achievement>

    suspend fun getDoneAchievements(): List<Achievement>

    suspend fun getNotYetAchievements(): List<Achievement>

    suspend fun updateAchievement(done: Boolean, id: Long)
}
