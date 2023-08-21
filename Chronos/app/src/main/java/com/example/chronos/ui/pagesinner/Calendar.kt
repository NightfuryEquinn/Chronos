package com.example.chronos.ui.pagesinner

import android.util.Log
import android.widget.CalendarView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CalendarPage() {
  // State variables
  var date by remember { mutableStateOf("") }
  
  AndroidView(factory = { CalendarView(it) }, update = {
    it.setOnDateChangeListener { calendarView, year, month, day ->
      date = "$day - ${month + 1} - $year"
    }
  })
}