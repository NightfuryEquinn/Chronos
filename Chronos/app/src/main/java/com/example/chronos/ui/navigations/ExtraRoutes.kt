package com.example.chronos.ui.navigations

sealed class ExtraRoutes(val route: String) {
  object UpdateProfile: ExtraRoutes("Update Profile")
}