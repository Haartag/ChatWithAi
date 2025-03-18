package com.llinsoft.finalchat.domain.request_making

data class ChatRequest(
    val chatProperties: ChatProperties,
    val request: String
)
