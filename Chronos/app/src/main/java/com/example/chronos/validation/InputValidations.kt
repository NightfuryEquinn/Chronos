package com.example.chronos.validation

// Function to validate email address
fun String.isValidEmail() : Boolean {
  return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}