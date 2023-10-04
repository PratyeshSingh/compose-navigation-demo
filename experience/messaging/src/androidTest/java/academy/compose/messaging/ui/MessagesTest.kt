

package academy.compose.messaging.ui

import academy.compose.messaging.MessageFactory
import academy.compose.messaging.ui.message.Messages
import academy.compose.messaging.Tags.TAG_EMPTY
import academy.compose.messaging.Tags.TAG_MESSAGE
import academy.compose.messaging.Tags.TAG_MESSAGES
import academy.compose.messaging.util.groupMessagesByDate
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
@ExperimentalAnimationApi
class MessagesTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Messages_Displayed() {
        val messages = MessageFactory.makeMessages()
        composeTestRule.setContent {
            Messages(
                messages = MessageFactory.makeMessages(),
                onMessageSelected = { },
                unSendMessage = { }
            )
        }
        groupMessagesByDate(messages).forEach { entry ->
            composeTestRule.onNodeWithTag(TAG_MESSAGES)
                .onChildren()
                .assertAny(hasTestTag(entry.key.timeInMillis.toString()))

            entry.value.forEach { message ->
                composeTestRule.onNodeWithTag(TAG_MESSAGES)
                    .onChildren()
                    .assertAny(hasTestTag(TAG_MESSAGE + message.id))
            }
        }
    }

    @Test
    fun Empty_Messages_Never_Displayed() {
        composeTestRule.setContent {
            Messages(
                messages = MessageFactory.makeMessages(),
                onMessageSelected = { },
                unSendMessage = { }
            )
        }

        composeTestRule.onNodeWithTag(TAG_EMPTY)
            .assertDoesNotExist()
    }

    @Test
    fun Empty_Messages_Displayed() {
        composeTestRule.setContent {
            Messages(
                messages = emptyList(),
                onMessageSelected = { },
                unSendMessage = { }
            )
        }

        composeTestRule.onNodeWithTag(TAG_EMPTY)
            .assertIsDisplayed()
    }

}
