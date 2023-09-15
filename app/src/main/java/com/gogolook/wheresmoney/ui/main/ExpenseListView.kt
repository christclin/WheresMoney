package com.gogolook.wheresmoney.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

/**
 * A composable that provides expense list, click on the expense item will callback onExpenseClick().
 * @param viewModel the view model for expense list.
 * @param onExpenseClick the callback when expense item is clicked
 */
@Composable
fun ExpenseListView(viewModel: ExpenseListViewModel, onExpenseClick: (expenseId: Int) -> Unit) {
    viewModel.queryExpense()
    val data = viewModel.expenses.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.DarkGray)
            ,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(5.dp),
        ) {
            items(data.value.size) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF73B95B),
                            shape = RoundedCornerShape(16.dp),
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.White)) {
                                append("金額：${data.value[it].amount}" + "\n" +
                                        "項目：${data.value[it].name}" + "\n" +
                                        "日期：${data.value[it].date}" + "\n" +
                                        "類型：")
                            }
                            withStyle(style = SpanStyle(color = Color(value = data.value[it].category?.color?.toULong() ?: 0.toULong()))) {
                                append("${data.value[it].category?.name}")
                            }
                        },
                        modifier = Modifier.clickable { onExpenseClick(data.value[it].id) },
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}