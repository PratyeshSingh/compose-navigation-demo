

package com.example.compose_navigation_demo

import com.example.home.model.Destination
import com.example.home.ui.Navigation
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Feed_Displayed_By_Default() {
        composeTestRule.setContent {
            com.example.home.ui.Navigation(navController = rememberNavController())
        }
        composeTestRule.onNodeWithTag(com.example.home.model.Destination.Feed.path)
            .assertIsDisplayed()
    }

    @Test
    fun Contacts_Displayed() {
        assertNavigation(com.example.home.model.Destination.Contacts)
    }

    @Test
    fun Calendar_Displayed() {
        assertNavigation(com.example.home.model.Destination.Calendar)
    }

    @Test
    fun Create_Displayed() {
        assertNavigation(com.example.home.model.Destination.Add)
    }

    @Test
    fun Upgrade_Displayed() {
        assertNavigation(com.example.home.model.Destination.Upgrade)
    }

    @Test
    fun Settings_Displayed() {
        assertNavigation(com.example.home.model.Destination.Settings)
    }

    private fun assertNavigation(destination: com.example.home.model.Destination) {
        composeTestRule.setContent {
            val navController = rememberNavController()
            com.example.home.ui.Navigation(navController = navController)
            navController.navigate(destination.path)
        }
        composeTestRule.onNodeWithTag(destination.path)
            .assertIsDisplayed()
    }
}
