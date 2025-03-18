package com.llinsoft.finalchat.domain.request_making

import com.llinsoft.finalchat.domain.ChatMessage
import com.llinsoft.finalchat.domain.SenderType
import com.llinsoft.finalchat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SubscribeForResponse(
    private val repository: ChatRepository
) {

    fun subscribe(): Flow<ChatMessage> {
        return repository.subscribeForResponse().map {
            ChatMessage(
                senderType = SenderType.CHAT,
                text = it,
            )
        }
    }
}