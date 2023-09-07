

package academy.compose.messaging.ui

import academy.compose.messaging.MessageFactory
import academy.compose.messaging.model.ConversationEvent
import academy.compose.messaging.model.ConversationState
import academy.compose.messaging.ui.input.InputBar
import academy.compose.messaging.ui.message.MessageActions
import academy.compose.messaging.ui.message.Messages
import academy.compose.messaging.ContactFactory
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun Conversation(
    modifier: Modifier = Modifier,
    state: ConversationState,
    handleEvent: (event: ConversationEvent) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Header(
            modifier = Modifier.fillMaxWidth(),
            onClose = {
                // finish activity
            }
        )
        Messages(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            messages = state.messages,
            onMessageSelected = { messageId ->
                handleEvent(ConversationEvent.SelectMessage(messageId))
            },
            unSendMessage = { messageId ->
                handleEvent(ConversationEvent.UnsendMessage(messageId))
            }
        )
        InputBar(
            modifier = Modifier.fillMaxWidth(),
            sendMessage = { message ->
                handleEvent(ConversationEvent.SendMessage(message))
            },
            contacts = state.contacts
        )
    }
    if (state.selectedMessage != null) {
        MessageActions(
            onDismiss = {
                handleEvent(ConversationEvent.UnselectMessage)
            },
            onUnsendMessage = {
                handleEvent(ConversationEvent.UnsendMessage(state.selectedMessage.id))
            }
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun Preview_Conversation() {
    MaterialTheme {
        Conversation(
            modifier = Modifier.fillMaxSize(),
            state = ConversationState(
                messages = MessageFactory.makeMessages(),
                contacts = ContactFactory.makeContacts()
            ),
            handleEvent = { }
        )
    }
}