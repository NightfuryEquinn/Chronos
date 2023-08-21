package com.example.chronos.ui.pages

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.chronos.R
import com.example.chronos.realm.realmclass.Chron
import com.example.chronos.ui.navigations.NavRoutes
import com.example.chronos.ui.viewmodels.RegisterVM
import com.example.chronos.validation.isValidEmail

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(navController: NavHostController, registerVM: RegisterVM = viewModel()) {
  // State variables
  var username by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var confirmPassword by remember { mutableStateOf("") }

  // Access keyboard
  val keyboardController = LocalSoftwareKeyboardController.current

  // Validations
  val passwordsMatch = password == confirmPassword
  val allFieldsNotEmpty = username.isNotBlank() && email.isNotBlank() && confirmPassword.isNotBlank() && email.isNotBlank()

  var isEmailValid by remember { mutableStateOf(false) }
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
      value = username,
      onValueChange = { username = it },
      label = {
        Text(
          text = "USERNAME",
          style = TextStyle(fontFamily = FontFamily(Font(R.font.oswald))
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
      value = password,
      onValueChange = {
        password = it
        isPasswordValid = it.length >= 8
      },
      label = {
        Text(
          text = "PASSWORD",
          style = TextStyle(fontFamily = FontFamily(Font(R.font.oswald))
          )
        )
      },
      visualTransformation = PasswordVisualTransformation(),
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
      value = confirmPassword,
      onValueChange = { confirmPassword = it },
      label = {
        Text(
          text = "CONFIRM PASSWORD",
          style = TextStyle(fontFamily = FontFamily(Font(R.font.oswald))
          )
        )
      },
      visualTransformation = PasswordVisualTransformation(),
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
      value = email,
      onValueChange = {
        email = it
        isEmailValid = it.isValidEmail()
      },
      label = {
        Text(
          text = "EMAIL ADDRESS",
          style = TextStyle(fontFamily = FontFamily(Font(R.font.oswald))
          )
        )
      },
      textStyle = TextStyle.Default.copy(fontFamily = FontFamily(Font(R.font.oswald))),
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Email,
        imeAction = ImeAction.Done
      ),
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 8.dp)
    )

    Spacer(
      modifier = Modifier.height(16.dp)
    )

    Button(
      onClick = {
        val newChron = Chron().apply {
          chronUsername = username
          chronEmail = email
          chronPassword = password
        }
        registerVM.insertChron(newChron)

        navController.navigate(NavRoutes.Login.route)
      },
      enabled = passwordsMatch && allFieldsNotEmpty && isEmailValid && isPasswordValid,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
      Text(
        text = "REGISTER",
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
        text = "Existing User?",
        style = TextStyle(
          fontFamily = FontFamily(Font(R.font.corm)),
          fontWeight = FontWeight.ExtraBold
        )
      )
    }
  }
}