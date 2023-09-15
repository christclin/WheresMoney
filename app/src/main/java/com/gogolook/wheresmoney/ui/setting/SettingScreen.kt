package com.gogolook.wheresmoney.ui.setting

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gogolook.wheresmoney.data.Category
import com.gogolook.wheresmoney.ui.components.FloatingActionButton
import com.gogolook.wheresmoney.ui.components.Toolbar
import com.gogolook.wheresmoney.ui.components.ToolbarAction

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
    Surface(onClick = createCategory, modifier = Modifier.fillMaxSize()) {

        var categoryList = viewModel.categoryList
        viewModel.update()
        SettingPanel(categoryList)
    }

}

@Composable
fun SettingPanel(
    categoryList: List<Category>,
    back: () -> Unit = {},
    createCategory: () -> Unit = {},
    updateCategory: (categoryId: Int) -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar(
                headerAction = ToolbarAction(image = Icons.Outlined.ArrowBack, onClick = {}),
                title = "WheresMoneySetting",
                tailActions = listOf(

                    ToolbarAction(
                        image = Icons.Outlined.Settings,
                        onClick = back
                    )
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(icon = Icons.Outlined.Add) {
//                createExpense()
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
        }
    }
    LazyColumn {
        items(categoryList) { category ->
            SettingItem(category)
        }
    }
}

@Composable
fun SettingItem(category: Category) {
    Row(modifier = Modifier.clickable { }) {
        Text(
            text = category.id.toString(),
            modifier = Modifier,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Surface(
            shape = MaterialTheme.shapes.medium,
            // surfaceColor color will be changing gradually from primary to surface
            color = MaterialTheme.colorScheme.primary,
            // animateContentSize will change the Surface size gradually
            modifier = Modifier
                .animateContentSize()
                .padding(1.dp)
        ) {
            Text(
                text = category.name,
                modifier = Modifier.padding(all = 4.dp),
            )
        }
    }
}


@Preview
@Composable
fun PreviewConversation() {
    var categoryList = mutableListOf<Category>()
    categoryList.add(Category(name = "Food", color = Color.Red.value.toLong()))
    categoryList.add(Category(name = "Food", color = Color.Red.value.toLong()))
    categoryList.add(Category(name = "Food", color = Color.Red.value.toLong()))
    categoryList.add(Category(name = "Food", color = Color.Red.value.toLong()))
    categoryList.add(Category(name = "Food", color = Color.Red.value.toLong()))
    categoryList.add(Category(name = "Food", color = Color.Red.value.toLong()))
    SettingPanel(categoryList)
}