package com.example.chronos.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.chronos.realm.dbconfig.CRUDoverwrite
import com.example.chronos.realm.dbconfig.Connection
import com.example.chronos.ui.navigations.NavRoutes
import kotlinx.coroutines.launch

class ForgotVM: ViewModel() {
  private val repo = CRUDoverwrite(Connection.connectDB())

  // Update the password
  fun updateChron(email: String, password: String, navController: NavController) {
    viewModelScope.launch {
      val chronData = repo.filterData(email)

      // Find the chron object in relate to email address
      val isExistingChron = chronData.find { chron ->
        chron.chronEmail == email
      }

      if(isExistingChron != null) {
        repo.updateChronPassword(email, password)

        navController.navigate(NavRoutes.Login.route)
      }
    }
  }
}