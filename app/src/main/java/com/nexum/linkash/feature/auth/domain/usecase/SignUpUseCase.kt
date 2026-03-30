package com.nexum.linkash.feature.auth.domain.usecase

import com.nexum.linkash.feature.auth.domain.AuthRepository
import com.nexum.linkash.feature.auth.domain.model.AuthResult
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(email: String, password: String): AuthResult =
        repository.signUp(email, password)
}
