

package academy.compose.home.ui

import academy.compose.home.R
import academy.compose.home.Tags.TAG_RAIL_CREATE
import academy.compose.home.Tags.TAG_RAIL_NAVIGATION
import academy.compose.home.model.Destination
import academy.compose.home.model.NavigationBarItem.Companion.buildNavigationItems
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RailNavigationBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit,
    onCreateItem: () -> Unit
) {
    NavigationRail(
        modifier = modifier.testTag(TAG_RAIL_NAVIGATION),
        header = {
            FloatingActionButton(
                modifier = Modifier.testTag(TAG_RAIL_CREATE),
                onClick = {
                    onCreateItem()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.cd_create_item)
                )
            }
        }
    ) {
        buildNavigationItems(
            currentDestination = currentDestination,
            onNavigate = onNavigate
        ).forEach { destination ->
            NavigationRailItem(
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
fun Preview_RailNavigationBar() {
    MaterialTheme {
        RailNavigationBar(
            modifier = Modifier.fillMaxHeight(),
            currentDestination = Destination.Feed,
            onNavigate = {},
            onCreateItem = {}
        )
    }
}