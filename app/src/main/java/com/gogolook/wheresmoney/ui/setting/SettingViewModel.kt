package com.gogolook.wheresmoney.ui.setting

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.gogolook.wheresmoney.data.Category
import com.gogolook.wheresmoney.data.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    val categoryRepository: CategoryRepository,
) : ViewModel() {
    var categoryList: List<Category> = mutableStateListOf()

    fun update() {
        categoryList = categoryRepository.getAll()
    }

}