

package academy.compose.authentication.ui

import academy.compose.authentication.R
import academy.compose.authentication.ui.Tags.TAG_INPUT_PASSWORD
import academy.compose.authentication.ui.Tags.TAG_PASSWORD_TOGGLE
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    password: String?,
    onPasswordChanged: (password: String) -> Unit,
    onDoneClicked: () -> Unit
) {
    var isPasswordHidden by remember {
        mutableStateOf(true)
    }

    TextField(
        modifier = modifier.testTag(TAG_INPUT_PASSWORD),
        value = password ?: "",
        onValueChange = onPasswordChanged,
        singleLine = true,
        label = {
            Text(text = stringResource(id = R.string.label_password))
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = null)
        },
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable(
                    onClickLabel = if (isPasswordHidden) {
                        stringResource(id = R.string.cd_show_password)
                    } else stringResource(id = R.string.cd_hide_password)
                ) {
                    isPasswordHidden = !isPasswordHidden
                }.testTag(TAG_PASSWORD_TOGGLE + isPasswordHidden),
                imageVector = if (isPasswordHidden) {
                    Icons.Default.Lock
                } else Icons.Default.Home,
                contentDescription = null
            )
        },
        keyboardActions = KeyboardActions(
            onDone = {
                onDoneClicked()
            }
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (isPasswordHidden) {
            PasswordVisualTransformation()
        } else VisualTransformation.None
    )
}

@Preview(showBackground = true)
@Composable
fun Preview_PasswordInput() {
    MaterialTheme {
        PasswordInput(
            modifier = Modifier.fillMaxWidth(),
            password = "12345678",
            onPasswordChanged = { },
            onDoneClicked = { }
        )
    }
}