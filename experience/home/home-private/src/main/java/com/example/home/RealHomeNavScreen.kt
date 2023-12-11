package com.example.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.compose_nav.NavRoute

class RealHomeNavScreen constructor(
    navGraphBuilder: NavGraphBuilder,
    navController: NavHostController
) : HomeNavScreen {

    init {
        navGraphBuilder.homeNavScreen()
    }

    override fun NavGraphBuilder.homeNavScreen() {

        navigation(
            route = NavRoute.Home.route,
            startDestination = HomeMainScreen.MainScreen.route
        ) {
            composable(route = HomeMainScreen.MainScreen.route) {
                MainScreen()
            }
        }
    }

}