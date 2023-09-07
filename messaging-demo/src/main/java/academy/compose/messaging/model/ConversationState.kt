

package academy.compose.messaging.model

import academy.compose.messaging.ContactFactory
import academy.compose.messaging.MessageFactory

data class ConversationState(
    val messages: List<Message> = MessageFactory.makeMessages(),
    val contacts: List<Contact> = ContactFactory.makeContacts(),
    val selectedMessage: Message? = null
)