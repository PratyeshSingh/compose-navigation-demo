

package academy.compose.settings

import academy.compose.settings.ui.Settings
import academy.compose.settings.Tags.TAG_CHECK_ITEM
import academy.compose.settings.Tags.TAG_MARKETING_OPTION
import academy.compose.settings.Tags.TAG_TOGGLE_ITEM
import androidx.annotation.StringRes
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    //<editor-fold desc="Settings are displayed">
    @Test
    fun Enable_Notifications_Setting_Is_Displayed() {
        assertSettingIsDisplayed(R.string.notification_settings)
    }

    @Test
    fun Show_Hints_Setting_Is_Displayed() {
        assertSettingIsDisplayed(R.string.hint_settings)
    }

    @Test
    fun Manage_Subscription_Setting_Is_Displayed() {
        assertSettingIsDisplayed(R.string.subscription_settings)
    }

    @Test
    fun Marketing_Setting_Is_Displayed() {
        assertSettingIsDisplayed(R.string.setting_option_marketing)
    }

    @Test
    fun Theme_Setting_Is_Displayed() {
        assertSettingIsDisplayed(R.string.setting_option_theme)
    }

    @Test
    fun App_Version_Setting_Is_Displayed() {
        assertSettingIsDisplayed(R.string.setting_app_version)
    }

    private fun assertSettingIsDisplayed(
        @StringRes title: Int
    ) {
        composeTestRule.setContent {
            Settings()
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(title)
        ).assertIsDisplayed()
    }
    //</editor-fold>

    @Test
    fun Enable_Notifications_Toggles_Selected_State() {
        composeTestRule.setContent {
            Settings()
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.notification_settings)
        ).performClick()

        composeTestRule.onNodeWithTag(TAG_TOGGLE_ITEM)
            .assertIsOn()
    }

    @Test
    fun Show_Hints_Toggles_Selected_State() {
        composeTestRule.setContent {
            Settings()
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.hint_settings)
        ).performClick()

        composeTestRule.onNodeWithTag(TAG_CHECK_ITEM)
            .assertIsOn()
    }

    @Test
    fun Marketing_Option_Toggles_Selected_State() {
        composeTestRule.setContent {
            Settings()
        }
        val selectedOption = 1
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext.resources
                .getStringArray(R.array.setting_options_marketing_choice)[selectedOption]
        ).performClick()

        composeTestRule.onNodeWithTag(TAG_MARKETING_OPTION + selectedOption)
            .assertIsSelected()
    }
}