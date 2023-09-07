package academy.compose.settings.ui

import academy.compose.settings.R
import academy.compose.settings.Tags.TAG_TOGGLE_ITEM
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NotificationSettings(
    modifier: Modifier = Modifier,
    title: String,
    checked: Boolean,
    onCheckedChanged: (checked: Boolean) -> Unit
) {
    val notificationsEnabledState = if (checked) {
        stringResource(id = R.string.cd_notifications_enabled)
    } else stringResource(id = R.string.cd_notifications_disabled)
    SettingItem(modifier = modifier) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = checked,
                    onValueChange = onCheckedChanged,
                    role = Role.Switch
                )
                .semantics {
                    stateDescription = notificationsEnabledState
                }
                .padding(horizontal = 16.dp)
                .testTag(TAG_TOGGLE_ITEM),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = title
            )
            Switch(checked = checked, onCheckedChange = null)
        }
    }
}

@Preview
@Composable
fun Preview_NotificationSettings() {
    MaterialTheme {
        NotificationSettings(
            modifier = Modifier.fillMaxWidth(),
            title = "Enable Notifications",
            checked = true,
            onCheckedChanged = { }
        )
    }
}