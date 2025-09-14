package org.example.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.database.AyatRepository

class DemoViewModel(private val repository: AyatRepository) : ViewModel() {
    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()

    init {
        getAyatOfSurah()
    }

    private fun getAyatOfSurah() {
        viewModelScope.launch(Dispatchers.IO) {
            val ayat = repository.getAyatBySuraNo(2)
            _state.update { ayat.joinToString(" ") { it.aya_text } }
        }
    }
}