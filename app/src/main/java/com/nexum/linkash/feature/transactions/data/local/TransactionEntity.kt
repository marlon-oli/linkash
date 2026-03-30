package com.nexum.linkash.feature.transactions.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey val id: String,
    val description: String,
    val amount: Double,
    val category: String,
    val date: Instant,
    val isExpense: Boolean,
)
