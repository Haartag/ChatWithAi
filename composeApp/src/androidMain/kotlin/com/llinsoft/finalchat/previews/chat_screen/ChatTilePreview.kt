package com.llinsoft.finalchat.previews.chat_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.llinsoft.finalchat.domain.SenderType
import com.llinsoft.finalchat.presentation.chat_screen.components.ChatTile

@Preview
@Composable
fun ChatTilePreviewSystem(modifier: Modifier = Modifier) {
    ChatTile(
        sender = SenderType.SYSTEM,
        message = "This is System message",
    )
}

@Preview
@Composable
fun ChatTilePreviewChat(modifier: Modifier = Modifier) {
    ChatTile(
        sender = SenderType.CHAT,
        message = "This is Chat message",
    )
}

@Preview
@Composable
fun ChatTilePreviewUser(modifier: Modifier = Modifier) {
    ChatTile(
        sender = SenderType.USER,
        message = "This is User message",
    )
}