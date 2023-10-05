

package com.example.auth.model

import com.example.auth.R
import androidx.annotation.StringRes

enum class PasswordRequirement(
    @StringRes val label: Int
) {
    CAPITAL_LETTER(R.string.requirement_capital),
    NUMBER(R.string.requirement_digit),
    EIGHT_CHARACTERS(R.string.requirement_characters)
}