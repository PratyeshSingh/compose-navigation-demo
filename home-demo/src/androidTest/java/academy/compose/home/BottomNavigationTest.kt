

package academy.compose.home

import academy.compose.home.Tags.TAG_BOTTOM_NAVIGATION
import academy.compose.home.model.Destination
import academy.compose.home.ui.BottomNavigationBar
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
            BottomNavigationBar(currentDestination = Destination.Feed, onNavigate = { })
        }
        composeTestRule.onNodeWithTag(TAG_BOTTOM_NAVIGATION)
            .assertIsDisplayed()
    }

    @Test
    fun Bottom_Navigation_Items_Displayed() {
        composeTestRule.setContent {
            BottomNavigationBar(currentDestination = Destination.Feed, onNavigate = { })
        }
        listOf(
            Destination.Feed,
            Destination.Contacts,
            Destination.Calendar
        ).forEach {
            composeTestRule.onNodeWithText(
                it.title
            ).assertIsDisplayed()
        }
    }

    @Test
    fun Bottom_Navigation_Callback_Triggered() {
        val destination = Destination.Contacts
        val onNavigate: (destination: Destination) -> Unit = mock()
        composeTestRule.setContent {
            BottomNavigationBar(currentDestination = Destination.Feed, onNavigate = onNavigate)
        }

        composeTestRule.onNodeWithText(destination.title)
            .performClick()

        verify(onNavigate).invoke(destination)
    }

    @Test
    fun Current_Destination_Selected() {
        val destination = Destination.Contacts
        composeTestRule.setContent {
            BottomNavigationBar(currentDestination = destination, onNavigate = { })
        }
        composeTestRule.onNodeWithText(destination.title)
            .assertIsSelected()
        composeTestRule.onNodeWithText(Destination.Feed.title)
            .assertIsNotSelected()
        composeTestRule.onNodeWithText(Destination.Calendar.title)
            .assertIsNotSelected()
    }
}