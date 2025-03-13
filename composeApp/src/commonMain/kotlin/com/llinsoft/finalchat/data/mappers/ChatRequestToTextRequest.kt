package com.llinsoft.finalchat.data.mappers

import com.llinsoft.finalchat.data.dto.OpenAiTextRequest
import com.llinsoft.finalchat.data.dto.TextRequest
import com.llinsoft.finalchat.domain.request_making.ApiProvider
import com.llinsoft.finalchat.domain.request_making.ChatRequest

fun ChatRequest.toTextRequest(): TextRequest {
    val apiProvider = this.chatProperties.apiProvider

    return when (apiProvider) {
        ApiProvider.OPENAI -> {
            OpenAiTextRequest(
                model = this.chatProperties.model.id,
                systemPrompt = this.chatProperties.systemPrompt,
                request = this.request,
                temperature = this.chatProperties.temperature.temperature,
                topP = this.chatProperties.temperature.topP
            )
        }
    }
}