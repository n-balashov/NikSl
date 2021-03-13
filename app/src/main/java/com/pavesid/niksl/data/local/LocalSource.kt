package com.pavesid.niksl.data.local

import androidx.lifecycle.LiveData
import com.pavesid.niksl.data.model.Achievement

interface LocalSource {

    fun getAllAchievements(): LiveData<List<Achievement>>

    fun getNotViewedAchievements(): LiveData<List<Achievement>>

    fun getDoneAchievements(): LiveData<List<Achievement>>

    fun getNotYetAchievements(): LiveData<List<Achievement>>

    suspend fun updateAchievement(done: Boolean, id: Long)
}
