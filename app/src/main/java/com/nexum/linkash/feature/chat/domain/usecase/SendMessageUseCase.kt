package com.nexum.linkash.feature.chat.domain.usecase

import com.nexum.linkash.feature.chat.domain.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: ChatRepository,
) {
    suspend operator fun invoke(message: String, context: String): Flow<String> =
        repository.sendMessage(message, context)
}
