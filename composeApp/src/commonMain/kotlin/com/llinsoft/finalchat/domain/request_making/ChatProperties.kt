package com.llinsoft.finalchat.domain.request_making

data class ChatProperties(
    val apiProvider: ApiProvider,
    val model: OpenAiModel,
    val systemPrompt: String,
    val temperature: OpenAiTemperatureSettings
)
