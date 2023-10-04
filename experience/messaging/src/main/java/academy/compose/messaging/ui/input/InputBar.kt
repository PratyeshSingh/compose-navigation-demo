package academy.compose.messaging.ui.input

import academy.compose.messaging.R
import academy.compose.messaging.model.Contact
import academy.compose.messaging.ContactFactory
import academy.compose.messaging.Tags.TAG_INPUT_BAR
import academy.compose.messaging.Tags.TAG_MENTIONS
import academy.compose.messaging.Tags.TAG_MESSAGE_INPUT
import academy.compose.messaging.Tags.TAG_SEND_MESSAGE
import academy.compose.messaging.util.replaceText
import academy.compose.messaging.util.buildAnnotatedStringWithColors
import academy.compose.messaging.util.inputShouldTriggerSuggestions
import academy.compose.messaging.util.selectedWord
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBar(
    modifier: Modifier = Modifier,
    sendMessage: (message: String) -> Unit,
    contacts: List<Contact>
) {
    Column(modifier = modifier.testTag(TAG_INPUT_BAR)) {
        var textState by remember { mutableStateOf(TextFieldValue("")) }
        val showMentions by derivedStateOf {
            textState.text.isNotEmpty() && inputShouldTriggerSuggestions(
                contacts,
                selectedWord(textState)
            )
        }
        if (showMentions) {
            Mentions(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(TAG_MENTIONS),
                contacts = contacts,
                query = selectedWord(textState)
            ) { query, result ->
                val startIndex = textState.text.indexOf(query)
                textState = textState.replaceText(
                    startIndex,
                    startIndex + query.length, result
                )
            }
        }
        Divider()
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .testTag(TAG_MESSAGE_INPUT),
            visualTransformation = MentionHighlightTransformation(MaterialTheme.colorScheme.primary),
            value = textState,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.hint_send_message)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            onValueChange = {
                textState = it
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier.testTag(TAG_SEND_MESSAGE),
                    onClick = {
                        sendMessage(textState.text)
                        textState = TextFieldValue()
                    },
                    enabled = textState.text.isNotEmpty()
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = stringResource(id = R.string.cd_send_message),
                        tint = if (textState.text.isNotEmpty()) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurface
                                .copy(alpha = 1f)
                        }
                    )
                }
            }
        )
    }
}

class MentionHighlightTransformation(private val color: Color) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            buildAnnotatedStringWithColors(text.toString(), color),
            OffsetMapping.Identity
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_InputBar() {
    MaterialTheme {
        InputBar(
            modifier = Modifier.fillMaxWidth(),
            sendMessage = { },
            contacts = ContactFactory.makeContacts()
        )
    }
}