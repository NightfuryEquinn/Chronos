package com.example.chronos.ui.navigations

sealed class InnerNavRoutes(val route: String) {
  object Profile: InnerNavRoutes("Profile")
  object Create: InnerNavRoutes("Create")
  object Calendar: InnerNavRoutes("Calendar")
}
