package com.llinsoft.finalchat.data.repository

import com.llinsoft.finalchat.data.mappers.makeStringFromRequest
import com.llinsoft.finalchat.data.mappers.toTextRequest
import com.llinsoft.finalchat.data.network.RealtimeMessagingClient
import com.llinsoft.finalchat.domain.repository.ChatRepository
import com.llinsoft.finalchat.domain.request_making.ChatRequest
import kotlinx.coroutines.flow.Flow

class DefaultChatRepository(
    private val client: RealtimeMessagingClient
): ChatRepository {

    override suspend fun requestAnswer(request: ChatRequest) {
        val textRequest = request.toTextRequest()
        val stringRequest = textRequest.makeStringFromRequest()
        client.sendTextRequest(stringRequest)
    }

    override fun subscribeForResponse(): Flow<String> {
        return client.getChatResponseStream()
    }
}