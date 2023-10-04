

package academy.compose.inbox.model

sealed class InboxEvent {

    object RefreshContent: InboxEvent()

    class DeleteEmail(val id: String): InboxEvent()
}
