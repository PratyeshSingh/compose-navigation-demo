

package com.example.auth

import academy.compose.authentication.R
import com.example.auth.model.AuthenticationMode
import com.example.auth.ui.AuthenticationButton
import com.example.auth.ui.Tags.TAG_AUTHENTICATE_BUTTON
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

class AuthenticationButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Sign_In_Action_Displayed() {
        composeTestRule.setContent {
            com.example.auth.ui.AuthenticationButton(
                authenticationMode = com.example.auth.model.AuthenticationMode.SIGN_IN,
                enableAuthentication = true
            ) {

            }
        }

        composeTestRule
            .onNodeWithTag(TAG_AUTHENTICATE_BUTTON)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.action_sign_in)
            )
    }

    @Test
    fun Sign_Up_Action_Displayed() {
        composeTestRule.setContent {
            com.example.auth.ui.AuthenticationButton(
                authenticationMode = com.example.auth.model.AuthenticationMode.SIGN_UP,
                enableAuthentication = true
            ) {

            }
        }

        composeTestRule
            .onNodeWithTag(TAG_AUTHENTICATE_BUTTON)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.action_sign_up)
            )
    }

    @Test
    fun Authenticate_Triggered() {
        val authenticate: () -> Unit = mock()
        composeTestRule.setContent {
            com.example.auth.ui.AuthenticationButton(
                authenticationMode = com.example.auth.model.AuthenticationMode.SIGN_UP,
                enableAuthentication = true,
                onAuthenticate = authenticate
            )
        }

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATE_BUTTON)
            .performClick()

        verify(authenticate).invoke()
    }

    @Test
    fun Authenticate_Never_Triggered() {
        val authenticate: () -> Unit = mock()
        composeTestRule.setContent {
            com.example.auth.ui.AuthenticationButton(
                authenticationMode = com.example.auth.model.AuthenticationMode.SIGN_UP,
                enableAuthentication = false,
                onAuthenticate = authenticate
            )
        }

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATE_BUTTON)
            .performClick()

        verify(authenticate, never()).invoke()
    }
}