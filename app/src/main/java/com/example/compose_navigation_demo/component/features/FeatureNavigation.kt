package com.example.compose_navigation_demo.component.features

import academy.compose.gallery.GalleryScreen
import academy.compose.inbox.ui.InboxScreen
import academy.compose.messaging.ui.Messaging
import academy.compose.music.ui.MusicCatalog
import academy.compose.settings.ui.Settings
import academy.compose.video.ui.Video
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.compose_nav.AboutScreen
import com.example.compose_nav.Screen

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun FeatureNavigation() {
    val navController = rememberNavController()
    val navigationRouter: (HomeAction) -> Unit = {
        it.performClick(navController)
    }
    NavHost(navController = navController, startDestination = FeaturesScreen.FeatureListScreen.route) {
        composable(
            route = Screen.AboutScreen.route + "/{name}",   // Not Nullable
            /**
            Step to verify deeplink:-
            O/P ==> uriPattern = "example://compose/about_screen/{name}"
            Verify this :-
            adb shell am start -W -a android.intent.action.VIEW -d "example://about_screen/'Hi Pratyesh is making Demo app to verify deeplink property'"
             */
            deepLinks = listOf(navDeepLink {
                uriPattern = "example://${Screen.AboutScreen.route}/{name}"
                action = Intent.ACTION_VIEW
            })
        ) {
            AboutScreen(args = it.arguments?.getString("name"))
        }

        composable(route = FeaturesScreen.FeatureListScreen.route) {
            FeatureList(onClick = navigationRouter)
        }

        composable(route = FeaturesScreen.GalleryScreen.route) {
            GalleryScreen()
        }

        composable(route = FeaturesScreen.InboxScreen.route) {
            InboxScreen()
        }

        composable(route = FeaturesScreen.MessagingScreen.route) {
            Messaging()
        }

        composable(route = FeaturesScreen.SettingScreen.route) {
            Settings()
        }

        composable(route = FeaturesScreen.VideoScreen.route) {
            Video(/*lifecycleOwner = this*/)
        }

        composable(route = FeaturesScreen.MusicScreen.route) {
            MusicCatalog()
        }

        composable(
            route = FeaturesScreen.DetailScreen.route + "?name={name}", // Nullable
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
            adb shell am start -W -a android.intent.action.VIEW -d "example://detail_screen/'Pratyesh cooming via Deeplink'"

            case2:-
            adb shell am start -W -a android.intent.action.VIEW -d "example://detail_screen/Pratyesh_cooming_via_Deeplink"
             */
            deepLinks = listOf(navDeepLink {
                uriPattern = "example://${FeaturesScreen.DetailScreen.route}/{name}"
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

private fun HomeAction.performClick(navController: NavHostController) {
    when (this) {
        HomeAction.FEATURE_LIST ->
            navController.navigate(FeaturesScreen.FeatureListScreen.route)

        HomeAction.GALLERY ->
            navController.navigate(FeaturesScreen.GalleryScreen.route)

        HomeAction.INBOX ->
            navController.navigate(FeaturesScreen.InboxScreen.route)

        HomeAction.MESSAGING ->
            navController.navigate(FeaturesScreen.MessagingScreen.route)

        HomeAction.SETTING ->
            navController.navigate(FeaturesScreen.SettingScreen.route)

        HomeAction.VIDEOSCREEN ->
            navController.navigate(FeaturesScreen.VideoScreen.route)

        HomeAction.MUSIC ->
            navController.navigate(FeaturesScreen.MusicScreen.route)

        HomeAction.ABOUT ->
            navController.navigate(Screen.AboutScreen.withArgs("Text args check")) // Not Nullable

        HomeAction.DETAILS ->
            navController.navigate(FeaturesScreen.DetailScreen.withArgs()) // Nullable
    }
}