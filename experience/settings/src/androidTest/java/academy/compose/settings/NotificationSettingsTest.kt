

package academy.compose.settings

import academy.compose.settings.ui.NotificationSettings
import academy.compose.settings.Tags.TAG_TOGGLE_ITEM
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class NotificationSettingsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Title_Displayed() {
        val title = "Enable Notifications"

        composeTestRule.setContent {
            NotificationSettings(title = title, checked = true, onCheckedChanged = { })
        }

        composeTestRule.onNodeWithText(title)
            .assertIsDisplayed()
    }

    @Test
    fun Setting_Checked() {
        composeTestRule.setContent {
            NotificationSettings(
                title = "Enable Notifications",
                checked = true,
                onCheckedChanged = { }
            )
        }
        composeTestRule.onNodeWithTag(TAG_TOGGLE_ITEM)
            .assertIsOn()
    }

}