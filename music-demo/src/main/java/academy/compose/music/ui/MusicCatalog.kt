package academy.compose.music.ui

import academy.compose.music.MusicViewModel
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalAnimationApi
@Composable
fun MusicCatalog() {
    val viewModel = viewModel<MusicViewModel>()
    val state = viewModel.uiState.collectAsState().value

    MaterialTheme {
        Dashboard(
            state = state,
            handleEvent = viewModel::handleEvent
        )
    }
}
