package com.nexum.linkash.feature.transactions.domain

import com.nexum.linkash.feature.transactions.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getTransactions(): Flow<List<Transaction>>
    suspend fun syncTransactions()
}
