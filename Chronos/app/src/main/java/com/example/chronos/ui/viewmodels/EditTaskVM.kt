package com.example.chronos.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chronos.realm.dbconfig.CRUDoverwrite
import com.example.chronos.realm.dbconfig.Connection
import com.example.chronos.realm.realmclass.UserSession
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime

class EditTaskVM: ViewModel() {
  private val realm = Connection.connectDB()
  private val repo = CRUDoverwrite(realm)

  fun updateTask(
    updateTitle: String,
    updateDescription: String,
    updateStartDate: String,
    updateEndDate: String,
    updateDuration: String,
    updatePriority: String,
    updateIsCompleted: Boolean
  ) {
    viewModelScope.launch {
      UserSession.sessionEditTask?.let {
        repo.updateEpheron(
          it,
          updateTitle,
          updateDescription,
          updateStartDate,
          updateEndDate,
          updateDuration,
          updatePriority,
          updateIsCompleted
        )
      }
    }
  }

  fun deleteTask() {
    viewModelScope.launch {
      UserSession.sessionEditTask?.let { repo.deleteEpheron(it) }
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