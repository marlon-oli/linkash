package com.nexum.linkash.core.di

import android.content.Context
import androidx.room.Room
import com.nexum.linkash.core.database.LinkashDatabase
import com.nexum.linkash.feature.transactions.data.local.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LinkashDatabase =
        Room.databaseBuilder(context, LinkashDatabase::class.java, "linkash.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideTransactionDao(db: LinkashDatabase): TransactionDao = db.transactionDao()
}
