package com.nexum.linkash.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nexum.linkash.core.database.converter.DateConverters
import com.nexum.linkash.feature.transactions.data.local.TransactionDao
import com.nexum.linkash.feature.transactions.data.local.TransactionEntity

@Database(
    entities = [TransactionEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(DateConverters::class)
abstract class LinkashDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
