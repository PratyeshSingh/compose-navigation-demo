

package academy.compose.messaging.ui

import academy.compose.messaging.R
import academy.compose.messaging.Tags.TAG_EMPTY
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EmptyConversation(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.testTag(TAG_EMPTY),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.label_no_messages))
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_Empty() {
    MaterialTheme {
        EmptyConversation(
            modifier = Modifier.fillMaxSize()
        )
    }
}