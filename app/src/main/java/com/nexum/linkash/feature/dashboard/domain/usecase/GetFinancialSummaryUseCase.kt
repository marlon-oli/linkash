package com.nexum.linkash.feature.dashboard.domain.usecase

import com.nexum.linkash.feature.dashboard.domain.model.FinancialSummary
import com.nexum.linkash.feature.transactions.domain.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFinancialSummaryUseCase @Inject constructor(
    private val repository: TransactionRepository,
) {
    operator fun invoke(): Flow<FinancialSummary> =
        repository.getTransactions().map { transactions ->
            val income   = transactions.filter { !it.isExpense }.sumOf { it.amount }
            val expenses = transactions.filter {  it.isExpense }.sumOf { it.amount }
            FinancialSummary(
                totalBalance  = income - expenses,
                totalIncome   = income,
                totalExpenses = expenses,
                savingsRate   = if (income > 0) ((income - expenses) / income) * 100 else 0.0,
            )
        }
}
