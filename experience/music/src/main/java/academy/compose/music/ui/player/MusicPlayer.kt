package academy.compose.music.ui.player

import academy.compose.music.R
import academy.compose.music.Tags.TAG_PLAYER
import academy.compose.music.Tags.TAG_PLAYER_BAR
import academy.compose.music.model.MusicCatalogEvent
import academy.compose.music.model.MusicDashboardState
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.snap
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@Composable
fun MusicPlayer(
    modifier: Modifier = Modifier,
    scaffoldState: BottomSheetScaffoldState,
    state: MusicDashboardState,
    handleEvent: (event: MusicCatalogEvent) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    if (scaffoldState.bottomSheetState.targetValue == SheetValue.Expanded) {
        state.nowPlaying?.let { nowPlaying ->
            Player(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag(TAG_PLAYER),
                nowPlaying = nowPlaying,
                rewindTrack = {
                    handleEvent(MusicCatalogEvent.RewindNowPlaying)
                },
                fastForwardTrack = {
                    handleEvent(MusicCatalogEvent.FastForwardNowPlaying)
                },
                onSeekChanged = {
                    handleEvent(MusicCatalogEvent.SeekTrack(it))
                },
                toggleNowPlayingState = {
                    handleEvent(MusicCatalogEvent.ToggleNowPlayingState)
                },
                onClose = {
                    coroutineScope.launch {
                        scaffoldState.bottomSheetState.show()
                    }
                }
            )
        }
    }

    if (scaffoldState.bottomSheetState.targetValue == SheetValue.PartiallyExpanded) {
        Column {
            Divider(
                modifier = Modifier.height(2.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
            )
            val onClickLabel = stringResource(id = R.string.action_show_player)
            PlayerBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClickLabel = onClickLabel
                    ) {
                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    }
                    .testTag(TAG_PLAYER_BAR),
                nowPlaying = state.nowPlaying,
                toggleNowPlayingState = {
                    handleEvent(MusicCatalogEvent.ToggleNowPlayingState)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@Preview
@Composable
fun Preview_MusicPlayer() {
    MaterialTheme {
        MusicPlayer(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = rememberBottomSheetScaffoldState(),
            state = MusicDashboardState(),
            handleEvent = { }
        )
    }
}