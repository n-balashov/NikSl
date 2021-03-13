package com.pavesid.niksl.data.local

import androidx.room.Dao
import androidx.room.Query
import com.pavesid.niksl.data.model.Achievement

@Dao
interface AchievementDao {

    @Query("SELECT * FROM achievement")
    fun getAll(): List<Achievement>

    @Query("SELECT * FROM achievement WHERE done is null")
    fun getNotViewed(): List<Achievement>

    @Query("SELECT * FROM achievement WHERE done = 1")
    fun getDone(): List<Achievement>

    @Query("SELECT * FROM achievement WHERE done = 0")
    fun getNotYet(): List<Achievement>
}
