package com.llinsoft.finalchat.previews.chat_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.llinsoft.finalchat.presentation.chat_screen.components.ChatInputField

@Preview
@Composable
fun ChatInputFieldPreview(modifier: Modifier = Modifier) {
    ChatInputField(
        message = "This is some user-inputted message. This is some user-inputted message.",
        onSendClick = {},
        onMessageChange = {}
    )
}