package com.example.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

object AuthScreenProvider {

    fun authNavScreen(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ): AuthNavScreen = RealAuthNavScreen(navGraphBuilder, navController)
}