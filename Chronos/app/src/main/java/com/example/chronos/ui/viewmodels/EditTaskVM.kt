package com.example.chronos.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chronos.realm.dbconfig.CRUDoverwrite
import com.example.chronos.realm.dbconfig.Connection
import com.example.chronos.realm.realmclass.UserSession
import kotlinx.coroutines.launch

class EditTaskVM: ViewModel() {
  private val realm = Connection.connectDB()
  private val repo = CRUDoverwrite(realm)

  fun updateTask(
    updateTitle: String,
    updateDescription: String,
    updateStartDate: String,
    updateEndDate: String,
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
}