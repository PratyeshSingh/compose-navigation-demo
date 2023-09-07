

package academy.compose.settings

import academy.compose.settings.model.Theme
import academy.compose.settings.Tags.TAG_SELECT_THEME
import academy.compose.settings.Tags.TAG_THEME
import academy.compose.settings.Tags.TAG_THEME_OPTION
import academy.compose.settings.ui.ThemeSettingItem
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class ThemeSettingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Selected_Theme_Displayed() {
        val option = Theme.DARK

        composeTestRule.setContent {
            ThemeSettingItem(selectedTheme = option, onThemeSelected = {})
        }

        composeTestRule.onNodeWithTag(TAG_THEME, useUnmergedTree = true)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(option.label)
            )
    }

    @Test
    fun Theme_Options_Displayed() {
        composeTestRule.setContent {
            ThemeSettingItem(selectedTheme = Theme.DARK, onThemeSelected = {})
        }

        composeTestRule.onNodeWithTag(TAG_SELECT_THEME)
            .performClick()

        Theme.values().forEach { theme ->
            composeTestRule.onNodeWithTag(
                TAG_THEME_OPTION + InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(theme.label)
            ).assertIsDisplayed()
        }
    }
}