package academy.compose.music

import academy.compose.music.ui.MusicCatalog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.animation.ExperimentalAnimationApi

@ExperimentalAnimationApi
class MusicActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MusicCatalog()
            }
        }
    }
}