package com.gogolook.wheresmoney.ui.category

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gogolook.wheresmoney.data.Category
import com.gogolook.wheresmoney.ui.components.FloatingActionButton
import com.gogolook.wheresmoney.ui.components.Toolbar
import com.gogolook.wheresmoney.ui.components.ToolbarAction

/**
 * Category screen
 * A composable that provides a screen for user to create or edit a category
 * @param viewModel: CategoryViewModel
 * @param categoryId: the id of the category to be edited, or 0 if creating a new category
 * @param onCompleted: callback when user finish creating or editing a category
 */
@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel,
    categoryId: Int,
    onCompleted: () -> Unit = {},
) {
    LaunchedEffect(key1 = categoryId) {
        viewModel.fetchCategory(categoryId)
    }
    CategoryView(viewModel.category.value, onSave = { category ->
        viewModel.saveCategory(category)
        onCompleted()
    }, onBack = {
        onCompleted()
    })
}

/**
 * Category view
 * A composable that provides a view for user to create or edit a category, you can choose a style
 * 1. A view with toolbar, or
 * 2. A non-full screen view just like a dialog
 * There are two editable fields:
 * 1. Name: the name of the category
 * 2. Color: the color of the category (clicking on this field will show a color picker)
 * @param category: the category to be edited, or null if creating a new category
 * @param onSave: callback when user finish creating or editing a category
 */
@Composable
fun CategoryView(category: Category?, onSave: (category: Category) -> Unit, onBack: () -> Unit) {
    val shouldShowColorPicker = remember { mutableStateOf(false) }
    val name = remember { mutableStateOf("") }
    val color = remember { mutableStateOf(Color.Red) }

    if (category != null) {
        color.value = Color(category.color.toULong())
        name.value = category.name
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar(
                title = "WheresMoney",
                headerAction = ToolbarAction(image = Icons.Outlined.ArrowBack, onClick = {
                    onBack()
                }),
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                icon = Icons.Outlined.Done
            ) {
                val newCategory = if (category != null) {
                    Category(category.id, name.value, color.value.value.toLong())
                } else {
                    Category(name = name.value, color = color.value.value.toLong())
                }
                onSave(newCategory)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .padding(paddingValues = paddingValues)
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = name.value,
                    onValueChange = { name.value = it },
                    label = { Text("Name") }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .clickable {
                        shouldShowColorPicker.value = true
                    }
                    .padding(16.dp)
            ) {
                Column {
                    Text(text = "Color")
                    Spacer(
                        modifier = Modifier
                            .height(36.dp)
                            .width(36.dp)
                            .background(color = color.value)
                    )
                }
            }


        }
    }

    AnimatedVisibility(visible = shouldShowColorPicker.value) {
        ColorPicker(defaultColor = color.value, onPick = {
            color.value = it
            shouldShowColorPicker.value = false
        })
    }
}

/**
 * Color picker
 * A composable that provides
 * 1. Three Slider for each RGB value, and
 * 2. A preview of the color
 * 3. A button to confirm the color
 * @param defaultColor: the default color of the color picker
 * @param onPick: callback when user pick a color
 */
@Composable
fun ColorPicker(defaultColor: Color? = null, onPick: (color: Color) -> Unit) {

}