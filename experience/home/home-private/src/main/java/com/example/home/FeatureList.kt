package com.example.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FeatureList(onClick: (HomeAction) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionButton("Gallery Demo") { onClick(HomeAction.GALLERY) }
        ActionButton("Inbox Demo") { onClick(HomeAction.INBOX) }
        ActionButton("Messaging Demo") { onClick(HomeAction.MESSAGING) }
        ActionButton("Settings Demo") { onClick(HomeAction.SETTING) }
        ActionButton("Video Demo") { onClick(HomeAction.VIDEOSCREEN) }
        ActionButton("Music Demo") { onClick(HomeAction.MUSIC) }
        ActionButton("Details") { onClick(HomeAction.DETAILS) }
        ActionButton("About Us") { onClick(HomeAction.ABOUT) }
    }
}


@Composable
internal fun ActionButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = text, style = MaterialTheme.typography.titleSmall)
    }
}