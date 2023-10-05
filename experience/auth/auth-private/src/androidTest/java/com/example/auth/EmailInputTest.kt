

package com.example.auth

import com.example.auth.ui.EmailInput
import com.example.auth.ui.Tags.TAG_INPUT_EMAIL
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.TextRange
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class EmailInputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Email_Displayed() {
        val email = "contact@compose.academy"
        composeTestRule.setContent {
            com.example.auth.ui.EmailInput(email = email, onEmailChanged = {}, onNextClicked = { })
        }

        composeTestRule
            .onNodeWithTag(TAG_INPUT_EMAIL)
            .assert(hasText(email))
    }

    @ExperimentalTestApi
    @Test
    fun Email_Change_Triggered() {
        val onEmailChanged: (email: String) -> Unit = mock()
        val email = "contact@compose.academy"
        composeTestRule.setContent {
            com.example.auth.ui.EmailInput(
                email = email,
                onEmailChanged = onEmailChanged,
                onNextClicked = { })
        }

        val appendedText = ".jetpack"
        composeTestRule
            .onNodeWithTag(TAG_INPUT_EMAIL)
            .apply {
                performTextInputSelection(TextRange(email.length))
                performTextInput(appendedText)
            }

        verify(onEmailChanged).invoke(email + appendedText)
    }
}