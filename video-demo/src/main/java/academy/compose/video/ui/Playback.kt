package academy.compose.video.ui

import academy.compose.video.model.PlayerStatus
import android.content.Context
import android.view.ViewGroup
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun Playback(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    state: PlayerStatus,
    context: Context,
    exoPlayer: ExoPlayer
) {
    val currentPlayerStatus by rememberUpdatedState(state)
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (currentPlayerStatus == PlayerStatus.PLAYING) {
                if (event == Lifecycle.Event.ON_RESUME) {
                    exoPlayer.play()
                } else if (event == Lifecycle.Event.ON_PAUSE) {
                    exoPlayer.pause()
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(exoPlayer, block = {
        exoPlayer.prepare()
    })

    DisposableEffect(
        AndroidView(
            modifier = modifier,
            factory = {
                PlayerView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    hideController()
                    useController = false
                    player = exoPlayer
                }
            },
            update = { playerView ->
                when (state) {
                    PlayerStatus.PLAYING -> {
                        playerView.player?.play()
                    }

                    PlayerStatus.PAUSED -> {
                        playerView.player?.pause()
                    }

                    PlayerStatus.LOADING -> {

                    }
                    PlayerStatus.IDLE -> {

                    }
                    PlayerStatus.ERROR -> {

                    }
                }
            })
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
}