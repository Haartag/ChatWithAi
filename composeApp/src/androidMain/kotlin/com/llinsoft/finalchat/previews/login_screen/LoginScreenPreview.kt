package com.llinsoft.finalchat.previews.login_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.llinsoft.finalchat.presentation.login_screen.LoginScreen
import com.llinsoft.finalchat.presentation.login_screen.LoginState

@Preview
@Composable
fun LoginScreenPreview(
    modifier: Modifier = Modifier
) {
    val fakeState = LoginState(
        username = "Tester_1",
        password = "StrongPassword1",
        isLoading = true,
        saveCheckbox = false
    )
    LoginScreen(
        state = fakeState,
        onAction = { }
    )
}