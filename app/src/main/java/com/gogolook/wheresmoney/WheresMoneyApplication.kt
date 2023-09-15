package com.gogolook.wheresmoney

import android.app.Application
import android.util.Log
import androidx.compose.ui.graphics.Color
import com.gogolook.wheresmoney.data.Category
import com.gogolook.wheresmoney.data.CategoryRepository
import com.gogolook.wheresmoney.data.Expense
import com.gogolook.wheresmoney.data.ExpenseRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltAndroidApp
class WheresMoneyApplication : Application() {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    @Inject
    lateinit var expenseRepository: ExpenseRepository

    @Inject
    lateinit var expenseData: ExpenseData

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            if (!expenseData.readExpense()) {
                categoryRepository.insert(Category(name = "Food", color = Color.Red.value.toLong()))
                categoryRepository.insert(Category(name = "Transportation", color = Color.Blue.value.toLong()))
                categoryRepository.insert(Category(name = "Entertainment", color = Color.Green.value.toLong()))
                categoryRepository.insert(Category(name = "Shopping", color = Color.Yellow.value.toLong()))
                categoryRepository.insert(Category(name = "Bills", color = Color.Magenta.value.toLong()))
                categoryRepository.insert(Category(name = "Others", color = Color.Gray.value.toLong()))

                expenseRepository.insert(Expense(categoryId = 1, amount = 100, name = "Lunch"))
                expenseRepository.insert(Expense(categoryId = 2, amount = 200, name = "Bus"))
                expenseRepository.insert(Expense(categoryId = 3, amount = 300, name = "Movie"))
                expenseRepository.insert(Expense(categoryId = 4, amount = 400, name = "Shoes"))
                expenseRepository.insert(Expense(categoryId = 5, amount = 500, name = "Electricity"))
                expenseRepository.insert(Expense(categoryId = 6, amount = 600, name = "Others"))

                expenseData.storeExpense(true)
            }

            val expenses = expenseRepository.getAll(Date())
            expenseRepository.getAll(Date()).forEach {
                Log.i("CHRISS", "expense: $it, category: ${it.category}")
            }
        }
    }
}