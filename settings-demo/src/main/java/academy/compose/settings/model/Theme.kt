

package academy.compose.settings.model

import academy.compose.settings.R
import androidx.annotation.StringRes

enum class Theme(@StringRes val label: Int) {
    LIGHT(R.string.theme_light),
    DARK(R.string.theme_dark),
    SYSTEM(R.string.theme_system)
}