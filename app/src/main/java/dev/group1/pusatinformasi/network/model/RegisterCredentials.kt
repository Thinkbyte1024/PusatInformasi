package dev.group1.pusatinformasi.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterCredentials(
    val nama: String,
    val alamat: String,
    val email: String,
    val password: String
)
