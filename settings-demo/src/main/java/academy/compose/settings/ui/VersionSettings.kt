package academy.compose.settings.ui

import academy.compose.settings.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppVersionSettingItem(
    modifier: Modifier = Modifier,
    appVersion: String
) {
    SettingItem(modifier = modifier) {
        Row(
            modifier = Modifier.padding(16.dp)
                .semantics(mergeDescendants = true) { },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.setting_app_version)
            )
            Text(text = appVersion)
        }
    }
}

@Preview
@Composable
fun Preview_AppVersionSettingItem() {
    MaterialTheme {
        AppVersionSettingItem(
            modifier = Modifier.fillMaxWidth(),
            appVersion = "2.1.0"
        )
    }
}