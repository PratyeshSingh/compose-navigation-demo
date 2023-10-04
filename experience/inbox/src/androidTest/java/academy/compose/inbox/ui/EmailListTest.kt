

package academy.compose.inbox.ui

import academy.compose.inbox.Tags
import academy.compose.inbox.model.EmailFactory
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalAnimationApi
class EmailListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @ExperimentalTestApi
    @Test
    fun Email_Items_Displayed() {
        val emails = EmailFactory.makeContentList()
        composeTestRule.setContent {
            EmailList(
                emails = emails,
                onEmailDeleted = { }
            )
        }

        emails.forEachIndexed { index, email ->
            composeTestRule.onNodeWithTag(Tags.TAG_CONTENT)
                .onChildAt(index)
                .performScrollTo()
                .assert(hasTestTag(Tags.TAG_EMAIL + email.id))
        }
    }

    @Test
    fun Delete_Email_Triggered() {
        val emails = EmailFactory.makeContentList()
        val deleteEmail: (id: String) -> Unit = mock()
        val indexToDelete = 2
        composeTestRule.setContent {
            EmailList(
                emails = emails,
                onEmailDeleted = deleteEmail
            )
        }

        composeTestRule.onNodeWithTag(Tags.TAG_CONTENT)
            .onChildAt(indexToDelete)
            .performScrollTo()
            .performTouchInput {
                swipeRight()
            }
        composeTestRule.waitForIdle()
        verify(deleteEmail).invoke(emails[indexToDelete].id)
    }
}