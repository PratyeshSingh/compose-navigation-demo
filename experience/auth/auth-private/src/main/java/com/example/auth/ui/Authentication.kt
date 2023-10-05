

package com.example.auth.ui

import com.example.auth.AuthenticationViewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.auth.AuthAction

@Composable
fun Authentication(
    onDone: (AuthAction) -> Unit
) {
    val viewModel: AuthenticationViewModel = viewModel()
    MaterialTheme {
        AuthenticationContent(
            state = viewModel.uiState.collectAsState().value,
            handleEvent = viewModel::handleEvent,
            onDone = onDone
        )
    }
}