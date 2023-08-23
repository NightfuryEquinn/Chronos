package com.example.chronos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chronos.ui.pagesinner.CalendarPage
import com.example.chronos.ui.pagesinner.EditTaskPage
import com.example.chronos.ui.pagesinner.PriorityBasedPage
import com.example.chronos.ui.pagesinner.TimeBasedPage
import com.example.chronos.ui.theme.ChronosTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      ChronosTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          EditTaskPage()
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
  ChronosTheme {
    EditTaskPage()
  }
}