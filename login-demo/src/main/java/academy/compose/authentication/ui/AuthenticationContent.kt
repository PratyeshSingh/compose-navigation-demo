

package academy.compose.authentication.ui

import academy.compose.authentication.model.AuthenticationEvent
import academy.compose.authentication.model.AuthenticationState
import academy.compose.authentication.ui.Tags.TAG_PROGRESS
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AuthenticationContent(
    state: AuthenticationState,
    handleEvent: (event: AuthenticationEvent) -> Unit
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_AuthenticationContent() {
    MaterialTheme {
        AuthenticationContent(state = AuthenticationState(), handleEvent = { })
    }
}