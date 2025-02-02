package com.llinsoft.finalchat.previews.chat_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.llinsoft.finalchat.domain.ChatMessage
import com.llinsoft.finalchat.domain.SenderType
import com.llinsoft.finalchat.presentation.chat_screen.ChatScreen
import com.llinsoft.finalchat.presentation.chat_screen.ChatState

@Preview
@Composable
fun ChatScreenPreview(
    modifier: Modifier = Modifier
) {

    val messages = listOf(
        ChatMessage(
            senderType = SenderType.SYSTEM,
            text = "This is system prompt"
        ),
        ChatMessage(
            senderType = SenderType.USER,
            text = "This is question"
        ),
        ChatMessage(
            senderType = SenderType.CHAT,
            text = "This is chat answer"
        ),
    )
    val fakeState = ChatState(
        textFieldMessage = "",
        chatMessages = messages
    )
    ChatScreen(
        state = fakeState,
        onAction = {}
    )
}