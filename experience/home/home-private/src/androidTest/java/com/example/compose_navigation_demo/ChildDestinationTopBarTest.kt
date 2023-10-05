

package com.example.compose_navigation_demo

import com.example.home.model.Destination
import com.example.home.ui.ChildDestinationTopBar
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import com.example.home.R

class ChildDestinationTopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Title_Displayed() {
        val title = com.example.home.model.Destination.Feed.title
        composeTestRule.setContent {
            com.example.home.ui.ChildDestinationTopBar(title = title) { }
        }

        composeTestRule.onNodeWithText(title)
            .assertIsDisplayed()
    }

    @Test
    fun Navigation_Icon_Displayed() {
        composeTestRule.setContent {
            com.example.home.ui.ChildDestinationTopBar(title = com.example.home.model.Destination.Feed.title) { }
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.cd_navigate_up)
        ).assertIsDisplayed()
    }

    @Test
    fun Navigation_Icon_Triggers_Callback() {
        val onUpClicked: () -> Unit = mock()
        composeTestRule.setContent {
            com.example.home.ui.ChildDestinationTopBar(
                title = com.example.home.model.Destination.Feed.title,
                onNavigateUp = onUpClicked
            )
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.cd_navigate_up)
        ).performClick()

        verify(onUpClicked).invoke()
    }
}