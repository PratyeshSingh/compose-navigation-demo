package com.example.auth

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
fun LoginScreen(onClick: (AuthAction) -> Unit) {
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

@Composable
fun RegistrationScreen(onClick: (AuthAction) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.errorContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Register", style = MaterialTheme.typography.headlineLarge)
        AuthButton("Submit") { onClick(AuthAction.SUBMIT) }
        AuthButton("Login") { onClick(AuthAction.LOGIN) }
    }
}


@Composable
fun ForgotScreen(onClick: (AuthAction) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.errorContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Forgot", style = MaterialTheme.typography.headlineLarge)
        AuthButton("Submit") { onClick(AuthAction.SUBMIT) }
        AuthButton("Login") { onClick(AuthAction.LOGIN) }
    }
}


@Composable
private fun AuthButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = text, style = MaterialTheme.typography.titleSmall)
    }
}