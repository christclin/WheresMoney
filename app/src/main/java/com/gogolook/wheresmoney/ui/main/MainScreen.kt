package com.gogolook.wheresmoney.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.gogolook.wheresmoney.ui.components.FloatingActionButton
import com.gogolook.wheresmoney.ui.components.Toolbar
import com.gogolook.wheresmoney.ui.components.ToolbarAction

/**
 * The main screen of the app.
 * A composable that provides
 * 1. A toolbar with setting button and title, click on the setting button will callback gotoSetting()
 * 2. A floating button to callback createExpense()
 * 3. A expense list, click on the expense item will callback updateExpense()
 * @param viewModel the view model for main screen.
 * @param gotoSetting the callback when setting button is clicked
 * @param createExpense the callback when create expense button is clicked
 * @param updateExpense the callback when expense item is clicked
 */
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    gotoSetting: () -> Unit = {},
    createExpense: () -> Unit = {},
    updateExpense: (expenseId: Int) -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar(
//                headerAction = ToolbarAction(image = Icons.Outlined.ArrowBack, onClick = {}),
                title = "WheresMoney",
                tailActions = listOf(ToolbarAction(image = Icons.Outlined.Settings, onClick = gotoSetting))
            )
        },
        floatingActionButton = {
            FloatingActionButton(icon = Icons.Outlined.Add) {
                createExpense()
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            ExpenseListView(hiltViewModel(), onExpenseClick = updateExpense)
        }
    }
}
