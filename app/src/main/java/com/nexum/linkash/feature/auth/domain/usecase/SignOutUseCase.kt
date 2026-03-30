package com.nexum.linkash.feature.auth.domain.usecase

import com.nexum.linkash.feature.auth.domain.AuthRepository
import com.nexum.linkash.feature.auth.domain.model.AuthResult
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(): AuthResult = repository.signOut()
}
