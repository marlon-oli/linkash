package com.nexum.linkash.feature.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexum.linkash.feature.dashboard.domain.model.FinancialSummary
import com.nexum.linkash.feature.dashboard.domain.usecase.GetFinancialSummaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    getFinancialSummary: GetFinancialSummaryUseCase,
) : ViewModel() {

    val summary: StateFlow<FinancialSummary?> = getFinancialSummary()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)
}
