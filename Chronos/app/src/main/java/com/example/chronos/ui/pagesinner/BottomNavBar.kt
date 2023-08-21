package com.example.chronos.ui.pagesinner

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.chronos.R
import com.example.chronos.ui.bottombar.NavBarItems

@Composable
fun BottomNavBar(navController: NavHostController) {
  BottomNavigation(
    backgroundColor = Color(0xFFA49A8E),
    modifier = Modifier
      .height(64.dp)
  ) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavBarItems.BarItems.forEach { navItem ->
      BottomNavigationItem(
        selected = currentRoute == navItem.route,
        onClick = {
          navController.navigate(navItem.route) {
            popUpTo(navController.graph.findStartDestination().id) {
              saveState = true
            }

            launchSingleTop = true
            restoreState = true
          }
        },

        icon = {
          Icon(
            imageVector = navItem.image,
            contentDescription = navItem.title,
            tint = if(currentRoute == navItem.route) Color(0xFFE4DDD5) else Color(0xFF504848),
            modifier = Modifier
              .padding(vertical = 8.dp)
          )
        },

        label = {
          Text(
            text = navItem.title,
            style = TextStyle(
              color = if(currentRoute == navItem.route) Color(0xFFE4DDD5) else Color(0xFF504848),
              fontFamily = FontFamily(Font(R.font.oswald))
            ),
            modifier = Modifier
              .padding(vertical = 8.dp)
          )
        }
      )
    }
  }
}