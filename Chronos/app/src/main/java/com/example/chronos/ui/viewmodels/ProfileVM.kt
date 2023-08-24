package com.example.chronos.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.chronos.realm.realmclass.UserSession
import com.example.chronos.ui.navigations.InnerNavRoutes
import com.example.chronos.ui.navigations.NavRoutes

class ProfileVM: ViewModel() {
  // Clear user session
  fun clearAndLogOut(navController: NavHostController) {
    UserSession.sessionToken = null
    UserSession.sessionUsername = null
    UserSession.sessionEmail = null

    navController.navigate("MainScreen") {
      popUpTo(InnerNavRoutes.Profile.route) {
        inclusive = true
      }
    }
  }
}