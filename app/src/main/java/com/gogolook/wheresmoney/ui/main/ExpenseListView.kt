package com.gogolook.wheresmoney.ui.main

import androidx.compose.runtime.Composable

/**
 * A composable that provides expense list, click on the expense item will callback onExpenseClick().
 * @param viewModel the view model for expense list.
 * @param onExpenseClick the callback when expense item is clicked
 */
@Composable
fun ExpenseListView(viewModel: ExpenseListViewModel, onExpenseClick: (expenseId: Int) -> Unit) {

}