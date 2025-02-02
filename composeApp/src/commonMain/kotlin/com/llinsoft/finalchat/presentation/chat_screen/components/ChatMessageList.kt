package com.llinsoft.finalchat.presentation.chat_screen.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.llinsoft.finalchat.domain.ChatMessage

@Composable
fun ChatMessageList(
    modifier: Modifier = Modifier,
    messages: List<ChatMessage> = emptyList()
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(messages) { message ->
            ChatTile(
                sender = message.senderType,
                message = message.text!!
            )
        }
    }
}