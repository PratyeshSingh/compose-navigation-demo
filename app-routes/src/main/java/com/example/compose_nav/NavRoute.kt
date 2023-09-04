package com.example.compose_nav

sealed class NavRoute(val route: String) {
    object AuthScreen : NavRoute("auth")
    object Home : NavRoute("home")
}