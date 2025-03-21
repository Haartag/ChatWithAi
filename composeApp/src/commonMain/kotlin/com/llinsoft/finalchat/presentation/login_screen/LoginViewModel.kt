package com.llinsoft.finalchat.presentation.login_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class LoginViewModel(

): ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state
        .onStart {

        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnUsernameUpdate -> {
                _state.update {
                    it.copy(
                        username = action.username
                    )
                }
            }

            is LoginAction.OnPasswordUpdate -> {
                _state.update {
                    it.copy(
                        password = action.password
                    )
                }
            }
        }
    }
}