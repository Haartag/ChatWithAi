package com.llinsoft.finalchat.domain.request_making

import com.llinsoft.finalchat.domain.repository.ChatRepository

class MakeRequestUseCase(
    private val repository: ChatRepository
) {

    suspend fun execute(
        properties: ChatProperties,
        message: String
    ) {
        val request = ChatRequest(properties, message)
        repository.requestAnswer(request)
    }
}
