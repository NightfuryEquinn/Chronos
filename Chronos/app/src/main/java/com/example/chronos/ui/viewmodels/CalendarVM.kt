package com.example.chronos.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CalendarVM: ViewModel() {
  val selectedDate = MutableStateFlow("")

  fun parseToTimeBased(date: String) {
    viewModelScope.launch {
      selectedDate.emit(date)
    }
  }
}