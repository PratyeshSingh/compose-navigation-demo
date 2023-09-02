package com.example.compose_navigation_demo

sealed class Screen(val route: String) {
    object AuthScreen : Screen("auth")
    object Home : Screen("home")
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")
    object AboutScreen : Screen("about_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}