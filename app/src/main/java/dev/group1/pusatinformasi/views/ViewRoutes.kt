package dev.group1.pusatinformasi.views

sealed class ViewRoutes(val route: String) {
    object HomePage: ViewRoutes(route = "homepage")
    object LoginPage: ViewRoutes(route = "login")
    object RegisterPage: ViewRoutes(route = "register")
}
