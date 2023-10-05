

package com.example.auth

import com.example.auth.model.AuthenticationState
import com.example.auth.ui.Authentication
import com.example.auth.ui.AuthenticationContent
import com.example.auth.ui.Tags.TAG_AUTHENTICATE_BUTTON
import com.example.auth.ui.Tags.TAG_AUTHENTICATION_TOGGLE
import com.example.auth.ui.Tags.TAG_CONTENT
import com.example.auth.ui.Tags.TAG_ERROR_ALERT
import com.example.auth.ui.Tags.TAG_INPUT_EMAIL
import com.example.auth.ui.Tags.TAG_INPUT_PASSWORD
import com.example.auth.ui.Tags.TAG_PROGRESS
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class AuthenticationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    //<editor-fold desc="Authentication Mode">
    @Test
    fun Sign_In_Title_Displayed_By_Default() {
        composeTestRule.setContent { Authentication(onDone = { }) }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.title_sign_in)
        ).assertIsDisplayed()
    }

    @Test
    fun Need_Account_Displayed_By_Default() {
        composeTestRule.setContent { Authentication(onDone = { }) }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.action_need_account)
        ).assertIsDisplayed()
    }

    @Test
    fun Sign_Up_Title_Displayed_After_Toggle() {
        composeTestRule.setContent { Authentication(onDone = { }) }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.action_need_account)
        ).performClick()

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.title_sign_up)
        ).assertIsDisplayed()
    }

    @Test
    fun Sign_Up_Button_Displayed_After_Toggle() {
        composeTestRule.setContent { Authentication(onDone = { }) }

        composeTestRule.onNodeWithTag(
            TAG_AUTHENTICATION_TOGGLE
        ).performClick()

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATE_BUTTON)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.action_sign_up)
            )
    }

    @Test
    fun Already_Have_Account_Displayed_After_Toggle() {
        composeTestRule.setContent { Authentication(onDone = { }) }

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATION_TOGGLE).apply {
            performClick()
            assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.action_already_have_account)
            )
        }
    }
    //</editor-fold>

    //<editor-fold desc="Content Validity">
    @Test
    fun Authentication_Button_Disabled_By_Default() {
        composeTestRule.setContent { Authentication(onDone = { }) }

        composeTestRule
            .onNodeWithTag(TAG_AUTHENTICATE_BUTTON)
            .assertIsNotEnabled()
    }

    @Test
    fun Authentication_Button_Enabled_With_Valid_Content() {
        composeTestRule.setContent { Authentication(onDone = { }) }

        composeTestRule.onNodeWithTag(TAG_INPUT_EMAIL)
            .performTextInput("contact@compose.academy")
        composeTestRule.onNodeWithTag(TAG_INPUT_PASSWORD)
            .performTextInput("password")

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATE_BUTTON)
            .assertIsEnabled()
    }
    //</editor-fold>

    //<editor-fold desc="Error alert">
    @Test
    fun Error_Alert_Not_Displayed_By_Default() {
        composeTestRule.setContent { Authentication(onDone = { }) }

        composeTestRule.onNodeWithTag(TAG_ERROR_ALERT)
            .assertDoesNotExist()
    }

    @Test
    fun Error_Alert_Displayed_After_Error() {
        composeTestRule.setContent {
            AuthenticationContent(
                state = com.example.auth.model.AuthenticationState(error = "Some error"),
                handleEvent = { },
                onDone = { },
            )
        }

        composeTestRule.onNodeWithTag(TAG_ERROR_ALERT)
            .assertIsDisplayed()
    }
    //</editor-fold>

    //<editor-fold desc="Loading state">
    @Test
    fun Progress_Not_Displayed_By_Default() {
        composeTestRule.setContent { Authentication(onDone = { }) }

        composeTestRule.onNodeWithTag(TAG_PROGRESS)
            .assertDoesNotExist()
    }

    @Test
    fun Progress_Displayed_While_Loading() {
        composeTestRule.setContent {
            AuthenticationContent(
                state = AuthenticationState(
                    isLoading = true
                ),
                handleEvent = { },
                onDone = { },
            )
        }
        composeTestRule.onNodeWithTag(TAG_PROGRESS)
            .assertIsDisplayed()
    }

    @Test
    fun Content_Displayed_When_Not_Loading() {
        composeTestRule.setContent { Authentication(onDone = { }) }

        composeTestRule.onNodeWithTag(TAG_CONTENT)
            .assertIsDisplayed()
    }

    @Test
    fun Content_Not_Displayed_When_Loading() {
        composeTestRule.setContent {
            AuthenticationContent(
                state = com.example.auth.model.AuthenticationState(
                    isLoading = true
                ),
                handleEvent = { },
                onDone = { },
            )
        }

        composeTestRule.onNodeWithTag(TAG_CONTENT)
            .assertDoesNotExist()
    }
    //</editor-fold>
}