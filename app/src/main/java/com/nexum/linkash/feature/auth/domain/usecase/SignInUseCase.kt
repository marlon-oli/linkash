package com.nexum.linkash.feature.auth.domain.usecase

import com.nexum.linkash.feature.auth.domain.AuthRepository
import com.nexum.linkash.feature.auth.domain.model.AuthResult
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(email: String, password: String): AuthResult =
        repository.signIn(email, password)
}
