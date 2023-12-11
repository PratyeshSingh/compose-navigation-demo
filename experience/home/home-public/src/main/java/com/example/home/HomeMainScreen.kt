package com.example.home


sealed class HomeMainScreen(val route: String) {
    object MainScreen : HomeMainScreen("home_tab_screen")
}