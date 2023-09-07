

package academy.compose.inbox.ui

import academy.compose.inbox.R
import academy.compose.inbox.model.InboxEvent
import academy.compose.inbox.model.InboxState
import academy.compose.inbox.model.InboxStatus
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInbox(
    modifier: Modifier = Modifier,
    inboxState: InboxState,
    handleEvent: (event: InboxEvent) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(
                            id = R.string.inbox_emails,
                            inboxState.emails?.count() ?: 0),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.DarkGray)
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(it),
            contentAlignment = Alignment.Center
        ) {
            when (inboxState.status) {
                InboxStatus.LOADING -> Loading()
                InboxStatus.ERROR -> {
                    ErrorState(modifier = Modifier.fillMaxWidth()) {
                        handleEvent(InboxEvent.RefreshContent)
                    }
                }
                InboxStatus.SUCCESS -> {
                    EmailList(
                        modifier = Modifier.fillMaxSize(),
                        emails = inboxState.emails!!,
                        onEmailDeleted = { id ->
                            handleEvent(InboxEvent.DeleteEmail(id))
                        }
                    )
                }
                else -> {
                    EmptyState(modifier = Modifier.fillMaxWidth()) {
                        handleEvent(InboxEvent.RefreshContent)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview_EmailInbox_Success() {
    MaterialTheme {
        EmailInbox(inboxState = InboxState(
            status = InboxStatus.SUCCESS
        ), handleEvent = { })
    }
}

@Preview
@Composable
fun Preview_EmailInbox_Loading() {
    MaterialTheme {
        EmailInbox(inboxState = InboxState(
            status = InboxStatus.LOADING
        ), handleEvent = { })
    }
}

@Preview
@Composable
fun Preview_EmailInbox_Error() {
    MaterialTheme {
        EmailInbox(inboxState = InboxState(
            status = InboxStatus.ERROR
        ), handleEvent = { })
    }
}

@Preview
@Composable
fun Preview_EmailInbox_Empty() {
    MaterialTheme {
        EmailInbox(inboxState = InboxState(
            status = InboxStatus.EMPTY
        ), handleEvent = { })
    }
}