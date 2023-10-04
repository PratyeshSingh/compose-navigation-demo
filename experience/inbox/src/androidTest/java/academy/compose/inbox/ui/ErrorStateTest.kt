

package academy.compose.inbox.ui

import academy.compose.inbox.R
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class ErrorStateTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Refresh_Triggered() {
        val onTryAgain: () -> Unit = mock()
        composeTestRule.setContent {
            ErrorState(onTryAgain = onTryAgain)
        }

        composeTestRule
            .onNodeWithText(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.label_try_again)
            )
            .performClick()

        verify(onTryAgain).invoke()
    }

}