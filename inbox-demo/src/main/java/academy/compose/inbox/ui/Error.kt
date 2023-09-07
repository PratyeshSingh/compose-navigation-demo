

package academy.compose.inbox.ui

import academy.compose.inbox.R
import academy.compose.inbox.Tags
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorState(
    modifier: Modifier = Modifier,
    onTryAgain: () -> Unit
) {
    Column(
        modifier = modifier.testTag(Tags.TAG_ERROR),
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.error_status)
        )
        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            onTryAgain()
        }) {
            Text(text = stringResource(id = R.string.label_try_again))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ErrorState() {
    MaterialTheme {
        ErrorState(modifier = Modifier.fillMaxWidth(), onTryAgain = {})
    }
}