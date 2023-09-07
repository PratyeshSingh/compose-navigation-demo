

package academy.compose.home.ui

import academy.compose.home.model.Destination
import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun Body(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    destination: Destination,
    orientation: Int,
    onNavigate: (destination: Destination) -> Unit,
    onCreateItem: () -> Unit
) {
    Row(modifier = modifier) {
        if (destination.isRootDestination && orientation == Configuration.ORIENTATION_LANDSCAPE) {
            RailNavigationBar(
                currentDestination = destination,
                onNavigate = onNavigate,
                onCreateItem = onCreateItem
            )
        }
        Navigation(
            modifier = Modifier.fillMaxSize(),
            navController = navController
        )
    }
}