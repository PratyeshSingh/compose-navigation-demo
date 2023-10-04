

package academy.compose.inbox

import academy.compose.inbox.ui.InboxScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class InboxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InboxScreen()
        }
    }
}
