package com.example.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun MainScreen(onClick: (HomeAction) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Main", style = MaterialTheme.typography.headlineLarge)
        AuthButton("Gallery Demo") { onClick(HomeAction.GALLERY) }
        AuthButton("Inbox Demo") { onClick(HomeAction.INBOX) }
        AuthButton("Messaging Demo") { onClick(HomeAction.MESSAGING) }
        AuthButton("Settings Demo") { onClick(HomeAction.SETTING) }
        AuthButton("Video Demo") { onClick(HomeAction.VIDEOSCREEN) }
        AuthButton("Music Demo") { onClick(HomeAction.MUSIC) }
        AuthButton("Details") { onClick(HomeAction.DETAILS) }
        AuthButton("About Us") { onClick(HomeAction.ABOUT) }
    }
}


@Composable
internal fun AuthButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = text, style = MaterialTheme.typography.titleSmall)
    }
}