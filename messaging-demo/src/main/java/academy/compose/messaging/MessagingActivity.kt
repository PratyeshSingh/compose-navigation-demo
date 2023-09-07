

package academy.compose.messaging

import academy.compose.messaging.ui.Messaging
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi

@ExperimentalAnimationApi
@ExperimentalFoundationApi
class MessagingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Messaging()
        }
    }
}
