

package academy.compose.inbox.ui

import academy.compose.inbox.R
import academy.compose.inbox.Tags.TAG_CONTENT
import academy.compose.inbox.Tags.TAG_EMAIL
import academy.compose.inbox.Tags.TAG_EMPTY
import academy.compose.inbox.Tags.TAG_ERROR
import academy.compose.inbox.Tags.TAG_PROGRESS
import academy.compose.inbox.model.EmailFactory
import academy.compose.inbox.model.InboxState
import academy.compose.inbox.model.InboxStatus
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

@ExperimentalAnimationApi
class EmailInboxTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Inbox_Title_Displayed() {
        val inboxState = InboxState(emails = EmailFactory.makeContentList())
        composeTestRule.setContent {
            EmailInbox(inboxState = inboxState) { }
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(
                R.string.inbox_emails, inboxState.emails!!.count()
            )
        ).assertIsDisplayed()
    }

    @Test
    fun Loading_State_Displayed() {
        composeTestRule.run {
            setContent {
                EmailInbox(inboxState = InboxState(status = InboxStatus.LOADING)) { }
            }

            onNodeWithTag(TAG_PROGRESS).assertIsDisplayed()
            onNodeWithTag(TAG_CONTENT).assertDoesNotExist()
            onNodeWithTag(TAG_ERROR).assertDoesNotExist()
            onNodeWithTag(TAG_EMPTY).assertDoesNotExist()
        }
    }

    @Test
    fun Empty_State_Displayed() {
        composeTestRule.run {
            setContent {
                EmailInbox(
                    inboxState = InboxState(status = InboxStatus.EMPTY),
                    handleEvent = { }
                )
            }
            onNodeWithTag(TAG_PROGRESS).assertDoesNotExist()
            onNodeWithTag(TAG_CONTENT).assertDoesNotExist()
            onNodeWithTag(TAG_ERROR).assertDoesNotExist()
            onNodeWithTag(TAG_EMPTY).assertIsDisplayed()
        }
    }

    @Test
    fun Error_State_Displayed() {
        composeTestRule.run {
            setContent {
                EmailInbox(
                    inboxState = InboxState(status = InboxStatus.ERROR),
                    handleEvent = { }
                )
            }
            onNodeWithTag(TAG_PROGRESS).assertDoesNotExist()
            onNodeWithTag(TAG_CONTENT).assertDoesNotExist()
            onNodeWithTag(TAG_ERROR).assertIsDisplayed()
            onNodeWithTag(TAG_EMPTY).assertDoesNotExist()
        }
    }

    @Test
    fun Content_State_Displayed() {
        composeTestRule.run {
            setContent {
                EmailInbox(
                    inboxState = InboxState(
                        status = InboxStatus.SUCCESS,
                        emails = EmailFactory.makeContentList()
                    ),
                    handleEvent = { }
                )
            }
            onNodeWithTag(TAG_PROGRESS).assertDoesNotExist()
            onNodeWithTag(TAG_CONTENT).assertIsDisplayed()
            onNodeWithTag(TAG_ERROR).assertDoesNotExist()
            onNodeWithTag(TAG_EMPTY).assertDoesNotExist()
        }
    }

    @Test
    fun Content_Displayed_After_Refresh() {
        val inboxState = mutableStateOf(InboxState(status = InboxStatus.EMPTY))
        composeTestRule.setContent {
            EmailInbox(inboxState = inboxState.value) {
                inboxState.value = InboxState(
                    status = InboxStatus.SUCCESS,
                    emails = EmailFactory.makeContentList()
                )
            }
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.label_check_again)
        ).performClick()

        composeTestRule.onNodeWithTag(TAG_CONTENT)
            .assertIsDisplayed()
    }

    @Test
    fun Empty_State_Hidden_After_Refresh() {
        val inboxState = mutableStateOf(InboxState(status = InboxStatus.EMPTY))
        composeTestRule.setContent {
            EmailInbox(inboxState = inboxState.value) {
                inboxState.value = InboxState(
                    status = InboxStatus.SUCCESS,
                    emails = EmailFactory.makeContentList()
                )
            }
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.label_check_again)
        ).performClick()

        composeTestRule.onNodeWithTag(TAG_EMPTY)
            .assertDoesNotExist()
    }

    @Test
    fun Content_Displayed_After_Retry() {
        val inboxState = mutableStateOf(InboxState(status = InboxStatus.ERROR))
        composeTestRule.setContent {
            EmailInbox(inboxState = inboxState.value) {
                inboxState.value = InboxState(
                    status = InboxStatus.SUCCESS,
                    emails = EmailFactory.makeContentList()
                )
            }
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.label_try_again)
        ).performClick()

        composeTestRule.onNodeWithTag(TAG_CONTENT)
            .assertIsDisplayed()
    }

    @Test
    fun Error_State_Hidden_After_Refresh() {
        val inboxState = mutableStateOf(InboxState(status = InboxStatus.ERROR))
        composeTestRule.setContent {
            EmailInbox(inboxState = inboxState.value) {
                inboxState.value = InboxState(
                    status = InboxStatus.SUCCESS,
                    emails = EmailFactory.makeContentList()
                )
            }
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.label_try_again)
        ).performClick()

        composeTestRule.onNodeWithTag(TAG_ERROR)
            .assertDoesNotExist()
    }


    @ExperimentalTestApi
    @Test
    fun Item_Dismissed_When_Swiped() {
        composeTestRule.setContent {
            Inbox()
        }

        val emails = EmailFactory.makeContentList()

        composeTestRule.onNodeWithTag(TAG_CONTENT)
            .onChildAt(0)
            .performTouchInput {
                swipeRight()
            }

        composeTestRule.onNodeWithTag(TAG_CONTENT)
            .onChildren()
            .assertCountEquals(emails.count() - 1)
    }

    @Test
    fun Remaining_Items_Displayed_When_Another_Dismissed() {
        composeTestRule.setContent {
            Inbox()
        }

        composeTestRule.onNodeWithTag(TAG_CONTENT)
            .onChildAt(0)
            .performTouchInput {
                swipeRight()
            }

        val emails = EmailFactory.makeContentList()
        emails.takeLast(emails.count() - 1).forEachIndexed { index, email ->
            composeTestRule
                .onNodeWithTag(TAG_CONTENT)
                .assertIsDisplayed()
                .onChildAt(index)
                .performScrollTo()
                .assert(hasTestTag(TAG_EMAIL + email.id))
        }
    }
}