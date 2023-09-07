

package academy.compose.video

import academy.compose.video.model.PlayerStatus
import academy.compose.video.model.VideoEvent
import academy.compose.video.model.VideoState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class VideoViewModel : ViewModel() {

    val uiState = MutableStateFlow(VideoState())

    fun handleEvent(videoEvent: VideoEvent) {
        when (videoEvent) {
            VideoEvent.VideoLoaded -> {
                uiState.value = uiState.value.copy(
                    playerStatus = PlayerStatus.IDLE
                )
            }
            VideoEvent.VideoError -> {
                uiState.value = uiState.value.copy(
                    playerStatus = PlayerStatus.ERROR
                )
            }
            VideoEvent.ToggleStatus -> togglePlayerStatus()
        }
    }

    private fun togglePlayerStatus() {
        val playerStatus = uiState.value.playerStatus
        val newPlayerStatus = if (playerStatus != PlayerStatus.PLAYING) {
            PlayerStatus.PLAYING
        } else {
            PlayerStatus.PAUSED
        }
        uiState.value = uiState.value.copy(
            playerStatus = newPlayerStatus
        )
    }
}