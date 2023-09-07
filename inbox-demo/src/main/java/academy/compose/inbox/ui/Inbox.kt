

package academy.compose.inbox.ui

import academy.compose.inbox.InboxViewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Inbox() {
    val viewModel: InboxViewModel = viewModel()
    MaterialTheme {
        EmailInbox(
            inboxState = viewModel.uiState.collectAsState().value,
            handleEvent = viewModel::handleEvent
        )
    }
    LaunchedEffect(Unit) {
        viewModel.loadContent()
    }
}