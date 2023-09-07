

package academy.compose.home.ui

import academy.compose.home.R
import academy.compose.home.model.Destination
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootDestinationTopBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    openDrawer: () -> Unit,
    showSnackbar: (message: String) -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        navigationIcon = {
            IconButton(onClick = {
                openDrawer()
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(
                        id = R.string.cd_open_menu
                    )
                )
            }
        },
        actions = {
            if (currentDestination != Destination.Feed) {
                val snackbarMessage = stringResource(id = R.string.not_available_yet)
                IconButton(onClick = {
                    showSnackbar(snackbarMessage)
                }) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = stringResource(id = R.string.cd_more_info)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun Preview_RootDestinationTopBar() {
    MaterialTheme {
        RootDestinationTopBar(
            modifier = Modifier.fillMaxWidth(),
            currentDestination = Destination.Feed,
            openDrawer = { },
            showSnackbar = { }
        )
    }
}