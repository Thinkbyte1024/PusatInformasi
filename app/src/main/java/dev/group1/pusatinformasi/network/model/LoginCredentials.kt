package dev.group1.pusatinformasi.network.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginCredentials(
    val email: String,
    val password: String
)
