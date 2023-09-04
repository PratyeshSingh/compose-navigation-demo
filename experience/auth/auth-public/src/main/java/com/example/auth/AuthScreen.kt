package com.example.auth

sealed class AuthScreen(val route: String) {
    object LoginScreen : AuthScreen("login_screen")
    object RegisterScreen : AuthScreen("register_screen")
    object ForgotScreen : AuthScreen("forgot_screen")
}