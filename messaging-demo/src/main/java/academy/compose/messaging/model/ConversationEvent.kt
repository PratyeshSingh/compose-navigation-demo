

package academy.compose.messaging.model

sealed class ConversationEvent {

    class SelectMessage(val id: String) : ConversationEvent()

    object UnselectMessage : ConversationEvent()

    class UnsendMessage(val id: String) : ConversationEvent()

    data class SendMessage(val message: String) : ConversationEvent()
}