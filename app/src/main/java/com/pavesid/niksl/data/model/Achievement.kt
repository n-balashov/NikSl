package com.pavesid.niksl.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Achievement(
    @PrimaryKey val id: Long,
    val name: String,
    val imagePath: String,
    val done: Boolean?
) : Parcelable
