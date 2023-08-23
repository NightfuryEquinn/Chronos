package com.example.chronos.ui.navigations

// Extra navigation
sealed class ExtraRoutes(val route: String) {
  object UpdateProfile: ExtraRoutes("Update Profile")
}