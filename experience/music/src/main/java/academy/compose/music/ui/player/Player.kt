package academy.compose.music.ui.player

import academy.compose.music.ContentFactory
import academy.compose.music.R
import academy.compose.music.ui.track.CoverArt
import academy.compose.music.Tags.TAG_PLAY_PAUSE
import academy.compose.music.Tags.TAG_TRACK_ARTIST
import academy.compose.music.Tags.TAG_TRACK_TITLE
import academy.compose.music.model.NowPlaying
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Player(
    modifier: Modifier = Modifier,
    nowPlaying: NowPlaying,
    toggleNowPlayingState: () -> Unit,
    rewindTrack: () -> Unit,
    fastForwardTrack: () -> Unit,
    onSeekChanged: (position: Float) -> Unit,
    onClose: () -> Unit
) {
    val dateFormat = remember { SimpleDateFormat("mm:ss", Locale.getDefault()) }
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val closeAction = stringResource(id = R.string.cd_close_now_playing)
        Icon(
            modifier = Modifier
                .align(Alignment.Start)
                .clickable(onClickLabel = closeAction) { onClose() },
            imageVector = Icons.Default.Close,
            contentDescription = null
        )

        Spacer(modifier = Modifier.weight(1f))

        CoverArt(
            modifier = Modifier.size(240.dp),
            track = nowPlaying.track
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.semantics(mergeDescendants = true) { }
        ) {
            Text(
                modifier = Modifier.testTag(
                    TAG_TRACK_TITLE
                ),
                fontSize = 22.sp,
                text = nowPlaying.track.title
            )

            Text(
                modifier = Modifier.testTag(
                    TAG_TRACK_ARTIST
                ),
                fontSize = 18.sp,
                text = nowPlaying.track.artist,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(36.dp))

        val horizontalPadding = 32.dp
        PlayerSeekBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding),
            nowPlaying = nowPlaying,
            canSeekTrack = true
        ) {
            onSeekChanged(it)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
        ) {
            Text(text = dateFormat.format(Date(nowPlaying.position * 1000L)))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = dateFormat.format(Date(nowPlaying.track.length * 1000L)))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                    rewindTrack()
                }
            ) {
                Icon(
                    modifier = Modifier.rotate(180f),
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = stringResource(id = R.string.cd_rewind)
                )
            }
            IconButton(
                modifier = Modifier.testTag(TAG_PLAY_PAUSE),
                onClick = {
                    toggleNowPlayingState()
                }
            ) {
                Icon(
                    imageVector = iconForPlayingState(nowPlaying.state),
                    contentDescription = stringResource(
                        id = descriptionForNowPlayingState(nowPlaying.state)
                    )
                )
            }
            IconButton(
                onClick = {
                    fastForwardTrack()
                }
            ) {
                Icon(
                    modifier = Modifier.rotate(90f),
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = stringResource(id = R.string.cd_fast_forward)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
fun Preview_Player() {
    MaterialTheme {
        Player(
            modifier = Modifier.fillMaxWidth(),
            nowPlaying = ContentFactory.makeNowPlaying(ContentFactory.makeTrack()),
            toggleNowPlayingState = { },
            rewindTrack = { },
            fastForwardTrack = { },
            onSeekChanged = { },
            onClose = { }
        )
    }
}