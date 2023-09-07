

package academy.compose.authentication

import academy.compose.authentication.model.AuthenticationMode
import academy.compose.authentication.ui.Tags.TAG_AUTHENTICATION_TOGGLE
import academy.compose.authentication.ui.ToggleAuthenticationMode
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class AuthenticationModeToggleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Need_Account_Action_Displayed() {
        composeTestRule.setContent {
            ToggleAuthenticationMode(authenticationMode = AuthenticationMode.SIGN_IN) {

            }
        }

        composeTestRule
            .onNodeWithTag(TAG_AUTHENTICATION_TOGGLE)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.action_need_account)
            )
    }

    @Test
    fun Already_Have_Account_Action_Displayed() {
        composeTestRule.setContent {
            ToggleAuthenticationMode(authenticationMode = AuthenticationMode.SIGN_UP) {

            }
        }

        composeTestRule
            .onNodeWithTag(TAG_AUTHENTICATION_TOGGLE)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.action_already_have_account)
            )
    }

    @Test
    fun Toggle_Authentication_Triggered() {
        val toggleAuthentication: () -> Unit = mock()
        composeTestRule.setContent {
            ToggleAuthenticationMode(
                authenticationMode = AuthenticationMode.SIGN_UP,
                toggleAuthentication = toggleAuthentication
            )
        }

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATION_TOGGLE)
            .performClick()

        verify(toggleAuthentication).invoke()
    }
}