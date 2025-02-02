package com.llinsoft.finalchat.domain

data class ChatMessage(
    val senderType: SenderType,
    val text: String? = null,
    val image: String? = null,
    val isError: Boolean = false,
)
