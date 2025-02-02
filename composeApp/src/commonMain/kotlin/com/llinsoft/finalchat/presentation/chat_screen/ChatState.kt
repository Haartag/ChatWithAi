package com.llinsoft.finalchat.presentation.chat_screen

import com.llinsoft.finalchat.domain.ChatMessage

data class ChatState(
    val textFieldMessage: String = "",
    val chatMessages: List<ChatMessage> = emptyList(),
    val isWaitingForAnswer: Boolean = false,
)
