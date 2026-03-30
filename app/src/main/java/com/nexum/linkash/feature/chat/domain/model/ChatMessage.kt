package com.nexum.linkash.feature.chat.domain.model

import java.time.Instant

data class ChatMessage(
    val id: String,
    val content: String,
    val isFromUser: Boolean,
    val timestamp: Instant = Instant.now(),
)
