package com.llinsoft.finalchat.presentation.chat_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llinsoft.finalchat.domain.ChatMessage
import com.llinsoft.finalchat.domain.SenderType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel(

): ViewModel() {

    val prompt = "Give me short answer on my question, 1-2 sentences."

    private val _state = MutableStateFlow(ChatState())
    val state = _state
        .onStart {
            setSystemMessage()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: ChatAction) {
        when (action) {
            is ChatAction.OnInputMessageChange -> {
                _state.update {
                    it.copy(
                        textFieldMessage = action.message
                    )
                }
            }
            is ChatAction.OnMessageSendClick -> {

            }
            is ChatAction.OnTileLongClick -> {

            }
        }
    }

    private fun setSystemMessage() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    chatMessages = listOf(
                        ChatMessage(SenderType.SYSTEM, prompt, null, false)
                    )
                )
            }
        }
    }
}