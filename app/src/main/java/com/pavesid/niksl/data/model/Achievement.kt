package com.pavesid.niksl.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Achievement(
    @PrimaryKey val id: Long,
    val name: String,
    val imagePath: String
)
