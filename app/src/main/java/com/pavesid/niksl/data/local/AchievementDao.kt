package com.pavesid.niksl.data.local

import androidx.room.Dao
import androidx.room.Query
import com.pavesid.niksl.data.model.Achievement

@Dao
interface AchievementDao {

    @Query("SELECT * FROM achievement")
    fun getAll(): List<Achievement>
}
