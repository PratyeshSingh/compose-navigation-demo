package com.example.home

import academy.compose.gallery.GalleryScreen
import academy.compose.inbox.ui.InboxScreen
import academy.compose.messaging.ui.Messaging
import academy.compose.music.ui.MusicCatalog
import academy.compose.settings.ui.Settings
import academy.compose.video.ui.Video
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.compose_nav.NavRoute

class RealHomeNavScreen constructor(
    navGraphBuilder: NavGraphBuilder,
    private val navController: NavHostController
) : HomeNavScreen {

    init {
        navGraphBuilder.homeNavScreen()
    }

    @OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class,
        ExperimentalComposeUiApi::class
    )
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

            composable(route = Screen.GalleryScreen.route) {
                GalleryScreen()
            }

            composable(route = Screen.InboxScreen.route) {
                InboxScreen()
            }

            composable(route = Screen.MessagingScreen.route) {
                Messaging()
            }

            composable(route = Screen.SettingScreen.route) {
                Settings()
            }

            composable(route = Screen.VideoScreen.route) {
                Video(/*lifecycleOwner = this*/)
            }

            composable(route = Screen.MusicScreen.route) {
                MusicCatalog()
            }

            composable(
                route = Screen.DetailScreen.route + "?name={name}", // Nullable
                arguments = listOf(
                    navArgument("name") {
                        type = NavType.StringType
                        defaultValue = "Pratyesh"
                        nullable = true
                    }
                ),
                /**
                Step to verify deeplink:-
                 O/P ==> uriPattern = "example://compose/detail_screen/{name}"
                 Verify this :-
                 case1:-
                 adb shell am start -W -a android.intent.action.VIEW -d "example://compose/detail_screen/'Pratyesh cooming via Deeplink'"

                 case2:-
                 adb shell am start -W -a android.intent.action.VIEW -d "example://compose/detail_screen/Pratyesh_cooming_via_Deeplink"
                */
                deepLinks = listOf(navDeepLink {
                    uriPattern = "example://compose/${Screen.DetailScreen.route}/{name}"
                    action = Intent.ACTION_VIEW
                })
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

            HomeAction.GALLERY ->
                navController.navigate(Screen.GalleryScreen.route)

            HomeAction.INBOX ->
                navController.navigate(Screen.InboxScreen.route)

            HomeAction.MESSAGING ->
                navController.navigate(Screen.MessagingScreen.route)

            HomeAction.SETTING ->
                navController.navigate(Screen.SettingScreen.route)

            HomeAction.VIDEOSCREEN ->
                navController.navigate(Screen.VideoScreen.route)

            HomeAction.MUSIC ->
                navController.navigate(Screen.MusicScreen.route)

            HomeAction.ABOUT ->
                navController.navigate(Screen.AboutScreen.withArgs("Text args check")) // Not Nullable

            HomeAction.DETAILS ->
                navController.navigate(Screen.DetailScreen.withArgs()) // Nullable
        }
    }
}