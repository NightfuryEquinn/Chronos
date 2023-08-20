package com.example.chronos

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chronos.ui.navigations.NavRoutes
import com.example.chronos.ui.pages.ForgotPage
import com.example.chronos.ui.pages.LoginPage
import com.example.chronos.ui.pages.RegisterPage
import com.example.chronos.ui.viewmodels.RegisterVM

@Composable
fun MainScreen() {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = NavRoutes.Login.route
  ) {
    composable(NavRoutes.Register.route) {
      RegisterPage(navController = navController)
    }

    composable(NavRoutes.Forgot.route) {
      ForgotPage(navController = navController)
    }

    composable(NavRoutes.Login.route) {
      LoginPage(navController = navController)
    }
  }
}