package com.example.chronos.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.chronos.realm.dbconfig.CRUDoverwrite
import com.example.chronos.realm.dbconfig.Connection
import com.example.chronos.realm.realmclass.Chron
import com.example.chronos.realm.realmclass.UserSession
import com.example.chronos.ui.navigations.NavRoutes
import io.realm.kotlin.ext.query

class LoginVM: ViewModel() {
  private val realm = Connection.connectDB()
  private val repo = CRUDoverwrite(realm)
  private val chronData = repo.fetchData()

  // Assign global variables as session
  fun assignGlobal(username: String, password: String, navController: NavHostController) {
    if(validateChron(username, password)) {
      val theChronData = realm.query<Chron>("chronUsername = $0 AND chronPassword = $1", username, password).first().find()

      UserSession.sessionToken = theChronData?._chron_id.toString()
      UserSession.sessionUsername = theChronData?.chronUsername
      UserSession.sessionEmail = theChronData?.chronEmail

      // Navigate to inner screen and clear main screen
      navController.navigate("InnerScreen") {
        popUpTo(NavRoutes.Login.route) {
          inclusive = true
        }
      }
    }
  }

  // Validate existing chron data
  private fun validateChron(username: String, password: String): Boolean {
    // Find the chron object username and password
    val isExistingChron = chronData.find { chron ->
      chron.chronUsername == username && chron.chronPassword == password
    }

    return isExistingChron != null
  }
}