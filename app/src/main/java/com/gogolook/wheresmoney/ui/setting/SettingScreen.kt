package com.gogolook.wheresmoney.ui.setting

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

/**
 * A composable that provides
 * 1. category list
 * 2. floating button to callback createCategory()
 * 3. click on the category item will callback updateCategory()
 * 4. a toolbar with back button and title
 * @param viewModel the view model for category list.
 * @param back the callback when back button is clicked
 * @param createCategory the callback when create category button is clicked
 * @param updateCategory the callback when category item is clicked
 */
@Composable
fun SettingScreen(
    viewModel: SettingViewModel,
    back: () -> Unit = {},
    createCategory: () -> Unit = {},
    updateCategory: (categoryId: Int) -> Unit = {},
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchCategories()
    }
    val categories = viewModel.categories.value

    // TODO Implement Categories List view
}