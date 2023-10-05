

package com.example.auth.ui

import com.example.auth.model.AuthenticationMode
import com.example.auth.model.PasswordRequirement
import com.example.auth.ui.Tags.TAG_CONTENT
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AuthenticationForm(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    email: String?,
    password: String?,
    satisfiedRequirements: List<PasswordRequirement>,
    enableAuthentication: Boolean,
    onEmailChanged: (email: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onAuthenticate: () -> Unit,
    forgotPassword: () -> Unit,
    onToggleMode: () -> Unit
) {
    Column(
        modifier = modifier.testTag(TAG_CONTENT),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        AuthenticationTitle(authenticationMode = authenticationMode)
        Spacer(modifier = Modifier.height(40.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            elevation = CardDefaults.elevatedCardElevation()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val passwordFocusRequester = FocusRequester()
                EmailInput(
                    modifier = Modifier.fillMaxWidth(),
                    email = email,
                    onEmailChanged = onEmailChanged,
                    onNextClicked = {
                        passwordFocusRequester.requestFocus()
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                PasswordInput(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(passwordFocusRequester),
                    password = password,
                    onPasswordChanged = onPasswordChanged,
                    onDoneClicked = {
                        onAuthenticate()
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
                AnimatedVisibility(visible = authenticationMode == AuthenticationMode.SIGN_UP) {
                    PasswordRequirements(
                        modifier = Modifier.fillMaxWidth(),
                        satisfiedRequirements = satisfiedRequirements
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                AuthenticationButton(
                    authenticationMode = authenticationMode,
                    enableAuthentication = enableAuthentication,
                    onAuthenticate = onAuthenticate,
                    forgotPassword = forgotPassword
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        ToggleAuthenticationMode(
            modifier = Modifier.fillMaxWidth(),
            authenticationMode = authenticationMode,
            toggleAuthentication = onToggleMode
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_AuthenticationForm() {
    MaterialTheme {
        AuthenticationForm(
            modifier = Modifier.fillMaxWidth(),
            authenticationMode = AuthenticationMode.SIGN_IN,
            email = "contact@compose.academy",
            password = "12345678",
            satisfiedRequirements = listOf(PasswordRequirement.CAPITAL_LETTER),
            enableAuthentication = true,
            onEmailChanged = { },
            onPasswordChanged = { },
            onAuthenticate = { },
            forgotPassword = { },
            onToggleMode = { }
        )
    }
}