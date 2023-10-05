
package com.example.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.model.AuthenticationEvent
import com.example.auth.model.AuthenticationMode
import com.example.auth.model.AuthenticationState
import com.example.auth.model.PasswordRequirement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthenticationViewModel: ViewModel() {

    val uiState = MutableStateFlow(AuthenticationState())

    fun handleEvent(authenticationEvent: AuthenticationEvent) {
        when (authenticationEvent) {
            AuthenticationEvent.ToggleAuthenticationMode -> toggleAuthenticationMode()
            is AuthenticationEvent.EmailChanged -> {
                updateEmail(authenticationEvent.emailAddress)
            }
            is AuthenticationEvent.PasswordChanged -> {
                updatePassword(authenticationEvent.password)
            }
            AuthenticationEvent.Authenticate -> authenticate()
            AuthenticationEvent.ErrorDismissed -> dismissError()
        }
    }

    private fun authenticate() {
        uiState.value = uiState.value.copy(isLoading = true)
        // trigger network request

        viewModelScope.launch(Dispatchers.IO) {
            val authStatue = uiState.value.email == "pratyesh.chandra@gmail.com"
                    && uiState.value.password == "abcd123"
            delay(2000L)
            withContext(Dispatchers.Main) {
                if (authStatue) {
                    // on Success verification
                    uiState.value = uiState.value.copy(
                        isLoading = false
                    )
                } else {
                    // on Failure verification
                    uiState.value = uiState.value.copy(
                        isLoading = false,
                        error = "Something went wrong!"
                    )
                }
            }
        }
    }

    private fun dismissError() {
        uiState.value = uiState.value.copy(error = null)
    }

    private fun updateEmail(email: String) {
        uiState.value = uiState.value.copy(email = email)
    }

    private fun updatePassword(password: String) {
        val satisfiedRequirements = mutableListOf<PasswordRequirement>()
        if (password.length > 7) {
            satisfiedRequirements.add(PasswordRequirement.EIGHT_CHARACTERS)
        }
        if (password.any { it.isDigit() }) {
            satisfiedRequirements.add(PasswordRequirement.NUMBER)
        }
        if (password.any { it.isUpperCase() }) {
            satisfiedRequirements.add(PasswordRequirement.CAPITAL_LETTER)
        }

        uiState.value = uiState.value.copy(
            password = password,
            satisfiedPasswordRequirements = satisfiedRequirements.toList()
        )
    }

    private fun toggleAuthenticationMode() {
        val currentMode = uiState.value.authenticationMode
        val newMode = if (currentMode == AuthenticationMode.SIGN_IN) {
            AuthenticationMode.SIGN_UP
        } else AuthenticationMode.SIGN_IN

        uiState.value = uiState.value.copy(authenticationMode = newMode)
    }

}