package com.example.compose_navigation_demo.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.compose_navigation_demo.Screen
import com.example.compose_navigation_demo.sharedViewModel

fun NavGraphBuilder.authNavScreen(
    navController: NavHostController,
) {
    val navigationRouter: (AuthAction) -> Unit = {
        it.performClick(navController)
    }

    with(this) {
        navigation(
            route = Screen.AuthScreen.route,
            startDestination = AuthScreen.LoginScreen.route
        ) {
            composable(route = AuthScreen.RegisterScreen.route) {
                val viewModel = it.sharedViewModel<SampleViewModel>(navController)
                RegistrationScreen(onClick = navigationRouter)
            }
            composable(route = AuthScreen.LoginScreen.route) {
                val viewModel = it.sharedViewModel<SampleViewModel>(navController)
                LoginScreen(onClick = navigationRouter)
            }
            composable(route = AuthScreen.ForgotScreen.route) {
                val viewModel = it.sharedViewModel<SampleViewModel>(navController)
                ForgotScreen(onClick = navigationRouter)
            }
        }
    }
}

private fun AuthAction.performClick(
    navController: NavHostController
) {
    when (this) {
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
            // Note: Ideally this should shit in Public module of home feature
            // But since this is a demo app so keeping it here to simplify for understanding
            navController.navigate(Screen.MainScreen.route) {
                popUpTo(Screen.AuthScreen.route) {
                    inclusive = true
                }
            }
        }
    }
}