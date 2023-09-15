package com.gogolook.wheresmoney.ui.setting

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gogolook.wheresmoney.data.Category
import com.gogolook.wheresmoney.data.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    val categoryRepository: CategoryRepository,
) : ViewModel() {
    var categoryList: List<Category> = mutableStateListOf()

    fun update() {
        viewModelScope.launch(Dispatchers.IO) { categoryList = categoryRepository.getAll() }

    }

}