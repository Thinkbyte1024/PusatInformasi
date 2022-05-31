package dev.group1.pusatinformasi.presenter

import androidx.compose.runtime.Composable
import dev.group1.pusatinformasi.model.AuthResponse

interface AuthView {
    fun onBadRequest(message: String) // code response 400 or 500
    fun onSuccessRegister(message: String) // code response 200
    fun onSuccessLogin(authResponse: AuthResponse)
}