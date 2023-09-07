

package academy.compose.authentication.ui

import academy.compose.authentication.R
import academy.compose.authentication.model.AuthenticationMode
import academy.compose.authentication.ui.Tags.TAG_AUTHENTICATION_TOGGLE
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ToggleAuthenticationMode(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    toggleAuthentication: () -> Unit
) {
    Surface(modifier = modifier, shadowElevation = 8.dp) {
        TextButton(
            modifier = Modifier.testTag(TAG_AUTHENTICATION_TOGGLE),
            onClick = {
                toggleAuthentication()
            }) {
            Text(
                text = stringResource(
                    id = if (authenticationMode == AuthenticationMode.SIGN_IN) {
                        R.string.action_need_account
                    } else R.string.action_already_have_account
                )
            )
        }
    }
}

@Preview
@Composable
fun Preview_ToggleAuthenticationMode() {
    MaterialTheme {
        ToggleAuthenticationMode(
            modifier = Modifier.fillMaxWidth(),
            authenticationMode = AuthenticationMode.SIGN_IN,
            toggleAuthentication = { }
        )
    }
}