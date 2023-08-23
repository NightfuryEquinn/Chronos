package com.example.chronos

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chronos.ui.navigations.ExtraRoutes
import com.example.chronos.ui.navigations.InnerNavRoutes
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

  Scaffold (
    content = {
      InnerNavigationHost(navController = navController)
    },
    bottomBar = {
      BottomNavBar(navController = navController)
    },
  )
}

@Composable
fun InnerNavigationHost(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = InnerNavRoutes.Calendar.route
  ) {
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