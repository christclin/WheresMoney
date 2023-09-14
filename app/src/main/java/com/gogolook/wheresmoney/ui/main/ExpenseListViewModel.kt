package com.gogolook.wheresmoney.ui.main

import androidx.lifecycle.ViewModel
import com.gogolook.wheresmoney.data.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExpenseListViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

}