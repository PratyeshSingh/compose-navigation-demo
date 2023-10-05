

package com.example.auth

import academy.compose.authentication.R
import com.example.auth.ui.AuthenticationErrorDialog
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class AuthenticationErrorDialogTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Error_Displayed() {
        val error = "This is an error"
        composeTestRule.setContent {
            com.example.auth.ui.AuthenticationErrorDialog(error = error, dismissError = { })
        }
        composeTestRule
            .onNodeWithText(error)
            .assertIsDisplayed()
    }

    @Test
    fun Dismiss_Triggered_From_Action() {
        val dismissError: () -> Unit = mock()
        composeTestRule.setContent {
            com.example.auth.ui.AuthenticationErrorDialog(
                error = "This is an error",
                dismissError = dismissError
            )
        }
        composeTestRule
            .onNodeWithText(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.error_action)
            )
            .performClick()

        verify(dismissError).invoke()
    }
}