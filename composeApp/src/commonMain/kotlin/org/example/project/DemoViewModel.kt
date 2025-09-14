package org.example.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DemoViewModel() : ViewModel() {
    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()

    init {
        getAyatOfSurah()
    }

    private fun getAyatOfSurah() {
        viewModelScope.launch(Dispatchers.IO) {}
    }
}