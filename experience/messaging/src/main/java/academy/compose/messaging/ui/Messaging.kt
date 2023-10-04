

package academy.compose.messaging.ui

import academy.compose.messaging.ConversationViewModel
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun Messaging() {
    val viewModel: ConversationViewModel = viewModel()
    MaterialTheme {
        Conversation(
            modifier = Modifier.fillMaxSize(),
            state = viewModel.uiState.collectAsState().value,
            handleEvent = viewModel::handleEvent
        )
    }
}