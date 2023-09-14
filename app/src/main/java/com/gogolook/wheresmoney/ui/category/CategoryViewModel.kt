package com.gogolook.wheresmoney.ui.category

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gogolook.wheresmoney.data.Category
import com.gogolook.wheresmoney.data.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
) : ViewModel() {

    val category: MutableState<Category?> = mutableStateOf(null)

    suspend fun fetchCategory(categoryId: Int) {
        if (categoryId == 0) return
        category.value = withContext(Dispatchers.IO) {
            categoryRepository.get(categoryId)
        }
    }

    fun saveCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            if (category.id != 0) {
                categoryRepository.update(category)
            } else {
                categoryRepository.insert(category)
            }
        }
    }
}