

package academy.compose.inbox.ui

import academy.compose.inbox.R
import academy.compose.inbox.Tags
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EmptyState(
    modifier: Modifier = Modifier,
    onCheckAgain: () -> Unit
) {
    Column(
        modifier = modifier.testTag(Tags.TAG_EMPTY),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.empty_status))
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            onCheckAgain()
        }) {
            Text(text = stringResource(id = R.string.label_check_again))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_EmptyState() {
    MaterialTheme {
        EmptyState(modifier = Modifier.fillMaxSize(), onCheckAgain = { })
    }
}