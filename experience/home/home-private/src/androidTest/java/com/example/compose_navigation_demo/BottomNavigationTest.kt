

package com.example.compose_navigation_demo

import com.example.home.Tags.TAG_BOTTOM_NAVIGATION
import com.example.home.model.Destination
import com.example.home.ui.BottomNavigationBar
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class BottomNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Bottom_Navigation_Displayed() {
        composeTestRule.setContent {
            com.example.home.ui.BottomNavigationBar(
                currentDestination = com.example.home.model.Destination.Feed,
                onNavigate = { })
        }
        composeTestRule.onNodeWithTag(TAG_BOTTOM_NAVIGATION)
            .assertIsDisplayed()
    }

    @Test
    fun Bottom_Navigation_Items_Displayed() {
        composeTestRule.setContent {
            com.example.home.ui.BottomNavigationBar(
                currentDestination = com.example.home.model.Destination.Feed,
                onNavigate = { })
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
    fun Bottom_Navigation_Callback_Triggered() {
        val destination = com.example.home.model.Destination.Contacts
        val onNavigate: (destination: com.example.home.model.Destination) -> Unit = mock()
        composeTestRule.setContent {
            com.example.home.ui.BottomNavigationBar(
                currentDestination = com.example.home.model.Destination.Feed,
                onNavigate = onNavigate
            )
        }

        composeTestRule.onNodeWithText(destination.title)
            .performClick()

        verify(onNavigate).invoke(destination)
    }

    @Test
    fun Current_Destination_Selected() {
        val destination = com.example.home.model.Destination.Contacts
        composeTestRule.setContent {
            com.example.home.ui.BottomNavigationBar(
                currentDestination = destination,
                onNavigate = { })
        }
        composeTestRule.onNodeWithText(destination.title)
            .assertIsSelected()
        composeTestRule.onNodeWithText(com.example.home.model.Destination.Feed.title)
            .assertIsNotSelected()
        composeTestRule.onNodeWithText(com.example.home.model.Destination.Calendar.title)
            .assertIsNotSelected()
    }
}