

package academy.compose.inbox

import academy.compose.inbox.ui.Inbox
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import academy.compose.inbox.ui.theme.ComposeByExampleTheme

class InboxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeByExampleTheme {
                Inbox()
            }
        }
    }
}
