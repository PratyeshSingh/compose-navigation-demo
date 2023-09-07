

package academy.compose.messaging.ui

import academy.compose.messaging.TestDataFactory.randomString
import academy.compose.messaging.ui.input.InputBar
import academy.compose.messaging.ContactFactory
import academy.compose.messaging.Tags.TAG_MENTIONS
import academy.compose.messaging.Tags.TAG_MESSAGE_INPUT
import academy.compose.messaging.Tags.TAG_SEND_MESSAGE
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalAnimationApi
class InputBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Mentions_Not_Displayed_By_Default() {
        composeTestRule.setContent {
            InputBar(sendMessage = {}, contacts = ContactFactory.makeContacts())
        }
        composeTestRule.onNodeWithTag(
            TAG_MENTIONS
        ).assertDoesNotExist()
    }

    @Test
    fun Mentions_Displayed() {
        composeTestRule.setContent {
            InputBar(sendMessage = {}, contacts = ContactFactory.makeContacts())
        }

        composeTestRule.onNodeWithTag(TAG_MESSAGE_INPUT)
            .performTextInput("@")

        composeTestRule.onNodeWithTag(
            TAG_MENTIONS
        ).assertIsDisplayed()
    }

    @Test
    fun Mentions_Hidden() {
        composeTestRule.setContent {
            InputBar(sendMessage = {}, contacts = ContactFactory.makeContacts())
        }

        composeTestRule.onNodeWithTag(TAG_MESSAGE_INPUT)
            .performTextInput("@joe hello")

        composeTestRule.onNodeWithTag(
            TAG_MENTIONS
        ).assertDoesNotExist()
    }

    @Test
    fun Send_Message_Disabled_With_No_Text() {
        composeTestRule.setContent {
            InputBar(sendMessage = {}, contacts = ContactFactory.makeContacts())
        }

        composeTestRule.onNodeWithTag(TAG_SEND_MESSAGE)
            .assertIsNotEnabled()
    }

    @Test
    fun Send_Message_Enabled_With_Text() {
        composeTestRule.setContent {
            InputBar(sendMessage = {}, contacts = ContactFactory.makeContacts())
        }

        composeTestRule.onNodeWithTag(TAG_MESSAGE_INPUT)
            .performTextInput(randomString())

        composeTestRule.onNodeWithTag(TAG_SEND_MESSAGE)
            .assertIsEnabled()
    }

    @Test
    fun Send_Message_Triggers_Callback() {
        val message = randomString()
        val sendMessage: (message: String) -> Unit = mock()
        composeTestRule.setContent {
            InputBar(sendMessage = sendMessage, contacts = ContactFactory.makeContacts())
        }

        composeTestRule.onNodeWithTag(TAG_MESSAGE_INPUT)
            .performTextInput(message)
        composeTestRule.onNodeWithTag(TAG_SEND_MESSAGE)
            .performClick()

        verify(sendMessage).invoke(message)
    }
}