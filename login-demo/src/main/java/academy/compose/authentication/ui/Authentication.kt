

package academy.compose.authentication.ui

import academy.compose.authentication.AuthenticationViewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Authentication() {
    val viewModel: AuthenticationViewModel = viewModel()
    MaterialTheme {
        AuthenticationContent(
            state = viewModel.uiState.collectAsState().value,
            handleEvent = viewModel::handleEvent
        )
    }
}