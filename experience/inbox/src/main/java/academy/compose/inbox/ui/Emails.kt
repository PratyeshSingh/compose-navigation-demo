

package academy.compose.inbox.ui

import academy.compose.inbox.R
import academy.compose.inbox.Tags
import academy.compose.inbox.model.Email
import academy.compose.inbox.model.EmailFactory
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailList(
    modifier: Modifier = Modifier,
    emails: List<Email>,
    onEmailDeleted: (id: String) -> Unit
) {
    LazyColumn(modifier = modifier.testTag(Tags.TAG_CONTENT)) {
        items(emails, key = { item -> item.id }) { email ->
            var isEmailItemDismissed by remember {
                mutableStateOf(false)
            }
            val dismissState = rememberDismissState(
                confirmValueChange = {
                    if (it == DismissValue.DismissedToEnd) {
                        isEmailItemDismissed = true
                    }
                    true
                }
            )

            val emailHeightAnimation by animateDpAsState(
                targetValue = if (isEmailItemDismissed) 0.dp else 120.dp,
                animationSpec = tween(delayMillis = 300),
                finishedListener = {
                    onEmailDeleted(email.id)
                }
            )

            val deleteEmailLabel = stringResource(id = R.string.cd_delete_email)
            SwipeToDismiss(
                modifier = Modifier
                    .semantics {
                        customActions = listOf(
                            CustomAccessibilityAction(deleteEmailLabel) {
                                onEmailDeleted(email.id)
                                true
                            }
                        )
                    }
                    .testTag(Tags.TAG_EMAIL + email.id),
                directions = setOf(
                    DismissDirection.StartToEnd
                ),
                background = {
                    EmailItemBackground(
                        modifier = Modifier
                            .height(emailHeightAnimation)
                            .fillMaxWidth(),
                        targetValue = dismissState.targetValue,
                        currentValue = dismissState.currentValue
                    )
                },
                state = dismissState,
                dismissContent = {
                    EmailItem(
                        modifier = Modifier
                            .height(emailHeightAnimation)
                            .fillMaxWidth(),
                        email = email,
                        dismissDirection = dismissState.dismissDirection
                    )
                }
            )
            val dividerVisibilityAnimation by animateFloatAsState(
                targetValue = if (dismissState.targetValue == DismissValue.Default) {
                    1f
                } else 0f,
                animationSpec = tween(delayMillis = 300)
            )
            Divider(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .alpha(dividerVisibilityAnimation)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailItem(
    modifier: Modifier = Modifier,
    email: Email,
    dismissDirection: DismissDirection?
) {
    val cardElevation by animateDpAsState(
        targetValue = if (dismissDirection != null) {
            4.dp
        } else 0.dp
    )
    Card(
        modifier = modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(cardElevation)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = email.title, fontWeight = FontWeight.Bold)
            Divider(modifier = Modifier.height(8.dp))
            Text(
                text = email.description,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailItemBackground(
    modifier: Modifier = Modifier,
    targetValue: DismissValue,
    currentValue: DismissValue
) {
    val backgroundColor by animateColorAsState(
        targetValue = when (targetValue) {
            DismissValue.DismissedToEnd -> MaterialTheme.colorScheme.error
            else -> MaterialTheme.colorScheme.background
        },
        animationSpec = tween()
    )
    val iconColor by animateColorAsState(
        targetValue = when (targetValue) {
            DismissValue.DismissedToEnd -> MaterialTheme.colorScheme.onError
            else -> MaterialTheme.colorScheme.onSurface
        },
        animationSpec = tween()
    )
    val scale by animateFloatAsState(
        targetValue = if (targetValue == DismissValue.DismissedToEnd) {
            1f
        } else 0.75f
    )
    Box(
        modifier = modifier
            .background(backgroundColor)
            .padding(horizontal = 20.dp)
    ) {
        if (currentValue == DismissValue.Default) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .scale(scale),
                imageVector = Icons.Default.Delete,
                tint = iconColor,
                contentDescription = stringResource(id = R.string.cd_delete_email)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Preview_EmailItemBackground() {
    MaterialTheme {
        EmailItemBackground(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            targetValue = DismissValue.DismissedToEnd,
            currentValue = DismissValue.Default
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Preview_EmailItem() {
    MaterialTheme {
        EmailItem(
            modifier = Modifier.fillMaxWidth(),
            email = EmailFactory.makeContentList()[0],
            dismissDirection = DismissDirection.StartToEnd
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_EmailList() {
    MaterialTheme {
        EmailList(
            modifier = Modifier.fillMaxSize(),
            emails = EmailFactory.makeContentList(),
            onEmailDeleted = { }
        )
    }
}