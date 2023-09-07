

package academy.compose.settings

import academy.compose.settings.ui.HintSettingsItem
import academy.compose.settings.Tags.TAG_CHECK_ITEM
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class HintSettingsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Title_Displayed() {
        val title = "Show Hints"

        composeTestRule.setContent {
            HintSettingsItem(title = title, checked = true, onCheckedChanged = { })
        }

        composeTestRule.onNodeWithText(title)
            .assertIsDisplayed()
    }

    @Test
    fun Setting_Checked() {
        composeTestRule.setContent {
            HintSettingsItem(
                title = "Show Hints",
                checked = true,
                onCheckedChanged = { }
            )
        }
        composeTestRule.onNodeWithTag(TAG_CHECK_ITEM)
            .assertIsOn()
    }

}