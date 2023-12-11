package com.example.compose_navigation_demo.component.features

sealed class FeaturesScreen(val route: String) {
    object FeatureListScreen : FeaturesScreen("features_screen")
    object GalleryScreen : FeaturesScreen("gallery_screen")
    object InboxScreen : FeaturesScreen("inbox_screen")
    object MessagingScreen : FeaturesScreen("messaging_screen")
    object SettingScreen : FeaturesScreen("setting_screen")
    object VideoScreen : FeaturesScreen("video_screen")
    object MusicScreen : FeaturesScreen("music_screen")
    object DetailScreen : FeaturesScreen("detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}