package com.llinsoft.finalchat.presentation.login_screen

data class LoginState(
    val username: String = "",
    val password: String = "",
    val error: String? = "",
    val isLoading: Boolean = false,
    val saveCheckbox: Boolean = false
)
