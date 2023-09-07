

package academy.compose.home

import academy.compose.home.Tags.TAG_CONTENT_ICON
import academy.compose.home.Tags.TAG_CONTENT_TITLE
import academy.compose.home.model.Destination
import academy.compose.home.ui.ContentArea
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class ContentAreaTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Destination_Displayed() {
        val destination = Destination.Feed
        composeTestRule.setContent {
            ContentArea(destination = destination)
        }
        composeTestRule.onNodeWithTag(destination.path)
            .assertIsDisplayed()
    }

    @Test
    fun Content_Title_Displayed() {
        val destination = Destination.Contacts
        composeTestRule.setContent {
            ContentArea(destination = destination)
        }
        composeTestRule.onNodeWithTag(TAG_CONTENT_TITLE)
            .assert(hasText(destination.title))
    }

    @Test
    fun Content_Icon_Displayed() {
        val destination = Destination.Contacts
        composeTestRule.setContent {
            ContentArea(destination = destination)
        }
        composeTestRule.onNodeWithTag(TAG_CONTENT_ICON)
            .assertContentDescriptionEquals(destination.title)
    }
}