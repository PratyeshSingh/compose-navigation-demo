

package academy.compose.inbox.ui

import academy.compose.inbox.Tags
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Loading(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier = modifier.testTag(Tags.TAG_PROGRESS))
}

@Preview(showBackground = true)
@Composable
fun Preview_Loading() {
    MaterialTheme {
        Loading()
    }
}