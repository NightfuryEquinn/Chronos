package com.example.chronos.ui.pagesinner

import android.annotation.SuppressLint
import android.util.Log
import android.widget.CalendarView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.chronos.R

@Composable
fun CalendarPage() {
  // State variables
  var date by remember { mutableStateOf("") }

  Column(
    modifier = Modifier
      .fillMaxSize()
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
            date = "$day - ${month + 1} - $year"
          }
        },
        modifier = Modifier
          .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
      )
    }
  }
}