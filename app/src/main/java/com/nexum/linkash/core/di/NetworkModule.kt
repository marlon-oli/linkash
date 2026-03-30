package com.nexum.linkash.core.di

import com.apollographql.apollo.ApolloClient
import com.nexum.linkash.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.graphql.graphql
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApolloClient(supabase: SupabaseClient): ApolloClient =
        supabase.graphql.apolloClient
            .newBuilder()
            .serverUrl("${BuildConfig.SUPABASE_URL}/graphql/v1")
            .build()
}
