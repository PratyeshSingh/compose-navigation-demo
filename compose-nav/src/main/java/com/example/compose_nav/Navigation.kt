package com.example.compose_nav

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
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
            /**
            Step to verify deeplink:-
            O/P ==> uriPattern = "example://compose/about_screen/{name}"
            Verify this :-
            adb shell am start -W -a android.intent.action.VIEW -d "example://compose/about_screen/'Hi Pratyesh is making Demo app to verify deeplink property'"
             */
            deepLinks = listOf(navDeepLink {
                uriPattern = "example://compose/${Screen.AboutScreen.route}/{name}"
                action = Intent.ACTION_VIEW
            })
        ) {
            AboutScreen(args = it.arguments?.getString("name"))
        }
        authNavScreen(navGraphBuilder = this, navController = navController)
        homeNavScreen(navGraphBuilder = this, navController = navController)
    }
}