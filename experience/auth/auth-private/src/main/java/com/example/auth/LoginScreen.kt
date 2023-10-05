package com.example.auth

import androidx.compose.runtime.Composable
import com.example.auth.ui.Authentication

@Composable
fun LoginScreen(onClick: (AuthAction) -> Unit) {
    Authentication(
        onDone = onClick
    )
}
