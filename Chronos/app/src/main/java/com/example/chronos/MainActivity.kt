package com.example.chronos

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.edit
import com.example.chronos.realm.realmclass.UserSession
import com.example.chronos.ui.pages.LoadingPage
import com.example.chronos.ui.theme.ChronosTheme
import com.example.chronos.ui.viewmodels.LoadingVM

class MainActivity : ComponentActivity() {
  // Get loading view model
  private val loadingVM by viewModels<LoadingVM>()

  // Shared preferences
  private lateinit var sharedPreferences: SharedPreferences

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      ChronosTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF100C09)) {
          // Observe the loading progress from view model
          val loadingProgress by loadingVM.loadingProgress.collectAsState()

          if(loadingProgress < 1.0f) {
            LoadingPage(loadingProgress)
          } else {
            MainScreen()
          }
        }
      }
    }

    sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE)

    // Retrieve data from shared preferences
    val sessionToken = sharedPreferences.getString("session_token", null)
    val sessionUsername = sharedPreferences.getString("session_username", null)
    val sessionEmail = sharedPreferences.getString("session_email", null)

    // Restore the user session
    UserSession.sessionToken = sessionToken
    UserSession.sessionUsername = sessionUsername
    UserSession.sessionEmail = sessionEmail
  }

  override fun onStop() {
    super.onStop()

    sharedPreferences.edit {
      putString("session_token", UserSession.sessionToken)
      putString("session_username", UserSession.sessionUsername)
      putString("session_email", UserSession.sessionEmail)
    }
  }

  // Ignore deprecation
  @Deprecated("Deprecated in Java")
  override fun onBackPressed() {
    super.onBackPressed()

    finish()
  }
}