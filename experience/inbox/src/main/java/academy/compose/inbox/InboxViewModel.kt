

package academy.compose.inbox

import academy.compose.inbox.model.EmailFactory
import academy.compose.inbox.model.InboxEvent
import academy.compose.inbox.model.InboxState
import academy.compose.inbox.model.InboxStatus
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class InboxViewModel : ViewModel() {

    val uiState = MutableStateFlow(InboxState())

    fun loadContent() {
        uiState.value = uiState.value.copy(
            status = InboxStatus.LOADING
        )
        uiState.value = uiState.value.copy(
            status = InboxStatus.SUCCESS,
            emails = EmailFactory.makeContentList()
        )
    }

    private fun deleteEmail(id: String) {
        uiState.value = uiState.value.copy(
            emails = uiState.value.emails?.filter {
                it.id != id
            }
        )
    }

    fun handleEvent(inboxEvent: InboxEvent) {
        when (inboxEvent) {
            is InboxEvent.RefreshContent -> loadContent()
            is InboxEvent.DeleteEmail -> deleteEmail(inboxEvent.id)
        }
    }

}