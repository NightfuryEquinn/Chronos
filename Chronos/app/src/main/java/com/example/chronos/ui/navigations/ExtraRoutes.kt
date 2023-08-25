package com.example.chronos.ui.navigations

// Extra navigation
sealed class ExtraRoutes(val route: String) {
  object UpdateProfile: ExtraRoutes("Update Profile")
  object TimeBased: ExtraRoutes("Time Based")
  object PriorityBased: ExtraRoutes("Priority Based")
  object EditTask: ExtraRoutes("Edit Task")
}