package com.example.compose_navigation_demo.component.features

import academy.compose.video.ui.recorder.RecorderActivity
import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext

@Composable
fun FeatureList(onClick: (HomeAction) -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionButton("Gallery Demo") { onClick(HomeAction.GALLERY) }
        ActionButton("Inbox Demo") { onClick(HomeAction.INBOX) }
        ActionButton("Messaging Demo") { onClick(HomeAction.MESSAGING) }
        ActionButton("Settings Demo") { onClick(HomeAction.SETTING) }
        ActionButton("Video Demo") { onClick(HomeAction.VIDEOSCREEN) }
        ActionButton("Record or Capture") {
            context.startActivity(Intent(context, RecorderActivity::class.java))
        }
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