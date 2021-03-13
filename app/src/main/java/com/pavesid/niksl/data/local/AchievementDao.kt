package com.pavesid.niksl.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.pavesid.niksl.data.model.Achievement

@Dao
interface AchievementDao {

    @Query("SELECT * FROM achievement")
    fun getAll(): LiveData<List<Achievement>>

    @Query("SELECT * FROM achievement WHERE done is null")
    fun getNotViewed(): LiveData<List<Achievement>>

    @Query("SELECT * FROM achievement WHERE done = 1")
    fun getDone(): LiveData<List<Achievement>>

    @Query("SELECT * FROM achievement WHERE done = 0")
    fun getNotYet(): LiveData<List<Achievement>>

    @Query("UPDATE achievement SET done = :done WHERE id LIKE :id ")
    suspend fun updateAchievement(done: Boolean, id: Long)
}
