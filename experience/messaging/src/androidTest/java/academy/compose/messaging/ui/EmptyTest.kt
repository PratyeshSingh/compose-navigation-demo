

package academy.compose.messaging.ui

import academy.compose.messaging.R
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

@ExperimentalAnimationApi
class EmptyTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Empty_Message_Displayed() {
        composeTestRule.setContent {
            EmptyConversation()
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.label_no_messages)
        ).assertIsDisplayed()
    }

}