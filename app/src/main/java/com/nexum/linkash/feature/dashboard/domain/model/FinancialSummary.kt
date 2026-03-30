package com.nexum.linkash.feature.dashboard.domain.model

data class FinancialSummary(
    val totalBalance: Double,
    val totalIncome: Double,
    val totalExpenses: Double,
    val savingsRate: Double,
)
