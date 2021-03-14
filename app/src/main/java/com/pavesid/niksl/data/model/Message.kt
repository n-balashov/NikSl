package com.pavesid.niksl.data.model

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class Message(
    val id: String = "",
    val text: String = "",
    val userId: String? = "",
    val data: Long = Date().time
)
