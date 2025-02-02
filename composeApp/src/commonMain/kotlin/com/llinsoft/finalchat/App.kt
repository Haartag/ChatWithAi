package com.llinsoft.finalchat

import androidx.compose.runtime.Composable
import com.llinsoft.finalchat.presentation.chat_screen.ChatScreenRoot
import com.llinsoft.finalchat.presentation.chat_screen.ChatViewModel
import com.llinsoft.finalchat.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    val viewModel = ChatViewModel()
    ChatScreenRoot(viewModel)
}
