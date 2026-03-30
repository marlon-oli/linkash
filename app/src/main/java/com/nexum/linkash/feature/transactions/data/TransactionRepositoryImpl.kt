package com.nexum.linkash.feature.transactions.data

import com.nexum.linkash.feature.transactions.data.local.TransactionDao
import com.nexum.linkash.feature.transactions.data.local.TransactionEntity
import com.nexum.linkash.feature.transactions.domain.TransactionRepository
import com.nexum.linkash.feature.transactions.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val dao: TransactionDao,
) : TransactionRepository {

    override fun getTransactions(): Flow<List<Transaction>> =
        dao.getAllTransactions().map { entities -> entities.map { it.toDomain() } }

    override suspend fun syncTransactions() {
        // TODO: buscar via Apollo GraphQL e salvar no Room
    }

    private fun TransactionEntity.toDomain() = Transaction(
        id          = id,
        description = description,
        amount      = amount,
        category    = category,
        date        = date,
        isExpense   = isExpense,
    )
}
