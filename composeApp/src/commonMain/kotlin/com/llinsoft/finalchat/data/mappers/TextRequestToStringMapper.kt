package com.llinsoft.finalchat.data.mappers

import com.llinsoft.finalchat.data.dto.OpenAiTextRequest
import com.llinsoft.finalchat.data.dto.TextRequest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun TextRequest.makeStringFromRequest(): String {
    return when (this) {
        is OpenAiTextRequest -> {
            "chat_request#${Json.encodeToString<OpenAiTextRequest>(this)}"
        }
    }
}