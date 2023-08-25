package com.example.chronos.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.chronos.realm.dbconfig.CRUDoverwrite
import com.example.chronos.realm.dbconfig.Connection
import com.example.chronos.realm.realmclass.UserSession
import com.example.chronos.ui.navigations.InnerNavRoutes
import kotlinx.coroutines.launch

class ProfileVM: ViewModel() {
  private val realm = Connection.connectDB()
  private val repo = CRUDoverwrite(realm)
  private val chronData = repo.fetchData()

  // Clear user session
  fun clearAndLogOut(navController: NavHostController) {
    UserSession.sessionToken = null
    UserSession.sessionUsername = null
    UserSession.sessionEmail = null
    UserSession.sessionPassword = null

    navController.navigate("MainScreen") {
      popUpTo(InnerNavRoutes.Profile.route) {
        inclusive = true
      }
    }
  }

  // Update user profile and user session
  fun updateChronAndSession(username: String, email: String, password: String, navController: NavHostController) {
    viewModelScope.launch {
      if(!usedUsername(username)) {
        if(!usedEmail(email)) {
          UserSession.sessionToken?.let {
            repo.updateChronProfile(it, username, email, password)
          }

          UserSession.sessionUsername = username
          UserSession.sessionEmail = email
          UserSession.sessionPassword = password

          navController.navigate(InnerNavRoutes.Profile.route)
        }
      }
    }
  }

  // Check for duplication username or email
  private fun usedUsername(username: String): Boolean {
    val isUsedUsername = chronData.any { chron ->
      chron.chronUsername == username
    }

    val isSessionUsername = username == UserSession.sessionUsername

    return isUsedUsername && !isSessionUsername
  }

  private fun usedEmail(email: String): Boolean {
    val isUsedEmail = chronData.any { chron ->
      chron.chronEmail == email
    }

    val isSessionEmail = email == UserSession.sessionEmail

    return isUsedEmail && !isSessionEmail
  }
}