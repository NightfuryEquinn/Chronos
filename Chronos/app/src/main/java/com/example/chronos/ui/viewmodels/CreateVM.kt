package com.example.chronos.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chronos.realm.dbconfig.CRUDoverwrite
import com.example.chronos.realm.dbconfig.Connection
import com.example.chronos.realm.realmclass.Epheron
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime

class CreateVM: ViewModel() {
  private val repo = CRUDoverwrite(Connection.connectDB())

  // Insert new epheron data
  fun insertEpheron(newEpheron: Epheron) {
    viewModelScope.launch {
      repo.insertEpheron(newEpheron)
    }
  }

  // Calculate duration between start date and end date
  fun calculateDuration(startDate: LocalDateTime?, endDate: LocalDateTime?): String {
    val duration = Duration.between(startDate, endDate)

    val days = duration.toDays()
    val hours = duration.toHours() % 24
    val minutes = duration.toMinutes() % 60

    return "$days day(s), $hours hour(s), $minutes minute(s)"
  }
}