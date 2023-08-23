package com.example.chronos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.chronos.ui.pages.LoadingPage
import com.example.chronos.ui.pagesinner.CalendarPage
import com.example.chronos.ui.pagesinner.EditTaskPage
import com.example.chronos.ui.pagesinner.PriorityBasedPage
import com.example.chronos.ui.pagesinner.TimeBasedPage
import com.example.chronos.ui.theme.ChronosTheme
import com.example.chronos.ui.viewmodels.LoadingVM

class MainActivity : ComponentActivity() {
  private val loadingVM by viewModels<LoadingVM>()

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
  }
}