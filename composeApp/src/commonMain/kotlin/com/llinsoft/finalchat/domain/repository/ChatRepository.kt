package com.llinsoft.finalchat.domain.repository

import com.llinsoft.finalchat.domain.request_making.ChatRequest
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun requestAnswer(request: ChatRequest)
    fun subscribeForResponse(): Flow<String>
}