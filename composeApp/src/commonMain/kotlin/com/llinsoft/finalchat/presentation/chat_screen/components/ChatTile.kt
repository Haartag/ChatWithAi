package com.llinsoft.finalchat.presentation.chat_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.llinsoft.finalchat.domain.SenderType

@Composable
fun ChatTile(
    sender: SenderType,
    message: String,
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (sender == SenderType.USER) Alignment.TopEnd else Alignment.TopStart
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 40f,
                        topEnd = 40f,
                        bottomStart = if (sender == SenderType.USER) 40f else 0f,
                        bottomEnd = if (sender == SenderType.USER) 0f else 40f,
                    )
                )
                .background(
                    if (sender == SenderType.USER) {
                        MaterialTheme.colorScheme.tertiaryContainer
                    } else MaterialTheme.colorScheme.primaryContainer
                )
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(4.dp)
            ) {
                Text(
                    modifier = Modifier
                        .align(if (sender == SenderType.USER) Alignment.End else Alignment.Start),
                    text = "${
                        sender.name.lowercase().replaceFirstChar { it.titlecase() }
                    }:",
                    style = MaterialTheme.typography.labelSmall,
                )
                when (sender) {
                    SenderType.SYSTEM -> {
                        TextChatTileContent(
                            text = message
                        )
                    }
                    SenderType.CHAT -> {
                        TextChatTileContent(
                            text = message
                        )
                    }
                    SenderType.USER -> {
                        TextChatTileContent(
                            text = message
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TextChatTileContent(
    text: String,
    inError: Boolean = false,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = text,
        textStyle = TextStyle.Default.copy(
            color = if (inError) MaterialTheme.colorScheme.error else Color.Unspecified
        ),
        onValueChange = {},
        readOnly = true
    )
}

@Composable
fun ImageChatTileContent(
    modifier: Modifier = Modifier
) {

}

