package com.example.auth.ui

import com.example.auth.model.AuthenticationEvent
import com.example.auth.model.AuthenticationState
import com.example.auth.ui.Tags.TAG_PROGRESS
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.auth.AuthAction

@Composable
fun AuthenticationContent(
    state: AuthenticationState,
    handleEvent: (event: AuthenticationEvent) -> Unit,
    onDone: (AuthAction) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.testTag(TAG_PROGRESS)
            )
        } else {
            AuthenticationForm(
                modifier = Modifier.fillMaxSize(),
                authenticationMode = state.authenticationMode,
                email = state.email,
                password = state.password,
                satisfiedRequirements = state.satisfiedPasswordRequirements,
                enableAuthentication = state.isFormValid(),
                onEmailChanged = {
                    handleEvent(AuthenticationEvent.EmailChanged(it))
                },
                onPasswordChanged = {
                    handleEvent(AuthenticationEvent.PasswordChanged(it))
                },
                onAuthenticate = {
                    handleEvent(AuthenticationEvent.Authenticate)
                },
                forgotPassword = {
                    onDone(AuthAction.FORGOT)
                },
                onToggleMode = {
                    handleEvent(AuthenticationEvent.ToggleAuthenticationMode)
                }
            )
            state.error?.let { error ->
                AuthenticationErrorDialog(
                    error = error,
                    dismissError = {
                        handleEvent(AuthenticationEvent.ErrorDismissed)
                    }
                )
            }
            LaunchedEffect(Unit) {
                if (state.isFormValid() && state.error.isNullOrEmpty()) {
                    onDone(AuthAction.SUBMIT)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_AuthenticationContent() {
    MaterialTheme {
        AuthenticationContent(state = AuthenticationState(),
            handleEvent = { },
            onDone = { }
        )
    }
}