

package com.example.compose_navigation_demo

import com.example.home.Tags.TAG_CONTENT_ICON
import com.example.home.Tags.TAG_CONTENT_TITLE
import com.example.home.model.Destination
import com.example.home.ui.ContentArea
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class ContentAreaTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Destination_Displayed() {
        val destination = com.example.home.model.Destination.Feed
        composeTestRule.setContent {
            com.example.home.ui.ContentArea(destination = destination)
        }
        composeTestRule.onNodeWithTag(destination.path)
            .assertIsDisplayed()
    }

    @Test
    fun Content_Title_Displayed() {
        val destination = com.example.home.model.Destination.Contacts
        composeTestRule.setContent {
            com.example.home.ui.ContentArea(destination = destination)
        }
        composeTestRule.onNodeWithTag(TAG_CONTENT_TITLE)
            .assert(hasText(destination.title))
    }

    @Test
    fun Content_Icon_Displayed() {
        val destination = com.example.home.model.Destination.Contacts
        composeTestRule.setContent {
            com.example.home.ui.ContentArea(destination = destination)
        }
        composeTestRule.onNodeWithTag(TAG_CONTENT_ICON)
            .assertContentDescriptionEquals(destination.title)
    }
}