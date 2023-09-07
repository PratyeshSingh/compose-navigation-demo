

package academy.compose.video.ui

import academy.compose.video.R
import academy.compose.video.Tags.TAG_CONTROLS
import academy.compose.video.Tags.TAG_CONTROL_BUTTON
import academy.compose.video.model.PlayerStatus
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Controls(
    modifier: Modifier = Modifier,
    playerStatus: PlayerStatus,
    togglePlayingState: () -> Unit
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .testTag(TAG_CONTROLS),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            modifier = Modifier.testTag(TAG_CONTROL_BUTTON),
            onClick = {
                togglePlayingState()
            },
            enabled = playerStatus != PlayerStatus.LOADING
        ) {
            val icon = if (playerStatus ==
                PlayerStatus.PLAYING
            ) {
                Icons.Default.ThumbUp
            } else Icons.Default.PlayArrow

            val description = if (playerStatus ==
                PlayerStatus.PLAYING
            ) {
                stringResource(id = R.string.cd_pause)
            } else stringResource(id = R.string.cd_play)
            Icon(
                imageVector = icon,
                contentDescription = description
            )
        }
    }
}

@Preview
@Composable
fun Preview_Controls() {
    MaterialTheme {
        Controls(
            modifier = Modifier.fillMaxWidth(),
            playerStatus = PlayerStatus.LOADING,
            togglePlayingState = { }
        )
    }
}