package com.nexum.linkash.feature.transactions.di

import com.nexum.linkash.feature.transactions.data.TransactionRepositoryImpl
import com.nexum.linkash.feature.transactions.domain.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TransactionModule {

    @Binds
    @Singleton
    abstract fun bindTransactionRepository(impl: TransactionRepositoryImpl): TransactionRepository
}
