package com.nexum.linkash.feature.chat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexum.linkash.feature.chat.domain.model.ChatMessage
import com.nexum.linkash.feature.chat.domain.usecase.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

data class ChatUiState(
    val messages: List<ChatMessage> = emptyList(),
    val isLoading: Boolean          = false,
    val errorMessage: String?       = null,
)

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessage: SendMessageUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    fun send(content: String, financialContext: String = "") = viewModelScope.launch {
        val userMsg = ChatMessage(UUID.randomUUID().toString(), content, isFromUser = true)
        _uiState.update { it.copy(messages = it.messages + userMsg, isLoading = true) }

        runCatching {
            val sb = StringBuilder()
            sendMessage(content, financialContext).collect { chunk ->
                sb.append(chunk)
                val aiMsg = ChatMessage(UUID.randomUUID().toString(), sb.toString(), isFromUser = false)
                _uiState.update { state ->
                    val msgs = state.messages.dropLast(if (state.messages.lastOrNull()?.isFromUser == false) 1 else 0) + aiMsg
                    state.copy(messages = msgs)
                }
            }
        }.onFailure { e ->
            _uiState.update { it.copy(errorMessage = e.message) }
        }

        _uiState.update { it.copy(isLoading = false) }
    }

    fun clearError() = _uiState.update { it.copy(errorMessage = null) }
}
