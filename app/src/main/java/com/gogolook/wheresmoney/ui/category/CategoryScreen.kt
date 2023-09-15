package com.gogolook.wheresmoney.ui.category

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gogolook.wheresmoney.data.Category

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
fun CategoryView(category: Category?, onSave: (category: Category) -> Unit) {
    val shouldShowColorPicker = remember { mutableStateOf(false) }
    val color = remember { mutableStateOf(Color.Red) }

    TODO("Implement CategoryView")

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
    var r by remember { mutableFloatStateOf(0f) }
    var g by remember { mutableFloatStateOf(0f) }
    var b by remember { mutableFloatStateOf(0f) }
    var selectedColor by remember { mutableStateOf(Color.White) }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Picked Color")
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(selectedColor),
            contentAlignment = Alignment.Center
        ) {}
        Spacer(modifier = Modifier.height(16.dp))

        Slider(
            value = r,
            onValueChange = { r = it },
            valueRange = 0f..255f,
            steps = 256,
            onValueChangeFinished = {
                selectedColor = Color(r.toInt(), g.toInt(), b.toInt())
            },
        )
        Text(text = "R: ${r.toInt()}")

        Slider(
            value = g,
            onValueChange = { g = it },
            valueRange = 0f..255f,
            steps = 256,
            onValueChangeFinished = {
                selectedColor = Color(r.toInt(), g.toInt(), b.toInt())
            },
        )
        Text(text = "G: ${g.toInt()}")

        Slider(
            value = b,
            onValueChange = { b = it },
            valueRange = 0f..255f,
            steps = 256,
            onValueChangeFinished = {
                selectedColor = Color(r.toInt(), g.toInt(), b.toInt())
            },
        )
        Text(text = "B: ${b.toInt()}")

        Button(onClick = {
            onPick(selectedColor)
        }) {
            Text(text = "Confirm")
        }
    }
}