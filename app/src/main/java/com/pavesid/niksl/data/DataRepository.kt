package com.pavesid.niksl.data

import com.pavesid.niksl.data.model.Achievement

interface DataRepository {

    suspend fun getAllAchievements(): List<Achievement>
}
