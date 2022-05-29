package dev.group1.pusatinformasi.network.config

import dev.group1.pusatinformasi.network.model.AuthResponse
import dev.group1.pusatinformasi.network.model.LoginCredentials
import dev.group1.pusatinformasi.network.model.RegisterCredentials
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

interface NetConfig {
    suspend fun login(request: LoginCredentials): AuthResponse
    suspend fun register(request: RegisterCredentials): AuthResponse

    companion object {
        fun create(): NetService {
            return NetService(
                client = HttpClient(Android) {
                    install(ContentNegotiation) {
                        json()
                    }
                }
            )
        }
    }
}