

package com.example.home.ui

import com.example.home.Tags.TAG_CHILD_TOP_BAR
import com.example.home.Tags.TAG_ROOT_TOP_BAR
import com.example.home.model.Destination
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun DestinationTopBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    openDrawer: () -> Unit,
    onNavigateUp: () -> Unit,
    showSnackbar: (message: String) -> Unit
) {
    if (currentDestination.isRootDestination) {
        RootDestinationTopBar(
            modifier = modifier.testTag(TAG_ROOT_TOP_BAR),
            currentDestination = currentDestination,
            openDrawer = openDrawer,
            showSnackbar = { message ->
                showSnackbar(message)
            }
        )
    } else {
        ChildDestinationTopBar(
            modifier = modifier.testTag(TAG_CHILD_TOP_BAR),
            title = currentDestination.title,
            onNavigateUp = onNavigateUp
        )
    }
}