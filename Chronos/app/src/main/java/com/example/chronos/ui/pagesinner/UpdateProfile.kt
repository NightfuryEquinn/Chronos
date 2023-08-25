package com.example.chronos.ui.pagesinner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.chronos.R
import com.example.chronos.realm.realmclass.UserSession
import com.example.chronos.ui.navigations.InnerNavRoutes
import com.example.chronos.ui.viewmodels.ProfileVM
import com.example.chronos.validation.isValidEmail

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun UpdateProfilePage(navController: NavHostController, profileVM: ProfileVM = viewModel()) {
  // State variables
  var updateEmail by remember { mutableStateOf("${UserSession.sessionEmail}") }
  var updateUsername by remember { mutableStateOf("${UserSession.sessionUsername}") }
  var updatePassword by remember { mutableStateOf("") }
  var updateConfirmPassword by remember { mutableStateOf("") }

  // Access keyboard
  val keyboardController = LocalSoftwareKeyboardController.current

  // Validations
  val passwordsMatch = updatePassword == updateConfirmPassword
  val allFieldsNotEmpty = updateUsername.isNotBlank() && updateEmail.isNotBlank() && updatePassword.isNotBlank() && updateConfirmPassword.isNotBlank()

  var isEmailValid by remember { mutableStateOf(false) }
  var isPasswordValid by remember { mutableStateOf(false) }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFF100C09))
      .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      painter = painterResource(id = R.drawable.logo),
      contentDescription = "Chronos_Logo",
      modifier = Modifier
        .padding(top = 16.dp)
        .size(150.dp)
    )

    Text(
      text = "UPDATING PROFILE",
      style = TextStyle(
        color = Color(0xFFA49A8E),
        fontSize = 36.sp,
        fontFamily = FontFamily(Font(R.font.oswald))
      ),
      modifier = Modifier
        .padding(bottom = 16.dp)
    )

    Box(
      modifier = Modifier
        .padding(horizontal = 24.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
      ) {
        TextField(
          value = updateUsername,
          onValueChange = { updateUsername = it },
          label = {
            Text(
              text = "UPDATE USERNAME",
              style = TextStyle(
                fontFamily = FontFamily(Font(R.font.corm))
              )
            )
          },
          textStyle = TextStyle.Default.copy(fontFamily = FontFamily(Font(R.font.corm))),
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

        TextField(
          value = updateEmail,
          onValueChange = {
            updateEmail = it
            isEmailValid = it.isValidEmail()
          },
          label = {
            Text(
              text = "UPDATE EMAIL ADDRESS",
              style = TextStyle(
                fontFamily = FontFamily(Font(R.font.corm))
              )
            )
          },
          textStyle = TextStyle.Default.copy(fontFamily = FontFamily(Font(R.font.corm))),
          keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
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

        TextField(
          value = updatePassword,
          onValueChange = {
            updatePassword = it
            isPasswordValid = it.length >= 8
          },
          label = {
            Text(
              text = "UPDATE PASSWORD",
              style = TextStyle(
                fontFamily = FontFamily(Font(R.font.corm))
              )
            )
          },
          visualTransformation = PasswordVisualTransformation(),
          textStyle = TextStyle.Default.copy(fontFamily = FontFamily(Font(R.font.corm))),
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

        TextField(
          value = updateConfirmPassword,
          onValueChange = { updateConfirmPassword = it },
          label = {
            Text(
              text = "CONFIRM UPDATE PASSWORD",
              style = TextStyle(
                fontFamily = FontFamily(Font(R.font.corm))
              )
            )
          },
          visualTransformation = PasswordVisualTransformation(),
          textStyle = TextStyle.Default.copy(fontFamily = FontFamily(Font(R.font.corm))),
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
      }
    }

    Row(
      modifier = Modifier
        .padding(horizontal = 48.dp, vertical = 16.dp)
        .fillMaxWidth(),
      horizontalArrangement = Arrangement.Center
    ) {
      Box(
        modifier = Modifier
          .padding(16.dp)
      ) {
        IconButton(
          onClick = {
            navController.navigate(InnerNavRoutes.Profile.route)
          },
          modifier = Modifier
            .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
            .background(Color(0xFFE4DDD5))
        ) {
          Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back",
          )
        }
      }

      Box(
        modifier = Modifier
          .padding(16.dp)
      ) {
        IconButton(
          onClick = {
            profileVM.updateChronAndSession(updateUsername, updateEmail, updatePassword, navController)
          },
          enabled = passwordsMatch && allFieldsNotEmpty && isEmailValid && isPasswordValid,
          modifier = Modifier
            .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
            .background(Color(0xFFE4DDD5))
        ) {
          Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Save Profile",
          )
        }
      }
    }
  }
}