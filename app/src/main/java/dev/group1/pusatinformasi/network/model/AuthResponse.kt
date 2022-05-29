package dev.group1.pusatinformasi.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val message: String? = null,
    val token: String? = null,
    val nama: String? = null
)
