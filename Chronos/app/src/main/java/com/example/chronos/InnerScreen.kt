package com.example.chronos

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.chronos.realm.realmclass.UserSession
import com.example.chronos.ui.navigations.ExtraRoutes
import com.example.chronos.ui.navigations.InnerNavRoutes
import com.example.chronos.ui.navigations.NavRoutes
import com.example.chronos.ui.pages.LoginPage
import com.example.chronos.ui.pagesinner.BottomNavBar
import com.example.chronos.ui.pagesinner.CalendarPage
import com.example.chronos.ui.pagesinner.CreatePage
import com.example.chronos.ui.pagesinner.ProfilePage
import com.example.chronos.ui.pagesinner.UpdateProfilePage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InnerScreen() {
  val navController = rememberNavController()

  val backStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = backStackEntry?.destination?.route

  Scaffold (
    content = {
      InnerNavigationHost(navController = navController)
    },
    bottomBar = {
      // Check user is in main screen, yes, disable bottom navigation
      if(currentRoute != "MainScreen") BottomNavBar(navController = navController)
    },
  )
}

@Composable
fun InnerNavigationHost(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = "start"
  ) {
    composable("start") {
      if(UserSession.sessionToken == null) {
        navController.navigate("MainScreen")
      } else {
        navController.navigate(InnerNavRoutes.Profile.route)
      }
    }

    composable("MainScreen") {
      MainScreen()
    }

    composable(InnerNavRoutes.Profile.route) {
      ProfilePage(navController = navController)
    }

    composable(InnerNavRoutes.Create.route) {
      CreatePage()
    }

    composable(InnerNavRoutes.Calendar.route) {
      CalendarPage()
    }

    composable(ExtraRoutes.UpdateProfile.route) {
      UpdateProfilePage(navController = navController)
    }
  }
}