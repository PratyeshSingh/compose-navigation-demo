

package academy.compose.authentication.model

import academy.compose.authentication.R
import androidx.annotation.StringRes

enum class PasswordRequirement(
    @StringRes val label: Int
) {
    CAPITAL_LETTER(R.string.requirement_capital),
    NUMBER(R.string.requirement_digit),
    EIGHT_CHARACTERS(R.string.requirement_characters)
}