package com.pavesid.niksl.data.local

import com.pavesid.niksl.data.model.Achievement

interface LocalSource {

    suspend fun getAllAchievements(): List<Achievement>
}
