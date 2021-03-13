package com.pavesid.niksl.data.local

import com.pavesid.niksl.data.model.Achievement

interface LocalSource {

    suspend fun getAllAchievements(): List<Achievement>

    suspend fun getNotViewedAchievements(): List<Achievement>

    suspend fun getDoneAchievements(): List<Achievement>

    suspend fun getNotYetAchievements(): List<Achievement>
}
