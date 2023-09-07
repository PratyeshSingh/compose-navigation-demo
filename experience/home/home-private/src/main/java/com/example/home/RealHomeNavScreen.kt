package com.example.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.compose_nav.NavRoute

class RealHomeNavScreen constructor(
    navGraphBuilder: NavGraphBuilder,
    private val navController: NavHostController
) : HomeNavScreen {

    init {
        navGraphBuilder.homeNavScreen()
    }

    override fun NavGraphBuilder.homeNavScreen() {
        val navigationRouter: (HomeAction) -> Unit = {
            it.performClick()
        }
        navigation(
            route = NavRoute.Home.route,
            startDestination = Screen.MainScreen.route
        ) {
            composable(route = Screen.MainScreen.route) {
                MainScreen(onClick = navigationRouter)
            }
            composable(
                route = Screen.DetailScreen.route + "?name={name}", // Nullable
                arguments = listOf(
                    navArgument("name") {
                        type = NavType.StringType
                        defaultValue = "Pratyesh"
                        nullable = true
                    }
                )
            ) {
                DetailsScreen(
                    args = it.arguments?.getString("name"),
                    onClick = navigationRouter
                )
            }
        }
    }

    private fun HomeAction.performClick() {
        when (this) {
            HomeAction.MAIN ->
                navController.navigate(Screen.MainScreen.route)

            HomeAction.ABOUT ->
                navController.navigate(Screen.AboutScreen.withArgs("Text args check")) // Not Nullable

            HomeAction.DETAILS ->
                navController.navigate(Screen.DetailScreen.withArgs()) // Nullable
        }
    }
}