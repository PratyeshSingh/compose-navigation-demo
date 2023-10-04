

package academy.compose.settings

import academy.compose.settings.model.MarketingOption
import academy.compose.settings.ui.MarketingSettingItem
import academy.compose.settings.Tags.TAG_MARKETING_OPTION
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class MarketingSettingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Marketing_Option_Selected() {
        val option = MarketingOption.NOT_ALLOWED

        composeTestRule.setContent {
            MarketingSettingItem(selectedOption = option, onOptionSelected = { })
        }

        composeTestRule.onNodeWithTag(TAG_MARKETING_OPTION + option.id)
            .assertIsSelected()
    }
}