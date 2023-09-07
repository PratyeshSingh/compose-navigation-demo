

package academy.compose.home

import academy.compose.home.model.Destination
import academy.compose.home.ui.ChildDestinationTopBar
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

class ChildDestinationTopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Title_Displayed() {
        val title = Destination.Feed.title
        composeTestRule.setContent {
            ChildDestinationTopBar(title = title) { }
        }

        composeTestRule.onNodeWithText(title)
            .assertIsDisplayed()
    }

    @Test
    fun Navigation_Icon_Displayed() {
        composeTestRule.setContent {
            ChildDestinationTopBar(title = Destination.Feed.title) { }
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
            ChildDestinationTopBar(title = Destination.Feed.title, onNavigateUp = onUpClicked)
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.cd_navigate_up)
        ).performClick()

        verify(onUpClicked).invoke()
    }
}