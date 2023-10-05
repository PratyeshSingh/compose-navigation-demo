

package com.example.auth.ui

import androidx.compose.foundation.layout.Row
import com.example.auth.R
import com.example.auth.model.AuthenticationMode
import com.example.auth.ui.Tags.TAG_AUTHENTICATE_BUTTON
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AuthenticationButton(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    enableAuthentication: Boolean,
    forgotPassword: () -> Unit,
    onAuthenticate: () -> Unit,
) {
    Row {
        Button(
            modifier = modifier.testTag(TAG_AUTHENTICATE_BUTTON)
                .weight(1f)
                .padding(horizontal = 8.dp),
            enabled = enableAuthentication,
            onClick = {
                onAuthenticate()
            }
        ) {
            Text(
                text = stringResource(
                    id = if (authenticationMode == AuthenticationMode.SIGN_IN) {
                        R.string.action_sign_in
                    } else R.string.action_sign_up
                )
            )
        }

        if (authenticationMode == AuthenticationMode.SIGN_IN) {
            Button(
                modifier = modifier.testTag(TAG_AUTHENTICATE_BUTTON)
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                onClick = {
                    forgotPassword()
                }
            ) {
                Text(
                    text = stringResource(
                        id = R.string.action_forgot
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview_AuthenticationButton() {
    MaterialTheme {
        AuthenticationButton(
            modifier = Modifier.fillMaxWidth(),
            authenticationMode = AuthenticationMode.SIGN_UP,
            enableAuthentication = true,
            forgotPassword = { }
        ) {

        }
    }
}