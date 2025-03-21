package com.llinsoft.finalchat.presentation.login_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.llinsoft.finalchat.presentation.login_screen.components.LoginInputTile
import com.llinsoft.finalchat.presentation.login_screen.components.TopIconField

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoginScreen(
        state = state,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopIconField()

        LoginInputTile(
            username = state.username,
            password = state.password,
            isLoading = state.isLoading,
            onUsernameChange = { onAction(LoginAction.OnUsernameUpdate(it)) },
            onPasswordChange = { onAction(LoginAction.OnPasswordUpdate(it)) }
        )

        state.error?.let { error ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = error,
            )
        }
    }
}