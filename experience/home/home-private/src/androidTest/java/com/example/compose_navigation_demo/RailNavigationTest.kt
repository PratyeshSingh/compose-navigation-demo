

package com.example.compose_navigation_demo

import com.example.home.Tags.TAG_RAIL_CREATE
import com.example.home.Tags.TAG_RAIL_NAVIGATION
import com.example.home.model.Destination
import com.example.home.ui.RailNavigationBar
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class RailNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Navigation_Rail_Displayed() {
        composeTestRule.setContent {
            com.example.home.ui.RailNavigationBar(
                currentDestination = com.example.home.model.Destination.Feed,
                onNavigate = { },
                onCreateItem = { }
            )
        }
        composeTestRule.onNodeWithTag(TAG_RAIL_NAVIGATION)
            .assertIsDisplayed()
    }

    @Test
    fun Header_Displayed() {
        composeTestRule.setContent {
            com.example.home.ui.RailNavigationBar(
                currentDestination = com.example.home.model.Destination.Feed,
                onNavigate = { },
                onCreateItem = { }
            )
        }
        composeTestRule.onNodeWithTag(TAG_RAIL_CREATE)
            .assertIsDisplayed()
    }

    @Test
    fun Navigation_Rail_Items_Displayed() {
        composeTestRule.setContent {
            com.example.home.ui.RailNavigationBar(
                currentDestination = com.example.home.model.Destination.Feed,
                onNavigate = { },
                onCreateItem = { }
            )
        }
        listOf(
            com.example.home.model.Destination.Feed,
            com.example.home.model.Destination.Contacts,
            com.example.home.model.Destination.Calendar
        ).forEach {
            composeTestRule.onNodeWithText(
                it.title
            ).assertIsDisplayed()
        }
    }

    @Test
    fun Current_Destination_Selected() {
        val destination = com.example.home.model.Destination.Contacts
        composeTestRule.setContent {
            com.example.home.ui.RailNavigationBar(
                currentDestination = destination,
                onNavigate = { },
                onCreateItem = { }
            )
        }
        composeTestRule.onNodeWithText(destination.title)
            .assertIsSelected()
        composeTestRule.onNodeWithText(com.example.home.model.Destination.Feed.title)
            .assertIsNotSelected()
        composeTestRule.onNodeWithText(com.example.home.model.Destination.Calendar.title)
            .assertIsNotSelected()
    }

    @Test
    fun Create_Item_Callback_Triggered() {
        val onCreateItem: () -> Unit = mock()
        composeTestRule.setContent {
            com.example.home.ui.RailNavigationBar(
                currentDestination = com.example.home.model.Destination.Feed,
                onNavigate = { },
                onCreateItem = onCreateItem
            )
        }

        composeTestRule.onNodeWithTag(TAG_RAIL_CREATE)
            .performClick()

        verify(onCreateItem).invoke()
    }

    @Test
    fun Navigation_Callback_Triggered() {
        val destination = com.example.home.model.Destination.Contacts
        val onNavigate: (destination: com.example.home.model.Destination) -> Unit = mock()
        composeTestRule.setContent {
            com.example.home.ui.RailNavigationBar(
                currentDestination = com.example.home.model.Destination.Feed,
                onNavigate = onNavigate,
                onCreateItem = {}
            )
        }

        composeTestRule.onNodeWithText(destination.title)
            .performClick()

        verify(onNavigate).invoke(destination)
    }
}