

package academy.compose.messaging.ui

import academy.compose.messaging.R
import academy.compose.messaging.Tags.TAG_HEADER
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    modifier: Modifier = Modifier,
    onClose: () -> Unit
) {
    TopAppBar(
        modifier = modifier.testTag(TAG_HEADER),
        title = {
            Text(text = stringResource(id = R.string.title_chat))
        },
        navigationIcon = {
            IconButton(onClick = {
                onClose()
            }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.cd_close_conversation)
                )
            }
        },
        actions = {

        }

    )
}

@Preview(showBackground = true)
@Composable
fun Preview_Header() {
    MaterialTheme {
        Header(
            modifier = Modifier.fillMaxWidth()
        ) {

        }
    }
}