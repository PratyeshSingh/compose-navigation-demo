

package com.example.auth

import academy.compose.authentication.R
import com.example.auth.model.AuthenticationMode
import com.example.auth.ui.AuthenticationTitle
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class AuthenticationTitleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Sign_In_Title_Displayed() {
        composeTestRule.setContent {
            com.example.auth.ui.AuthenticationTitle(authenticationMode = com.example.auth.model.AuthenticationMode.SIGN_IN)
        }

        composeTestRule
            .onNodeWithText(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.title_sign_in)
            )
            .assertIsDisplayed()
    }

    @Test
    fun Sign_Up_Title_Displayed() {
        composeTestRule.setContent {
            com.example.auth.ui.AuthenticationTitle(authenticationMode = com.example.auth.model.AuthenticationMode.SIGN_UP)
        }

        composeTestRule
            .onNodeWithText(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.title_sign_up)
            )
            .assertIsDisplayed()
    }
}