

package academy.compose.home.ui

import academy.compose.home.Tags.TAG_BOTTOM_NAVIGATION
import academy.compose.home.model.Destination
import academy.compose.home.model.NavigationBarItem.Companion.buildNavigationItems
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit
) {
    NavigationBar(modifier = modifier.testTag(TAG_BOTTOM_NAVIGATION)) {
        buildNavigationItems(
            currentDestination = currentDestination,
            onNavigate = onNavigate
        ).forEach { destination ->
            NavigationBarItem(
                selected = destination.selected,
                icon = { destination.icon() },
                label = { destination.label() },
                onClick = { destination.onClick() }
            )
        }
    }
}

@Preview
@Composable
fun Preview_BottomNavigationBar() {
    MaterialTheme {
        BottomNavigationBar(
            modifier = Modifier.fillMaxWidth(),
            currentDestination = Destination.Contacts,
            onNavigate = { }
        )
    }
}