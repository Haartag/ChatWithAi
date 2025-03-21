package com.llinsoft.finalchat.presentation.login_screen


sealed interface LoginAction {
    data class OnUsernameUpdate(val username: String): LoginAction
    data class OnPasswordUpdate(val password: String): LoginAction
}