package com.nexum.linkash.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexum.linkash.feature.auth.domain.model.AuthResult
import com.nexum.linkash.feature.auth.domain.usecase.SignInUseCase
import com.nexum.linkash.feature.auth.domain.usecase.SignOutUseCase
import com.nexum.linkash.feature.auth.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val isLoading: Boolean    = false,
    val isSuccess: Boolean    = false,
    val errorMessage: String? = null,
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signIn:  SignInUseCase,
    private val signUp:  SignUpUseCase,
    private val signOut: SignOutUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun signIn(email: String, password: String) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        _uiState.update {
            when (val result = signIn.invoke(email, password)) {
                is AuthResult.Success    -> it.copy(isLoading = false, isSuccess = true)
                is AuthResult.Error      -> it.copy(isLoading = false, errorMessage = result.message)
            }
        }
    }

    fun signUp(email: String, password: String) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        _uiState.update {
            when (val result = signUp.invoke(email, password)) {
                is AuthResult.Success    -> it.copy(isLoading = false, isSuccess = true)
                is AuthResult.Error      -> it.copy(isLoading = false, errorMessage = result.message)
            }
        }
    }

    fun signOut() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        signOut.invoke()
        _uiState.update { AuthUiState() }
    }

    fun clearError() = _uiState.update { it.copy(errorMessage = null) }
}
