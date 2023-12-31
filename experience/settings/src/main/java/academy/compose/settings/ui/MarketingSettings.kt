package academy.compose.settings.ui

import academy.compose.settings.R
import academy.compose.settings.model.MarketingOption
import academy.compose.settings.Tags.TAG_MARKETING_OPTION
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MarketingSettingItem(
    modifier: Modifier = Modifier,
    selectedOption: MarketingOption,
    onOptionSelected: (option: MarketingOption) -> Unit
) {
    SettingItem(modifier = modifier) {
        val options = stringArrayResource(id = R.array.setting_options_marketing_choice)
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = R.string.setting_option_marketing))
            Spacer(modifier = Modifier.height(8.dp))
            options.forEachIndexed { index, option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedOption.id == index,
                            onClick ={
                                val marketingOption = if (
                                    index == MarketingOption.ALLOWED.id
                                ) {
                                    MarketingOption.ALLOWED
                                } else MarketingOption.NOT_ALLOWED
                                onOptionSelected(marketingOption)
                            },
                            role = Role.RadioButton
                        )
                        .padding(10.dp)
                        .testTag(TAG_MARKETING_OPTION + index),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOption.id == index,
                        onClick = null
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = option)
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview_MarketingSettingItem() {
    MaterialTheme {
        MarketingSettingItem(
            modifier = Modifier.fillMaxWidth(),
            selectedOption = MarketingOption.ALLOWED,
            onOptionSelected = { }
        )
    }
}