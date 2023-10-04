package com.example.home

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object GalleryScreen : Screen("gallery_screen")
    object InboxScreen : Screen("inbox_screen")
    object MessagingScreen : Screen("messaging_screen")
    object SettingScreen : Screen("setting_screen")
    object VideoScreen : Screen("video_screen")
    object MusicScreen : Screen("music_screen")
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