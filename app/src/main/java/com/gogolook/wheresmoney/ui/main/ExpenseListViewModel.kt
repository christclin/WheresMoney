package com.gogolook.wheresmoney.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gogolook.wheresmoney.data.Expense
import com.gogolook.wheresmoney.data.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseListViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    private val _expenses = MutableStateFlow(emptyList<Expense>())
    val expenses = _expenses

    fun queryExpense() {
        viewModelScope.launch(Dispatchers.IO) {
            _expenses.value = expenseRepository.getAll()
        }
    }
}