package com.nexum.linkash.feature.auth.data

import com.nexum.linkash.core.datastore.UserPreferencesDataStore
import com.nexum.linkash.feature.auth.domain.AuthRepository
import com.nexum.linkash.feature.auth.domain.model.AuthResult
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val supabase: SupabaseClient,
    private val prefs: UserPreferencesDataStore,
) : AuthRepository {

    override suspend fun signIn(email: String, password: String): AuthResult =
        runCatching {
            supabase.auth.signInWith(Email) {
                this.email    = email
                this.password = password
            }
            val user = supabase.auth.currentUserOrNull()
            prefs.saveSession(user?.id ?: "", email)
            AuthResult.Success
        }.getOrElse { AuthResult.Error(it.message ?: "Erro ao fazer login") }

    override suspend fun signUp(email: String, password: String): AuthResult =
        runCatching {
            supabase.auth.signUpWith(Email) {
                this.email    = email
                this.password = password
            }
            AuthResult.Success
        }.getOrElse { AuthResult.Error(it.message ?: "Erro ao criar conta") }

    override suspend fun signOut(): AuthResult =
        runCatching {
            supabase.auth.signOut()
            prefs.clearSession()
            AuthResult.Success
        }.getOrElse { AuthResult.Error(it.message ?: "Erro ao sair") }

    override suspend fun isLoggedIn(): Boolean = prefs.isLoggedIn.first()
}
