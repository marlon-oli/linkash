package com.nexum.linkash.feature.transactions.domain.model

import java.time.Instant

data class Transaction(
    val id: String,
    val description: String,
    val amount: Double,
    val category: String,
    val date: Instant,
    val isExpense: Boolean,
)
