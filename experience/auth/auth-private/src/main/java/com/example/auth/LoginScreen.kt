package com.example.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.auth.theme.ComposenavigationdemoTheme

@Composable
fun LoginScreen(onClick: (AuthAction) -> Unit) {
    ComposenavigationdemoTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.errorContainer),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Login", style = MaterialTheme.typography.headlineLarge)
                AuthButton("Submit") { onClick(AuthAction.SUBMIT) }
                AuthButton("Register") { onClick(AuthAction.REGISTER) }
                AuthButton("Forgot") { onClick(AuthAction.FORGOT) }
            }
        }
    }
}
