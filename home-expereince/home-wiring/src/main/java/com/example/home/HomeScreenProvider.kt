package com.example.home

import androidx.navigation.NavHostController

object HomeScreenProvider {

    fun homeNavScreen(navController: NavHostController): HomeNavScreen = RealHomeNavScreen(navController)
}