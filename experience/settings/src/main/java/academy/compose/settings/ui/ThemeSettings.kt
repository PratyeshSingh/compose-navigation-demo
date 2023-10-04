package academy.compose.settings.ui

import academy.compose.settings.R
import academy.compose.settings.model.Theme
import academy.compose.settings.Tags.TAG_SELECT_THEME
import academy.compose.settings.Tags.TAG_THEME
import academy.compose.settings.Tags.TAG_THEME_OPTION
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun ThemeSettingItem(
    modifier: Modifier = Modifier,
    selectedTheme: Theme,
    onThemeSelected: (theme: Theme) -> Unit
) {
    SettingItem(modifier = modifier) {
        var expanded by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .clickable(
                    onClickLabel = stringResource(id = R.string.cd_select_theme)
                ) {
                    expanded = !expanded
                }
                .padding(16.dp)
                .testTag(TAG_SELECT_THEME),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.setting_option_theme)
            )
            Text(
                modifier = Modifier.testTag(TAG_THEME),
                text = stringResource(id = selectedTheme.label)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            offset = DpOffset(16.dp, 0.dp)
        ) {
            Theme.values().forEach { theme ->
                val themeLabel = stringResource(id = theme.label)
                DropdownMenuItem(
                    modifier = Modifier.testTag(TAG_THEME_OPTION + themeLabel),
                    text = {
                        Text(text = themeLabel)
                    },
                    onClick = {
                        onThemeSelected(theme)
                        expanded = false
                    })
            }
        }
    }
}

@Preview
@Composable
fun Preview_ThemeSettingItem() {
    MaterialTheme {
        ThemeSettingItem(
            modifier = Modifier.fillMaxWidth(),
            selectedTheme = Theme.SYSTEM,
            onThemeSelected = { }
        )
    }
}