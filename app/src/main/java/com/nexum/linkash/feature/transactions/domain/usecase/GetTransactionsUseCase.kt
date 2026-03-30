package com.nexum.linkash.feature.transactions.domain.usecase

import com.nexum.linkash.feature.transactions.domain.TransactionRepository
import com.nexum.linkash.feature.transactions.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val repository: TransactionRepository,
) {
    operator fun invoke(): Flow<List<Transaction>> = repository.getTransactions()
}
