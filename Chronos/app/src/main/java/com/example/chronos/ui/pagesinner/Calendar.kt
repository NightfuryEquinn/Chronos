package com.example.chronos.ui.pagesinner

import android.widget.CalendarView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.chronos.R
import com.example.chronos.realm.realmclass.UserSession
import com.example.chronos.ui.navigations.ExtraRoutes
import com.example.chronos.ui.viewmodels.CalendarVM

@Composable
fun CalendarPage(navController: NavHostController, calendarVM: CalendarVM = viewModel()) {
  // State variables
  var date by remember { mutableStateOf("") }

  val monthNames = arrayOf(
    "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
  )

  Column(
    modifier = Modifier
      .background(Color(0xFF100C09)),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      painter = painterResource(id = R.drawable.logo),
      contentDescription = "Chronos_Logo",
      modifier = Modifier
        .padding(top = 16.dp)
        .size(150.dp)
    )

    Spacer(
      modifier = Modifier.height(16.dp)
    )

    Box(
      modifier = Modifier
        .padding(horizontal = 16.dp)
    ) {
      AndroidView(
        factory = { context ->
          // Create instance
          val calendarView = CalendarView(context)

          // Apply customization
          calendarView.setBackgroundColor(0xFFE4DDD5.toInt())

          calendarView
        },
        update = {
          it.setOnDateChangeListener { _, year, month, day ->
            val monthName = monthNames[month]
            date = "$day $monthName $year"

            calendarVM.parseToTimeBased(date)

            if(UserSession.sessionFilterQuery == false) {
              navController.navigate(ExtraRoutes.TimeBased.route)
            } else {
              navController.navigate(ExtraRoutes.PriorityBased.route)
            }
          }
        },
        modifier = Modifier
          .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
      )
    }

    Spacer(
      modifier = Modifier
        .padding(vertical = 72.dp)
    )
  }
}