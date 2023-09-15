package com.gogolook.wheresmoney.ui.expense

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
fun AmountCalculator(defaultAmount: Int, onPick: (amount: Int) -> Unit) {

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
    LazyColumn(
        modifier = Modifier
            .background(Color.Transparent)
            .wrapContentHeight(align = Alignment.CenterVertically)
            .clip(shape = RoundedCornerShape(24.dp))
            .background(Color.LightGray)
        ,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp),
    ) {
        items(categories.size) {
            Row(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .clip(shape = RoundedCornerShape(24.dp))
                    .background(Color.White)
                ,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = categories[it].id == defaultCategory?.id,
                    onClick = {
                        onPick(categories[it])
                    }
                )
                Text(
                    text = categories[it].name,
                    color = Color(categories[it].color.toULong())
                )
            }
        }
    }
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