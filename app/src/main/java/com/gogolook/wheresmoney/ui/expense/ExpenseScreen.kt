package com.gogolook.wheresmoney.ui.expense

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gogolook.wheresmoney.data.Category
import com.gogolook.wheresmoney.data.Expense
import com.gogolook.wheresmoney.ui.components.PrimaryStandardButton
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

    expense?.let {
        date.value = it.date
        categoryId.value = it.categoryId
        amount.value = it.amount
    }

    ExpenseItemDialog(
        expense = expense,
        name = expense?.name ?: "",
        date = date.value,
        categoryName = categories.find { it.id == categoryId.value }?.name ?: "",
        categoryId = categoryId.value,
        amount = amount.value,
        onShowCategoryPicker = { shouldShowCategoryPicker.value = true },
        onShowDatePicker = { shouldShowDatePicker.value = true },
        onShowAmountCalculator = { shouldShowAmountCalculator.value = true },
        onSave = onSave,
    )

    AnimatedVisibility(visible = shouldShowDatePicker.value) {
        DatePicker(expense?.date) {
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

@Composable
fun ExpenseItemDialog(
    expense: Expense?,
    name: String,
    date: Date,
    categoryName: String,
    categoryId: Int,
    amount: Int,
    onShowCategoryPicker: () -> Unit,
    onShowDatePicker: () -> Unit,
    onShowAmountCalculator: () -> Unit,
    onSave: (expense: Expense) -> Unit,
) {
    var inputText by remember { mutableStateOf(expense?.name ?: "") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(30.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp))
                .background(Color.White),
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(32.dp))
                        .background(Color.Magenta)
                        .padding(vertical = 20.dp, horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val focusManager = LocalFocusManager.current
                    BasicTextField(
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            }),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        modifier = Modifier,
                        value = inputText,
                        maxLines = 3,
                        cursorBrush = SolidColor(Color.Green),
                        onValueChange = {
                            inputText = it
                        },
                        decorationBox = {
                            if (inputText.isBlank()) {
                                Text(
                                    text = name,
                                    color = Color.LightGray,
                                )
                            }
                            it()
                        },
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(32.dp))
                        .background(Color.Yellow)
                        .clickable {
                            onShowCategoryPicker()
                        }
                        .padding(vertical = 20.dp, horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier,
                        text = "category: $categoryName",
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(32.dp))
                        .background(Color.Green)
                        .clickable {
                            onShowDatePicker()
                        }
                        .padding(vertical = 20.dp, horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier,
                        text = "Date: ${date.toString()}",
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(32.dp))
                        .background(Color.Red)
                        .clickable {
                            onShowAmountCalculator()
                        }
                        .padding(vertical = 20.dp, horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier,
                        text = "Amount: $amount",
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                PrimaryStandardButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Save",
                    onClick = {
                        onSave(
                            expense?.copy(
                                id = expense.id,
                                name = inputText,
                                amount = amount,
                                date = date,
                                categoryId = categoryId,
                            ) ?: Expense(
                                name = inputText,
                                amount = amount,
                                date = date,
                                categoryId = categoryId,
                            )
                        )
                    }
                )
            }
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

}

/**
 * Date picker
 * A composable that provides
 * 1. A date picker with year, month, day (do not use 3rd-party library)
 * 2. A button to confirm the date
 * @param defaultDate: the default date of the date picker
 * @param onPick: callback when user pick a date
 */
@Composable
fun DatePicker(defaultDate: Date?, onPick: (date: Date) -> Unit) {

}