package com.example.compose_navigation_demo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_navigation_demo.AboutScreen
import com.example.compose_navigation_demo.auth.authNavScreen
import com.example.compose_navigation_demo.home.Screen
import com.example.compose_navigation_demo.home.homeNavScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val isAuthenticated = true

    val startDestination = if (isAuthenticated) NavRoute.Home.route else NavRoute.AuthScreen.route

    NavHost(navController = navController, startDestination = startDestination) {

        composable(
            route = Screen.AboutScreen.route + "/{name}",   // Not Nullable
        ) {
            AboutScreen(args = it.arguments?.getString("name"))
        }

        authNavScreen(navController = navController)

        homeNavScreen(navController = navController)
    }

}
