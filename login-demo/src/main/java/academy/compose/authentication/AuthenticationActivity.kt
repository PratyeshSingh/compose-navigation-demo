

package academy.compose.authentication

import academy.compose.authentication.ui.Authentication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class AuthenticationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Authentication()
        }
    }
}
