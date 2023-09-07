

package academy.compose.authentication.ui

import academy.compose.authentication.R
import academy.compose.authentication.model.AuthenticationMode
import academy.compose.authentication.ui.Tags.TAG_AUTHENTICATE_BUTTON
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AuthenticationButton(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    enableAuthentication: Boolean,
    onAuthenticate: () -> Unit
) {
    Button(
        modifier = modifier.testTag(TAG_AUTHENTICATE_BUTTON),
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
}

@Preview
@Composable
fun Preview_AuthenticationButton() {
    MaterialTheme {
        AuthenticationButton(
            modifier = Modifier.fillMaxWidth(),
            authenticationMode = AuthenticationMode.SIGN_UP,
            enableAuthentication = true
        ) {

        }
    }
}