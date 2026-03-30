package com.nexum.linkash.feature.auth.domain

import com.nexum.linkash.feature.auth.domain.model.AuthResult

interface AuthRepository {
    suspend fun signIn(email: String, password: String): AuthResult
    suspend fun signUp(email: String, password: String): AuthResult
    suspend fun signOut(): AuthResult
    suspend fun isLoggedIn(): Boolean
}
