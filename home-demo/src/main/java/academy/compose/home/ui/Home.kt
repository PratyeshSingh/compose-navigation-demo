package academy.compose.home.ui

import academy.compose.home.R
import academy.compose.home.model.Destination
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    orientation: Int
) {

    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination by remember {
        derivedStateOf {
            Destination.fromString(navBackStackEntry.value?.destination?.route)
        }
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val openDrawer: () -> Unit = {
        coroutineScope.launch {
            drawerState.open()
        }
    }

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onNavigate = { destination ->
                    navController.navigate(destination.path)
                    coroutineScope.launch {
                        drawerState.close()
                    }
                },
                onLogout = {
                    // handle logout
                }
            )
        },
        content = {
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    DestinationTopBar(
                        modifier = Modifier.fillMaxWidth(),
                        currentDestination = currentDestination,
                        openDrawer = openDrawer,
                        onNavigateUp = navController::popBackStack,
                        showSnackbar = { message ->
                            coroutineScope.launch {
                                // Do Actions
                            }
                        }
                    )
                },
                bottomBar = {
                    if (orientation == Configuration.ORIENTATION_PORTRAIT &&
                        currentDestination.isRootDestination
                    ) {
                        BottomNavigationBar(
                            currentDestination = currentDestination,
                            onNavigate = { destination ->
                                navController.navigate(destination.path) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                },
                floatingActionButton = {
                    if (currentDestination == Destination.Feed
                    ) {
                        FloatingActionButton(onClick = {
                            navController.navigate(Destination.Creation.path)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(id = R.string.cd_create_item)
                            )
                        }
                    }
                }
            ) {
                Body(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    destination = currentDestination,
                    navController = navController,
                    onCreateItem = {
                        navController.navigate(Destination.Add.path)
                    },
                    onNavigate = { destination ->
                        navController.navigate(destination.path) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    orientation = orientation
                )
            }

        }
    )
}

@Preview
@Composable
fun Preview_Home() {
    MaterialTheme {
        Home(modifier = Modifier.fillMaxSize(), orientation = Configuration.ORIENTATION_LANDSCAPE)
    }
}