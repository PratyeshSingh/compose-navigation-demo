

package academy.compose.home

import academy.compose.home.ui.Home
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import academy.compose.home.ui.theme.ComposeByExampleTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeByExampleTheme {
                Home(
                    modifier = Modifier.fillMaxSize(),
                    orientation = LocalConfiguration.current.orientation
                )
            }
        }
    }
}