package com.example.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.compose_nav.NavRoute
import com.example.home.HomeMainScreen

class RealAuthNavScreen(
    navGraphBuilder: NavGraphBuilder,
    private val navController: NavHostController
) : AuthNavScreen {

    init {
        navGraphBuilder.authNavScreen()
    }

    override fun NavGraphBuilder.authNavScreen() {
        val navigationRouter: (AuthAction) -> Unit = {
            it.performClick()
        }
        navigation(
            route = NavRoute.AuthScreen.route,
            startDestination = AuthScreen.LoginScreen.route
        ) {
            composable(route = AuthScreen.LoginScreen.route) {
                val viewModel = it.sharedViewModel<AuthenticationViewModel>(navController)
                LoginScreen(onClick = navigationRouter)
            }
            composable(route = AuthScreen.ForgotScreen.route) {
                val viewModel = it.sharedViewModel<AuthenticationViewModel>(navController)
                ForgotScreen(onClick = navigationRouter)
            }
        }
    }

    private fun AuthAction.performClick() {
        when (this) {
            AuthAction.LOGIN -> {
                navController.navigate(AuthScreen.LoginScreen.route)
            }

            AuthAction.FORGOT -> {
                navController.navigate(AuthScreen.ForgotScreen.route)
            }

            AuthAction.SUBMIT -> {
                navController.navigate(HomeMainScreen.MainScreen.route) {
                    popUpTo(NavRoute.AuthScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}