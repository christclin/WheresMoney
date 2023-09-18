package com.gogolook.wheresmoney.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.gogolook.wheresmoney.data.Expense
import com.gogolook.wheresmoney.data.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExpenseListViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {
    val expenses: MutableState<List<Expense>> = mutableStateOf(emptyList())

    suspend fun fetchExpenses() {
        expenses.value = withContext(Dispatchers.IO) {
            expenseRepository.getAll()
        }
    }
}