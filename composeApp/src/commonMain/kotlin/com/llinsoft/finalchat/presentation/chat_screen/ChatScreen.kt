package com.llinsoft.finalchat.presentation.chat_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.llinsoft.finalchat.presentation.chat_screen.components.ChatInputField
import com.llinsoft.finalchat.presentation.chat_screen.components.ChatMessageList

@Composable
fun ChatScreenRoot(
    viewModel: ChatViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ChatScreen(
        state = state,
        onAction = { action ->
/*            when (action) {
                is ChatAction.OnMessageSendClick -> { }
                else -> Unit
            }*/
            viewModel.onAction(action)
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    state: ChatState,
    onAction: (ChatAction) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text("")
                },
                navigationIcon = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ChatMessageList(
                messages = state.chatMessages,
                modifier = Modifier.weight(1f)
            )
            ChatInputField(
                message = state.textFieldMessage,
                onSendClick = {},
                onMessageChange = { onAction(ChatAction.OnInputMessageChange(it)) }
            )
        }
    }
}