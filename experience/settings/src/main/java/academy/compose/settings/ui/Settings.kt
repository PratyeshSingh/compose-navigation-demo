package academy.compose.settings.ui

import academy.compose.settings.R
import academy.compose.settings.SettingsViewModel
import academy.compose.settings.model.MarketingOption
import academy.compose.settings.model.SettingsState
import academy.compose.settings.model.Theme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Settings() {
    val viewModel: SettingsViewModel = viewModel()

    MaterialTheme {
        val state = viewModel.uiState.collectAsState().value
        SettingsList(
            modifier = Modifier.fillMaxSize(),
            state = state,
            toggleNotificationSetting = viewModel::toggleNotificationSetting,
            toggleHintsSetting = viewModel::toggleHintSetting,
            setMarketingOption = viewModel::setMarketingSetting,
            setSelectedTheme = viewModel::setTheme
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsList(
    modifier: Modifier = Modifier,
    state: SettingsState,
    toggleNotificationSetting: () -> Unit,
    toggleHintsSetting: () -> Unit,
    setMarketingOption: (option: MarketingOption) -> Unit,
    setSelectedTheme: (theme: Theme) -> Unit
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = {
                Text(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 18.sp,
                    text = stringResource(id = R.string.title_settings)
                )
            },
            navigationIcon = {
                Icon(
                    tint = MaterialTheme.colorScheme.onSurface,
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.cd_exit_settings)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.onPrimary),
        )

        NotificationSettings(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.notification_settings),
            checked = state.notificationsEnabled,
            onCheckedChanged = { toggleNotificationSetting() }
        )
        Divider()
        HintSettingsItem(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.hint_settings),
            checked = state.hintsEnabled,
            onCheckedChanged = { toggleHintsSetting() }
        )
        Divider()
        ManageSubscriptionSettingItem(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.subscription_settings),
            onSettingClicked = {
                // Handle Setting Click
            }
        )
        SectionSpacer(modifier = Modifier.fillMaxWidth())
        MarketingSettingItem(
            modifier = Modifier.fillMaxWidth(),
            selectedOption = state.marketingOption,
            onOptionSelected = setMarketingOption
        )
        Divider()
        ThemeSettingItem(
            modifier = Modifier.fillMaxWidth(),
            selectedTheme = state.selectedTheme,
            onThemeSelected = setSelectedTheme
        )
        SectionSpacer(modifier = Modifier.fillMaxWidth())
        AppVersionSettingItem(appVersion = stringResource(id = R.string.app_version))
    }
}

@Preview
@Composable
fun Preview_SettingsList() {
    MaterialTheme {
        SettingsList(
            modifier = Modifier.fillMaxSize(),
            state = SettingsState(
                notificationsEnabled = true,
                marketingOption = MarketingOption.NOT_ALLOWED
            ),
            toggleNotificationSetting = { },
            toggleHintsSetting = { },
            setMarketingOption = { },
            setSelectedTheme = { }
        )
    }
}