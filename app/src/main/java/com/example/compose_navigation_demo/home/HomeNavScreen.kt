package com.example.compose_navigation_demo.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.compose_navigation_demo.DetailsScreen
import com.example.compose_navigation_demo.MainScreen
import com.example.compose_navigation_demo.navigation.NavRoute

fun NavGraphBuilder.homeNavScreen(
    navController: NavHostController,
) {

    val navigationRouter: (HomeAction) -> Unit = {
        it.performClick(navController)
    }

    with(this) {
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
                DetailsScreen(args = it.arguments?.getString("name"), onClick = navigationRouter)
            }
        }
    }
}

private fun HomeAction.performClick(
    navController: NavHostController,
) {
    when (this) {
        HomeAction.MAIN ->
            navController.navigate(Screen.MainScreen.route)

        HomeAction.ABOUT ->
            navController.navigate(Screen.AboutScreen.withArgs("Text args check")) // Not Nullable

        HomeAction.DETAILS ->
            navController.navigate(Screen.DetailScreen.withArgs()) // Nullable
    }
}