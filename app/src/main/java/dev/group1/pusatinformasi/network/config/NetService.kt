package dev.group1.pusatinformasi.network.config

import dev.group1.pusatinformasi.network.HttpRoutes
import dev.group1.pusatinformasi.network.model.AuthResponse
import dev.group1.pusatinformasi.network.model.LoginCredentials
import dev.group1.pusatinformasi.network.model.RegisterCredentials
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class NetService(
    private val client: HttpClient
): NetConfig {
    override suspend fun login(request: LoginCredentials): AuthResponse {
        return client.post(HttpRoutes.LOGIN) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    override suspend fun register(request: RegisterCredentials): AuthResponse {
        return client.post(HttpRoutes.REGISTER) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}