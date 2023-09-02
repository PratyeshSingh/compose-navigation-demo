package com.example.compose_navigation_demo

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.AuthScreen.route) {

        composable(
            route = Screen.AboutScreen.route + "/{name}",   // Not Nullable
        ) {
            AboutScreen(args = it.arguments?.getString("name"))
        }

        navigation(
            route = Screen.AuthScreen.route,
            startDestination = AuthScreen.LoginScreen.route
        ) {
            fun onClick(authAction: AuthAction) {

                when (authAction) {
                    AuthAction.LOGIN -> {
                        navController.navigate(AuthScreen.LoginScreen.route)
                    }

                    AuthAction.REGISTER -> {
                        navController.navigate(AuthScreen.RegisterScreen.route)
                    }

                    AuthAction.FORGOT -> {
                        navController.navigate(AuthScreen.ForgotScreen.route)
                    }

                    AuthAction.SUBMIT -> {
                        navController.navigate(Screen.MainScreen.route) {
                            popUpTo(Screen.AuthScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            }

            composable(route = AuthScreen.RegisterScreen.route) {
                val viewModel = it.sharedViewModel<SampleViewModel>(navController)
                RegistrationScreen(onClick = ::onClick)
            }
            composable(route = AuthScreen.LoginScreen.route) {
                val viewModel = it.sharedViewModel<SampleViewModel>(navController)
                LoginScreen(onClick = ::onClick)
            }
            composable(route = AuthScreen.ForgotScreen.route) {
                val viewModel = it.sharedViewModel<SampleViewModel>(navController)
                ForgotScreen(onClick = ::onClick)
            }
        }

        navigation(
            route = Screen.Home.route,
            startDestination = Screen.MainScreen.route
        ) {
            fun onClick(action: HomeAction) {
                when (action) {
                    HomeAction.MAIN ->
                        navController.navigate(Screen.MainScreen.route)

                    HomeAction.ABOUT ->
                        navController.navigate(Screen.AboutScreen.withArgs("Text args check")) // Not Nullable

                    HomeAction.DETAILS ->
                        navController.navigate(Screen.DetailScreen.withArgs()) // Nullable
                }
            }
            composable(route = Screen.MainScreen.route) {
                MainScreen(onClick = ::onClick)
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
                DetailsScreen(args = it.arguments?.getString("name"), onClick = ::onClick)
            }
        }
    }

}

enum class HomeAction {
    MAIN,
    ABOUT,
    DETAILS
}

enum class AuthAction {
    LOGIN,
    REGISTER,
    FORGOT,
    SUBMIT,
}

sealed class AuthScreen(val route: String) {
    object LoginScreen : AuthScreen("login_screen")
    object RegisterScreen : AuthScreen("register_screen")
    object ForgotScreen : AuthScreen("forgot_screen")
}

sealed class Screen(val route: String) {
    object AuthScreen : Screen("auth")
    object Home : Screen("home")
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")
    object AboutScreen : Screen("about_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}