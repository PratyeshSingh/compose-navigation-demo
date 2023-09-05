package com.example.compose_nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.auth.AuthScreenProvider.authNavScreen
import com.example.home.HomeScreenProvider.homeNavScreen
import com.example.home.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val isAuthenticated = false
    val startDestination = if (isAuthenticated) NavRoute.Home.route else NavRoute.AuthScreen.route
    NavHost(navController = navController, startDestination = startDestination) {
        composable(
            route = Screen.AboutScreen.route + "/{name}",   // Not Nullable
        ) {
            AboutScreen(args = it.arguments?.getString("name"))
        }
        authNavScreen(navGraphBuilder = this, navController = navController)
        homeNavScreen(navGraphBuilder = this, navController = navController)
    }
}