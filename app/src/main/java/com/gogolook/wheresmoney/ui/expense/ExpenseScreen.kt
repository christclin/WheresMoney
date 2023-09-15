package com.gogolook.wheresmoney.ui.expense

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.setValue
import com.gogolook.wheresmoney.data.Category
import com.gogolook.wheresmoney.data.Expense
import java.util.Date

/**
 * Expense screen
 * A composable that provides a screen for user to create or edit an expense
 * @param viewModel: ExpenseViewModel
 * @param expenseId: the id of the expense to be edited, or 0 if creating a new expense
 * @param onCompleted: callback when user finish creating or editing an expense
 */
@Composable
fun ExpenseScreen(
    viewModel: ExpenseViewModel,
    expenseId: Int,
    onCompleted: () -> Unit = {},
) {
    LaunchedEffect(key1 = expenseId) {
        viewModel.fetchCategories()
        viewModel.fetchExpense(expenseId)
    }
    ExpenseView(
        expense = viewModel.expense.value,
        categories = viewModel.categories.value,
        onSave = { expense ->
            viewModel.saveExpense(expense)
            onCompleted()
        }
    )
}

/**
 * Expense view
 * A composable that provides a view with for user to create or edit an expense, you can choose a style
 * 1. A view with toolbar, or
 * 2. A non-full screen view just like a dialog
 * There are four editable fields:
 * 1. Name: the name of the expense
 * 2. Category: the category of the expense (clicking on this field will show a category picker)
 * 3. Date: the date of the expense (clicking on this field will show a date picker)
 * 4. Amount: the amount of the expense
 * @param expense: the expense to be edited, or null if creating a new expense
 * @param categories: the list of categories
 * @param onSave: callback when user finish creating or editing an expense
 */
@Composable
fun ExpenseView(expense: Expense?, categories: List<Category>, onSave: (expense: Expense) -> Unit) {
    val shouldShowDatePicker = remember { mutableStateOf(false) }
    val shouldShowCategoryPicker = remember { mutableStateOf(false) }
    val shouldShowAmountCalculator = remember { mutableStateOf(false) }
    val date = remember { mutableStateOf(Date()) }
    val categoryId = remember { mutableStateOf(0) }
    val amount = remember { mutableStateOf(0) }

    TODO("Implement ExpenseView")

    AnimatedVisibility(visible = shouldShowCategoryPicker.value) {
        MyDatePicker(expense?.date) {
            date.value = it
            shouldShowDatePicker.value = false
        }
    }
    AnimatedVisibility(visible = shouldShowCategoryPicker.value) {
        CategoryPicker(categories, expense?.category) {
            categoryId.value = it.id
            shouldShowCategoryPicker.value = false
        }
    }
    AnimatedVisibility(visible = shouldShowAmountCalculator.value) {
        AmountCalculator(amount.value) {
            amount.value = it
            shouldShowAmountCalculator.value = false
        }
    }
}

/**
 * Amount calculator
 * A composable that provides
 * 1. A text field for user to input amount
 * 2. A number pad supports +-x/ for user to input amount
 * 3. A button to confirm the amount
 * @param defaultAmount: the default amount of the amount calculator
 * @param onPick: callback when user pick an amount
 */

@Composable
@Preview

fun AmountCalculator(defaultAmount: Int=1, onPick: (amount: Int) -> Unit) {

    var number = defaultAmount

    Column {
        Row {
            TextField(value = number.toString(), onValueChange = {})
                Button(onClick = { number += 1 }) {
                    Text(text = "＋")
                }
                Button(onClick = { number -= 1 }) {
                    Text(text = "-")
                }

            fun refreshTextfield(number: Int) {
                //refresh textField value

            }
        }
            Button(onClick = { onPick(number) }) {
                Text(text = "confirm")


            }
        }
    }




/**
 * Category picker
 * A composable that provides
 * 1. A single choice list of categories
 * 2. A button to confirm the category
 * @param categories: the list of categories
 * @param defaultCategory: the default category of the category picker
 * @param onPick: callback when user pick a category
 */
@Composable
fun CategoryPicker(categories: List<Category>, defaultCategory: Category?, onPick: (category: Category) -> Unit) {

}

/**
 * Date picker
 * A composable that provides
 * 1. A date picker with year, month, day (do not use 3rd-party library)
 * 2. A button to confirm the date
 * @param defaultDate: the default date of the date picker
 * @param onPick: callback when user pick a date
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePicker(defaultDate: Date?, onPick: (date: Date) -> Unit) {
    var showDatePicker by remember {
        mutableStateOf(true)
    }

    val datePickerState = rememberDatePickerState(initialDisplayedMonthMillis = defaultDate?.time)

    val selectedDate = datePickerState.selectedDateMillis?.let {
        Date(it)
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Button(onClick = {
                    selectedDate?.let {
                        onPick(it)
                    }
                    showDatePicker = false
                }
                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showDatePicker = false
                }) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }
}