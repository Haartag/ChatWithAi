package com.llinsoft.finalchat.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class OpenAiTextRequest(
    val model: String,
    val systemPrompt: String,
    val request: String,
    val temperature: Double,
    val topP: Double
): TextRequest