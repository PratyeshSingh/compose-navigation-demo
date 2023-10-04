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
        AuthButton("Gallery") { onClick(HomeAction.GALLERY) }
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