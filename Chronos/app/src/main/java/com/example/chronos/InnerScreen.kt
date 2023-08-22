package com.example.chronos

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.chronos.ui.navigations.InnerNavRoutes
import com.example.chronos.ui.pagesinner.BottomNavBar
import com.example.chronos.ui.pagesinner.CalendarPage
import com.example.chronos.ui.pagesinner.CreatePage
import com.example.chronos.ui.pagesinner.ProfilePage

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
      ProfilePage()
    }

    composable(InnerNavRoutes.Create.route) {
      CreatePage()
    }

    composable(InnerNavRoutes.Calendar.route) {
      CalendarPage()
    }
  }
}