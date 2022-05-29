package dev.group1.pusatinformasi.network

object HttpRoutes {

    // Base URL
    private const val BASE_URL = "https://tubes-mobile.dikatest.xyz/api"

    // API Categories
    private const val AUTH = "${BASE_URL}/auth"

    // Authentication
    const val LOGIN = "${AUTH}/login"
    const val REGISTER = "${AUTH}/register"
}