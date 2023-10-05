

package com.example.auth.model

data class AuthenticationState(
    val authenticationMode: AuthenticationMode = AuthenticationMode.SIGN_IN,
    val email: String? = null,
    val password: String? = null,
    val satisfiedPasswordRequirements: List<PasswordRequirement> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    fun isFormValid(): Boolean {
        return !email.isNullOrEmpty() && !password.isNullOrEmpty() &&
            (authenticationMode == AuthenticationMode.SIGN_IN ||
                satisfiedPasswordRequirements.containsAll(PasswordRequirement.values().toList()))
    }
}