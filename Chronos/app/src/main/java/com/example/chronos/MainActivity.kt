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
import androidx.lifecycle.ViewModelProvider
import com.example.chronos.realm.dbconfig.CRUDoverwrite
import com.example.chronos.realm.dbconfig.Connection
import com.example.chronos.ui.pages.LoginPage
import com.example.chronos.ui.theme.ChronosTheme
import com.example.chronos.ui.viewmodels.RegisterVM
import io.realm.kotlin.Realm

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      ChronosTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          MainScreen()
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
  ChronosTheme {
    MainScreen()
  }
}