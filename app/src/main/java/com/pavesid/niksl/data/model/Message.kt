package com.pavesid.niksl.data.model

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class Message(
    val id: String = "",
    val mes: String = "",
    val achId: Long = 0L,
    val data: Long = Date().time
)
