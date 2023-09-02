package com.example.compose_navigation_demo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_navigation_demo.auth.authNavScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.AuthScreen.route) {

        composable(
            route = Screen.AboutScreen.route + "/{name}",   // Not Nullable
        ) {
            AboutScreen(args = it.arguments?.getString("name"))
        }

        authNavScreen(navController = navController)

        homeNavScreen(navController = navController)
    }

}