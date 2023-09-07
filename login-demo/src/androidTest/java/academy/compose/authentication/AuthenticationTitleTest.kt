

package academy.compose.authentication

import academy.compose.authentication.model.AuthenticationMode
import academy.compose.authentication.ui.AuthenticationTitle
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
            AuthenticationTitle(authenticationMode = AuthenticationMode.SIGN_IN)
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
            AuthenticationTitle(authenticationMode = AuthenticationMode.SIGN_UP)
        }

        composeTestRule
            .onNodeWithText(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.title_sign_up)
            )
            .assertIsDisplayed()
    }
}