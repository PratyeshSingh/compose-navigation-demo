package com.example.compose_nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.auth.AuthScreenProvider.authNavScreen
import com.example.home.HomeScreenProvider.homeNavScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val isAuthenticated = false
    val startDestination = if (isAuthenticated) NavRoute.Home.route else NavRoute.AuthScreen.route
    NavHost(navController = navController, startDestination = startDestination) {
        authNavScreen(navGraphBuilder = this, navController = navController)
        homeNavScreen(navGraphBuilder = this, navController = navController)
    }
}

sealed class Screen(val route: String) {
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