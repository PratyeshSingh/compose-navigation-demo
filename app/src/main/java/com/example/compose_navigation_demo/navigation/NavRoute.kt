package com.example.compose_navigation_demo.navigation

sealed class NavRoute(val route: String) {
    object AuthScreen : NavRoute("auth")
    object Home : NavRoute("home")
}