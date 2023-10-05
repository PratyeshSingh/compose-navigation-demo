package com.example.home


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.example.home.ui.Home


@Composable
fun MainScreen(onClick: (HomeAction) -> Unit) {
    Home(
        modifier = Modifier.fillMaxSize(),
        orientation = LocalConfiguration.current.orientation,
        onClick = onClick
    )
}
