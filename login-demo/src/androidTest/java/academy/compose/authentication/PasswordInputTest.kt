

package academy.compose.authentication

import academy.compose.authentication.ui.PasswordInput
import academy.compose.authentication.ui.Tags.TAG_INPUT_PASSWORD
import academy.compose.authentication.ui.Tags.TAG_PASSWORD_TOGGLE
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.TextRange
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class PasswordInputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Password_Displayed() {
        val password = "password123"
        composeTestRule.setContent {
            PasswordInput(password = password, onPasswordChanged = {}, onDoneClicked = {})
        }
        composeTestRule.onNodeWithTag(TAG_PASSWORD_TOGGLE + "true")
            .performClick()
        composeTestRule.onNodeWithTag(TAG_INPUT_PASSWORD)
            .assert(hasText(password))
    }

    @ExperimentalTestApi
    @Test
    fun Password_Changed_Triggered() {
        val onPasswordChanged: (password: String) -> Unit = mock()
        val password = "password123"
        composeTestRule.setContent {
            PasswordInput(
                password = password,
                onPasswordChanged = onPasswordChanged,
                onDoneClicked = {}
            )
        }
        val passwordText = "456"
        composeTestRule
            .onNodeWithTag(TAG_INPUT_PASSWORD)
            .apply {
                performTextInputSelection(TextRange(password.length))
                performTextInput(passwordText)
            }

        verify(onPasswordChanged).invoke(password + passwordText)
    }

    @Test
    fun Password_Toggled_Reflects_State() {
        composeTestRule.setContent {
            PasswordInput(password = "password123", onPasswordChanged = {}, onDoneClicked = {})
        }

        composeTestRule
            .onNodeWithTag(TAG_PASSWORD_TOGGLE + "true")
            .performClick()

        composeTestRule
            .onNodeWithTag(TAG_PASSWORD_TOGGLE + "false")
            .assertIsDisplayed()
    }
}