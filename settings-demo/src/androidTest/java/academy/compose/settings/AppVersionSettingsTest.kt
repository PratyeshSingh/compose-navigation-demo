

package academy.compose.settings

import academy.compose.settings.ui.AppVersionSettingItem
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class AppVersionSettingsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun App_Version_Displayed() {
        val version = "v2.4.0"
        composeTestRule.setContent {
            AppVersionSettingItem(appVersion = version)
        }

        composeTestRule.onNodeWithText(version)
            .assertIsDisplayed()
    }
}