package com.example.chronos.ui.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange

object NavBarItems {
  val BarItems = listOf(
    BarItem(
      title = "PROFILE",
      image = Icons.Filled.AccountCircle,
      route = "Profile"
    ),
    BarItem(
      title = "CREATE",
      image = Icons.Filled.AddCircle,
      route = "Create"
    ),
    BarItem(
      title = "CALENDAR",
      image = Icons.Filled.DateRange,
      route = "Calendar"
    )
  )
}