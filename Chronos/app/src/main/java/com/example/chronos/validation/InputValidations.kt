package com.example.chronos.validation

import java.time.LocalDateTime

// Function to validate email address
fun String.isValidEmail() : Boolean {
  return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

// Compare the start date and end date
fun compareDates(startDate: LocalDateTime, endDate: LocalDateTime): Boolean {
  val comparison = startDate.compareTo(endDate)

  return when {
    comparison < 0 -> true
    comparison > 0 -> false
    else -> false
  }
}