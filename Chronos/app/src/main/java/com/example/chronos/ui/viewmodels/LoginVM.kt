package com.example.chronos.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chronos.realm.dbconfig.CRUDoverwrite
import com.example.chronos.realm.dbconfig.Connection

class LoginVM: ViewModel() {
  // Validate existing chron data
  fun validateChron(username: String, password: String): Boolean {
    val repo = CRUDoverwrite(Connection.connectDB())
    val chronData = repo.fetchData()

    // Find the chron object username and password
    val isExistingChron = chronData.find { chron ->
      chron.chronUsername == username && chron.chronPassword == password
    }

    return isExistingChron != null
  }
}