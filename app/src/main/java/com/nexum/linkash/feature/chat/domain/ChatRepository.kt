package com.nexum.linkash.feature.chat.domain

import com.nexum.linkash.feature.chat.domain.model.ChatMessage
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun sendMessage(userMessage: String, financialContext: String): Flow<String>
}
