package dev.group1.pusatinformasi.network.config

import dev.group1.pusatinformasi.network.model.AuthResponse
import dev.group1.pusatinformasi.network.model.LoginCredentials
import dev.group1.pusatinformasi.network.model.RegisterCredentials
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*

interface NetConfig {
    suspend fun login(request: LoginCredentials): HttpResponse
    suspend fun register(request: RegisterCredentials): HttpResponse

    companion object {

        // Fungsi untuk membuat client HTTP dan memasang plugin-plugin dalam blok HttpClient()
        fun create(): NetService {
            return NetService(
                client = HttpClient(Android) {

                    // JSON
                    install(ContentNegotiation) {
                        json()
                    }

                    // HTTP Timeout
                    install(HttpTimeout) {
                        requestTimeoutMillis = 7500
                    }

                    // Logging
                    install(Logging) {
                        logger = Logger.DEFAULT
                        level = LogLevel.ALL
                    }
                }
            )
        }
    }
}