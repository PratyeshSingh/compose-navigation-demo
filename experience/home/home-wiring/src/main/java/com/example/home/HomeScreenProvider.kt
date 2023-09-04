package com.example.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

object HomeScreenProvider {

    fun homeNavScreen(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ): HomeNavScreen = RealHomeNavScreen(navGraphBuilder, navController)
}