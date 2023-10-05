

package com.example.compose_navigation_demo

import com.example.home.Tags.TAG_CHILD_TOP_BAR
import com.example.home.Tags.TAG_ROOT_TOP_BAR
import com.example.home.model.Destination
import com.example.home.ui.DestinationTopBar
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class DestinationTopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Root_Destination_Top_Bar_Displayed() {
        composeTestRule.setContent {
              DestinationTopBar(
                currentDestination = Destination.Home,
                openDrawer = { },
                onNavigateUp = { },
                showSnackbar = { }
            )
        }
        composeTestRule.onNodeWithTag(TAG_ROOT_TOP_BAR)
            .assertIsDisplayed()
    }

    @Test
    fun Root_Destination_Top_Bar_Never_Displayed() {
        composeTestRule.setContent {
              DestinationTopBar(
                currentDestination = Destination.Add,
                openDrawer = { },
                onNavigateUp = { },
                showSnackbar = { }
            )
        }
        composeTestRule.onNodeWithTag(TAG_ROOT_TOP_BAR)
            .assertDoesNotExist()
    }

    @Test
    fun Child_Top_Bar_Displayed() {
        composeTestRule.setContent {
              DestinationTopBar(
                currentDestination = Destination.Add,
                openDrawer = { },
                onNavigateUp = { },
                showSnackbar = { }
            )
        }
        composeTestRule.onNodeWithTag(TAG_CHILD_TOP_BAR)
            .assertIsDisplayed()
    }

    @Test
    fun Child_Top_Bar_Never_Displayed() {
        composeTestRule.setContent {
              DestinationTopBar(
                currentDestination = Destination.Feed,
                openDrawer = { },
                onNavigateUp = { },
                showSnackbar = { }
            )
        }
        composeTestRule.onNodeWithTag(TAG_CHILD_TOP_BAR)
            .assertDoesNotExist()
    }
}