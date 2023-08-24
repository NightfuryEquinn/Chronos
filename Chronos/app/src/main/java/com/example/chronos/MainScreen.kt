package com.example.chronos

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chronos.realm.realmclass.UserSession
import com.example.chronos.ui.navigations.NavRoutes
import com.example.chronos.ui.pages.ForgotPage
import com.example.chronos.ui.pages.LoginPage
import com.example.chronos.ui.pages.RegisterPage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
  val navController = rememberNavController()

  Scaffold (
    content = {
      MainNavigationHost(navController = navController)
    },
    bottomBar = {

    }
  )
}

@Composable
fun MainNavigationHost(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = "start"
  ) {
    composable("start") {
      if(UserSession.sessionToken != null) {
        navController.navigate("InnerScreen")
      } else {
        navController.navigate(NavRoutes.Login.route)
      }
    }

    composable(NavRoutes.Register.route) {
      RegisterPage(navController = navController)
    }

    composable(NavRoutes.Forgot.route) {
      ForgotPage(navController = navController)
    }

    composable(NavRoutes.Login.route) {
      LoginPage(navController = navController)
    }

    composable("InnerScreen") {
      InnerScreen()
    }
  }
}