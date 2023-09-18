package com.gogolook.wheresmoney.ui.setting

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.gogolook.wheresmoney.data.Category
import com.gogolook.wheresmoney.data.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
) : ViewModel() {
    val categories: MutableState<List<Category>> = mutableStateOf(emptyList())

    suspend fun fetchCategories() {
        categories.value = withContext(Dispatchers.IO) {
            categoryRepository.getAll()
        }
    }
}