package com.example.chronos.ui.navigations

sealed class NavRoutes(val route: String) {
  object Login: NavRoutes("Login")
  object Register: NavRoutes("Register")
  object Forgot: NavRoutes("Forgot")
}
