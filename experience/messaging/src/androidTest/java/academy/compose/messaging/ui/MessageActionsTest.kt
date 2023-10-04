

package academy.compose.messaging.ui

import academy.compose.messaging.R
import academy.compose.messaging.ui.message.MessageActions
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class MessageActionsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Unsend_Action_Displayed() {
        composeTestRule.setContent {
            MessageActions(onDismiss = {}, onUnsendMessage = {})
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.action_unsend_message)
        ).assertIsDisplayed()
    }

    @Test
    fun Unsend_Action_Triggers_Callback() {
        val unsendListener: () -> Unit = mock()
        composeTestRule.setContent {
            MessageActions(onDismiss = {}, onUnsendMessage = unsendListener)
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.action_unsend_message)
        ).performClick()

        verify(unsendListener).invoke()
    }
}