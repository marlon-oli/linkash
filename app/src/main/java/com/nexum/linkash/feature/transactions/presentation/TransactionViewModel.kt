package com.nexum.linkash.feature.transactions.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexum.linkash.feature.transactions.domain.model.Transaction
import com.nexum.linkash.feature.transactions.domain.usecase.GetTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    getTransactions: GetTransactionsUseCase,
) : ViewModel() {

    val transactions: StateFlow<List<Transaction>> = getTransactions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
}
