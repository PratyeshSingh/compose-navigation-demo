

package academy.compose.messaging.ui

import academy.compose.messaging.ui.input.Mentions
import academy.compose.messaging.ContactFactory
import academy.compose.messaging.Tags.TAG_CONTACTS
import academy.compose.messaging.util.stripMentionSymbol
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.util.*

@ExperimentalAnimationApi
class MentionsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Contacts_Displayed() {
        val contacts = ContactFactory.makeContacts()
        composeTestRule.setContent {
            Mentions(
                contacts = contacts,
                query = "@",
                onMentionClicked = { _, _ ->

                }
            )
        }

        contacts.forEachIndexed { index, contact ->
            composeTestRule.onNodeWithTag(TAG_CONTACTS)
                .onChildAt(index)
                .assertTextEquals(contact.name)
        }
    }

    @Test
    fun Filtered_Contacts_Displayed() {
        val contacts = ContactFactory.makeContacts()
        val query = "@j"
        val mentions = contacts.filter {
            val withoutMentionSymbol =
                stripMentionSymbol(query.lowercase(Locale.getDefault())) ?: ""
            it.name.lowercase(Locale.getDefault()).startsWith(withoutMentionSymbol)
        }
        composeTestRule.setContent {
            Mentions(
                contacts = contacts,
                query = query,
                onMentionClicked = { _, _ ->

                }
            )
        }

        mentions.forEachIndexed { index, contact ->
            composeTestRule.onNodeWithTag(TAG_CONTACTS)
                .onChildAt(index)
                .assertTextEquals(contact.name)
        }
    }

    @Test
    fun Contact_Triggers_Callback() {
        val onMentionClicked: (query: String, mention: String) -> Unit = mock()
        val contacts = ContactFactory.makeContacts()
        val query = "@"
        composeTestRule.setContent {
            Mentions(
                contacts = contacts,
                query = query,
                onMentionClicked = onMentionClicked
            )
        }

        composeTestRule.onNodeWithTag(TAG_CONTACTS)
            .onChildAt(1)
            .performClick()
        verify(onMentionClicked).invoke(query, "@" + contacts[1].name)
    }

}