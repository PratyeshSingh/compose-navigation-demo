

package academy.compose.home.model

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class NavigationBarItem(
    val selected: Boolean,
    val onClick: () -> Unit,
    val icon: @Composable () -> Unit,
    val label: @Composable () -> Unit
) {

    companion object {
        fun buildNavigationItems(
            currentDestination: Destination,
            onNavigate: (destination: Destination) -> Unit
        ): List<NavigationBarItem> {
            return listOf(
                Destination.Feed,
                Destination.Contacts,
                Destination.Calendar
            ).map {  destination ->
                NavigationBarItem(
                    selected = currentDestination == destination,
                    label = {
                        Text(text = destination.title)
                    },
                    icon = {
                        destination.icon?.let { image ->
                            Icon(
                                imageVector = image,
                                contentDescription = destination.path
                            )
                        }
                    },
                    onClick = {
                        onNavigate(destination)
                    }
                )
            }
        }
    }

}