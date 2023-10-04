package academy.compose.music.ui.player

import academy.compose.music.ContentFactory
import academy.compose.music.Tags.TAG_PLAY_PAUSE
import academy.compose.music.Tags.TAG_SEEK_BAR
import academy.compose.music.Tags.TAG_TRACK_ARTIST
import academy.compose.music.Tags.TAG_TRACK_TITLE
import academy.compose.music.ui.track.CoverArt
import academy.compose.music.model.NowPlaying
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PlayerBar(
    modifier: Modifier = Modifier,
    nowPlaying: NowPlaying?,
    toggleNowPlayingState: () -> Unit
) {
    nowPlaying?.let {
        Column(
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CoverArt(
                    modifier = Modifier.size(32.dp),
                    track = nowPlaying.track
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .semantics(mergeDescendants = true) { }
                ) {
                    Text(
                        modifier = Modifier.testTag(TAG_TRACK_TITLE),
                        text = nowPlaying.track.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                    Text(
                        modifier = Modifier.testTag(TAG_TRACK_ARTIST),
                        text = nowPlaying.track.artist,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
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
                            id = descriptionForNowPlayingState(
                                nowPlaying.state
                            )
                        )
                    )
                }
            }
            PlayerSeekBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(TAG_SEEK_BAR)
                    .padding(vertical = 10.dp),
                nowPlaying = nowPlaying,
                canSeekTrack = false
            )
        }
    }
}

@Preview
@Composable
fun Preview_PlayerBar() {
    MaterialTheme {
        PlayerBar(
            modifier = Modifier.fillMaxWidth(),
            nowPlaying = ContentFactory.makeNowPlaying(ContentFactory.makeTrack()),
            toggleNowPlayingState = { }
        )
    }
}
