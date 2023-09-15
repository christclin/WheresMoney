package com.gogolook.wheresmoney.ui.setting

import androidx.lifecycle.ViewModel
import com.gogolook.wheresmoney.data.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
) : ViewModel() {

}