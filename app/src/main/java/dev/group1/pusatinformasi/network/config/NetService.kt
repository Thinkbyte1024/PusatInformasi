package dev.group1.pusatinformasi.network.config

import dev.group1.pusatinformasi.network.HttpRoutes
import dev.group1.pusatinformasi.network.model.AuthResponse
import dev.group1.pusatinformasi.network.model.LoginCredentials
import dev.group1.pusatinformasi.network.model.RegisterCredentials
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.lang.Exception

class NetService(
    private val client: HttpClient
) : NetConfig {
    override suspend fun login(request: LoginCredentials): HttpResponse {
        return try {
            val response = client.post(HttpRoutes.LOGIN) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            return response
        } catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${ex.response.status.description}")
            ex.response
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error: ${ex.response.status.description}")
            ex.response
        } catch (ex: ServerResponseException) {
            // 5xx - responses
            println("Error: ${ex.response.status.description}")
            ex.response
        }
    }

    override suspend fun register(request: RegisterCredentials): HttpResponse {
        return client.post(HttpRoutes.REGISTER) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}