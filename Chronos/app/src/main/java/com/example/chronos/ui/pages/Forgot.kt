package com.example.chronos.ui.pages

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.chronos.R
import com.example.chronos.ui.navigations.NavRoutes

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ForgotPage(navController: NavHostController) {
  var email by remember { mutableStateOf("") }
  var newPassword by remember { mutableStateOf("") }
  var confirmNewPassword by remember { mutableStateOf("") }

  val keyboardController = LocalSoftwareKeyboardController.current

  val passwordsMatch = newPassword == confirmNewPassword
  val allFieldsNotEmpty = email.isNotBlank() && newPassword.isNotBlank() && confirmNewPassword.isNotBlank()

  var isPasswordValid by remember { mutableStateOf(false) }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFF100C09)),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Image(
      painter = painterResource(id = R.drawable.logo),
      contentDescription = "Chronos_Logo",
      modifier = Modifier.size(200.dp)
    )

    Spacer(
      modifier = Modifier.height(16.dp)
    )

    TextField(
      value = email,
      onValueChange = { email = it },
      label = {
        Text(
          text = "EMAIL ADDRESS",
          style = TextStyle(
            fontFamily = FontFamily(Font(R.font.oswald))
          )
        )
      },
      textStyle = TextStyle.Default.copy(fontFamily = FontFamily(Font(R.font.oswald))),
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
      ),
      keyboardActions = KeyboardActions(
        onNext = {
          keyboardController?.hide()
        }
      ),
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 8.dp)
    )

    Spacer(
      modifier = Modifier.height(4.dp)
    )

    TextField(
      value = newPassword,
      onValueChange = {
        newPassword = it
        isPasswordValid = it.length >= 8
      },
      label = {
        Text(
          text = "NEW PASSWORD",
          style = TextStyle(
            fontFamily = FontFamily(Font(R.font.oswald))
          )
        )
      },
      visualTransformation = PasswordVisualTransformation(),
      textStyle = TextStyle.Default.copy(fontFamily = FontFamily(Font(R.font.oswald))),
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
      ),
      keyboardActions = KeyboardActions(
        onNext = {
          keyboardController?.hide()
        }
      ),
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 8.dp)
    )

    Spacer(
      modifier = Modifier.height(4.dp)
    )

    TextField(
      value = confirmNewPassword,
      onValueChange = { confirmNewPassword = it },
      label = {
        Text(
          text = "CONFIRM NEW PASSWORD",
          style = TextStyle(
            fontFamily = FontFamily(Font(R.font.oswald))
          )
        )
      },
      visualTransformation = PasswordVisualTransformation(),
      textStyle = TextStyle.Default.copy(fontFamily = FontFamily(Font(R.font.oswald))),
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
      ),
      keyboardActions = KeyboardActions(
        onNext = {
          keyboardController?.hide()
        }
      ),
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 8.dp)
    )

    Spacer(
      modifier = Modifier.height(4.dp)
    )

    Button(
      onClick = { Log.d("Click", "Confirm Reset") },
      enabled = passwordsMatch && allFieldsNotEmpty && isPasswordValid,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
      Text(
        text = "RESET",
        style = TextStyle(
          fontFamily = FontFamily(Font(R.font.oswald))
        )
      )
    }

    TextButton(
      onClick = { navController.navigate(NavRoutes.Login.route) },
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
    ) {
      Text(
        text = "Back to Login",
        style = TextStyle(
          fontFamily = FontFamily(Font(R.font.corm)),
          fontWeight = FontWeight.ExtraBold
        )
      )
    }

    TextButton(
      onClick = { Log.d("Click", "Contact Service") },
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
    ) {
      Text(
        text = "Contact Service",
        style = TextStyle(
          fontFamily = FontFamily(Font(R.font.corm)),
          fontWeight = FontWeight.ExtraBold
        )
      )
    }
  }
}