

package com.example.home.ui

import com.example.home.model.Destination
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.Home.path
    ) {
        navigation(
            startDestination = Destination.Feed.path,
            route = Destination.Home.path,
        ) {
            composable(Destination.Feed.path) {
                FeedContent(
                    modifier = Modifier.fillMaxSize(),
                    destination = Destination.Feed
                )
            }

            composable(Destination.Contacts.path) {
                ContentArea(
                    modifier = Modifier.fillMaxSize(),
                    destination = Destination.Contacts
                )
            }

            composable(Destination.Calendar.path) {
                ContentArea(
                    modifier = Modifier.fillMaxSize(),
                    destination = Destination.Calendar
                )
            }
        }

        navigation(
            startDestination = Destination.Add.path,
            route = Destination.Creation.path
        ) {
            composable(route = Destination.Add.path) {
                ContentArea(
                    modifier = Modifier.fillMaxSize(),
                    destination = Destination.Add
                )
            }
        }

        composable(Destination.Settings.path) {
            ContentArea(
                modifier = Modifier.fillMaxSize(),
                destination = Destination.Settings
            )
        }

        composable(Destination.Upgrade.path) {
            ContentArea(
                modifier = Modifier.fillMaxSize(),
                destination = Destination.Upgrade
            )
        }
    }
}