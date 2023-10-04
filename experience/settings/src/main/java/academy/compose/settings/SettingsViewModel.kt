

package academy.compose.settings

import academy.compose.settings.model.MarketingOption
import academy.compose.settings.model.SettingsState
import academy.compose.settings.model.Theme
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsViewModel: ViewModel() {

    val uiState = MutableStateFlow(SettingsState())

    fun toggleNotificationSetting() {
        uiState.value = uiState.value.copy(
            notificationsEnabled = !uiState.value.notificationsEnabled
        )
    }

    fun toggleHintSetting() {
        uiState.value = uiState.value.copy(
            hintsEnabled = !uiState.value.hintsEnabled
        )
    }

    fun setMarketingSetting(option: MarketingOption) {
        uiState.value = uiState.value.copy(
            marketingOption = option
        )
    }

    fun setTheme(theme: Theme) {
        uiState.value = uiState.value.copy(
            selectedTheme = theme
        )
    }
}