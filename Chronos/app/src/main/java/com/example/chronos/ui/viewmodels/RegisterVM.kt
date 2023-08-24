package com.example.chronos.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.chronos.realm.dbconfig.CRUDoverwrite
import com.example.chronos.realm.dbconfig.Connection
import com.example.chronos.realm.realmclass.Chron
import com.example.chronos.ui.navigations.NavRoutes
import kotlinx.coroutines.launch

class RegisterVM: ViewModel() {
  private val repo = CRUDoverwrite(Connection.connectDB())
  private val chronData = repo.fetchData()

  // Insert new chron data
  fun insertChron(newChron: Chron, navController: NavHostController) {
    viewModelScope.launch {
      if(!usedUsername(newChron.chronUsername)) {
        if(!usedEmail(newChron.chronEmail)) {
          repo.insertChron(newChron)

          Log.d("Chron", "Success")

          navController.navigate(NavRoutes.Login.route)
        } else {
          Log.d("Chron", "Email address taken.")
        }
      } else {
        Log.d("Chron", "Username taken.")
      }
    }
  }

  // Check for duplication username or email
  private fun usedUsername(username: String): Boolean {
    val isUsedUsername = chronData.find { chron ->
      chron.chronUsername == username
    }

    return isUsedUsername != null
  }

  private fun usedEmail(email: String): Boolean {
    val isUsedEmail = chronData.find { chron ->
      chron.chronEmail == email
    }

    return isUsedEmail != null
  }
}