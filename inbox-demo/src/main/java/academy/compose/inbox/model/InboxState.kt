

package academy.compose.inbox.model

data class InboxState(
    val status: InboxStatus = InboxStatus.LOADING,
    val emails: List<Email>? = null
)