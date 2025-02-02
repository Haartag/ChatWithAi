package com.llinsoft.finalchat.domain

enum class SenderType(val senderName: String) {
    SYSTEM(senderName = "System"),
    CHAT(senderName = "Chat"),
    USER(senderName = "User")
}