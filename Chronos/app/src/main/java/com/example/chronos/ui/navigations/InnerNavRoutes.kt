package com.example.chronos.ui.navigations

// Inner screen navigation
sealed class InnerNavRoutes(val route: String) {
  object Profile: InnerNavRoutes("Profile")
  object Create: InnerNavRoutes("Create")
  object Calendar: InnerNavRoutes("Calendar")
}
