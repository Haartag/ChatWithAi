package com.llinsoft.finalchat.presentation.chat_screen

sealed interface ChatAction {
    data class OnInputMessageChange(val message: String): ChatAction
    data class OnMessageSendClick(val message: String): ChatAction
    data class OnTileLongClick(val index: Int): ChatAction
}