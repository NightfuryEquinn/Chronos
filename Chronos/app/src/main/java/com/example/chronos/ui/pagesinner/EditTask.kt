package com.example.chronos.ui.pagesinner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.chronos.R
import com.example.chronos.realm.realmclass.UserSession
import com.example.chronos.ui.navigations.InnerNavRoutes
import com.example.chronos.ui.viewmodels.EditTaskVM
import com.example.chronos.validation.compareDates
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import com.maxkeppeler.sheets.list.ListDialog
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection
import java.time.LocalDateTime

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EditTaskPage(navController: NavHostController, editTaskVM: EditTaskVM = viewModel()) {
  // State variables
  var updateTitle by remember { mutableStateOf("${UserSession.sessionEditTaskTitle}") }
  var updateDescription by remember { mutableStateOf("${UserSession.sessionEditTaskDescription}") }
  var updateStartDate by remember { mutableStateOf("") }
  var updateEndDate by remember { mutableStateOf("") }
  var updatePriority by remember { mutableStateOf("") }
  var updateIsCompleted by remember { mutableStateOf(false) }

  // Access keyboard
  val keyboardController = LocalSoftwareKeyboardController.current

  // Validations
  val allFieldsNotEmpty = updateTitle.isNotBlank() && updateDescription.isNotBlank() && updateStartDate.isNotBlank() && updateEndDate.isNotBlank() && updatePriority.isNotBlank()

  var checkStartDate by remember { mutableStateOf(LocalDateTime.now()) }
  var checkEndDate by remember { mutableStateOf(LocalDateTime.now()) }

  val validStartEndDate = compareDates(checkStartDate, checkEndDate)

  // Calendar date time dialog
  val startDateTimeState = rememberUseCaseState()
  val endDateTimeState = rememberUseCaseState()

  var displayStartDate by remember { mutableStateOf("") }
  var displayEndDate by remember { mutableStateOf ("") }

  DateTimeDialog(
    state = startDateTimeState,
    selection = DateTimeSelection.DateTime { newDateTime ->
      checkStartDate = newDateTime
      updateStartDate = newDateTime.toString()
      displayStartDate = "${newDateTime.year}-${newDateTime.monthValue}-${newDateTime.dayOfMonth} ${newDateTime.hour}:${newDateTime.minute}"
    }
  )

  DateTimeDialog(
    state = endDateTimeState,
    selection = DateTimeSelection.DateTime { newDateTime ->
      checkEndDate = newDateTime
      updateEndDate = newDateTime.toString()
      displayEndDate = "${newDateTime.year}-${newDateTime.monthValue}-${newDateTime.dayOfMonth} ${newDateTime.hour}:${newDateTime.minute}"
    }
  )

  // Option dialog for priority
  val listOptionState = rememberUseCaseState()

  var displayOption by remember { mutableStateOf("") }

  val options = listOf(
    ListOption(titleText = "LOW"),
    ListOption(titleText = "MEDIUM"),
    ListOption(titleText = "HIGH")
  )

  ListDialog(
    state = listOptionState,
    selection = ListSelection.Single(
      options = options
    ) { _, option ->
      updatePriority = option.titleText
      displayOption = option.titleText
    }
  )

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFF100C09)),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      painter = painterResource(id = R.drawable.logo),
      contentDescription = "Chronos_Logo",
      modifier = Modifier
        .padding(top = 16.dp)
        .size(150.dp)
    )

    Column(
      modifier = Modifier
        .fillMaxSize()
        .weight(1f)
        .background(Color(0xFF100C09))
        .verticalScroll(rememberScrollState()),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = "EDITING TASK",
        style = TextStyle(
          color = Color(0xFFA49A8E),
          fontSize = 36.sp,
          fontFamily = FontFamily(Font(R.font.oswald))
        ),
        modifier = Modifier
          .padding(bottom = 8.dp)
      )

      TextField(
        value = updateTitle,
        onValueChange = { updateTitle = it },
        label = {
          Text(
            text = "UPDATE TITLE",
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
        value = updateDescription,
        onValueChange = { updateDescription = it },
        label = {
          Text(
            text = "UPDATE DESCRIPTION",
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

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 24.dp, vertical = 8.dp)
      ) {
        Button(
          onClick = {
            startDateTimeState.show()
          },
          colors = ButtonDefaults.buttonColors(Color(0xFFE4DDD5)),
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, end = 4.dp, bottom = 8.dp)
            .weight(1f)
        ) {
          Text(
            text = if(displayStartDate == "") "START DATE AND TIME" else displayStartDate,
            style = TextStyle(
              color = Color(0xFF775A00),
              fontFamily = FontFamily(Font(R.font.corm)),
              textAlign = TextAlign.Center
            )
          )
        }

        Button(
          onClick = {
            endDateTimeState.show()
          },
          colors = ButtonDefaults.buttonColors(Color(0xFFE4DDD5)),
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp, start = 4.dp)
            .weight(1f)
        ) {
          Text(
            text = if(displayEndDate == "") "END DATE AND TIME" else displayEndDate,
            style = TextStyle(
              color = Color(0xFF775A00),
              fontFamily = FontFamily(Font(R.font.corm)),
              textAlign = TextAlign.Center
            )
          )
        }
      }

      Button(
        onClick = {
          listOptionState.show()
        },
        colors = ButtonDefaults.buttonColors(Color(0xFFE4DDD5)),
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 24.dp, vertical = 8.dp)
      ) {
        Text(
          text = if(displayOption == "") "PRIORITY LEVEL" else displayOption,
          style = TextStyle(
            color = Color(0xFF775A00),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.corm))
          )
        )
      }

      Row(
        modifier = Modifier
          .padding(horizontal = 24.dp, vertical = 8.dp)
          .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "TASK COMPLETED?",
          style = TextStyle(
            color = Color(0xFFA49A8E),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.oswald))
          )
        )

        Checkbox(
          checked = updateIsCompleted,
          onCheckedChange = {
            updateIsCompleted = it
          }
        )
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
              editTaskVM.deleteTask()

              navController.navigate(InnerNavRoutes.Calendar.route)
            },
            modifier = Modifier
              .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
              .background(Color(0xFFE4DDD5))
          ) {
            Icon(
              imageVector = Icons.Filled.Delete,
              contentDescription = "Delete",
            )
          }
        }

        Box(
          modifier = Modifier
            .padding(16.dp)
        ) {
          IconButton(
            onClick = {
              editTaskVM.updateTask(
                updateTitle,
                updateDescription,
                updateStartDate,
                updateEndDate,
                updatePriority,
                updateIsCompleted
              )

              navController.navigate(InnerNavRoutes.Calendar.route)
            },
            enabled = allFieldsNotEmpty && validStartEndDate,
            modifier = Modifier
              .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
              .background(Color(0xFFE4DDD5))
          ) {
            Icon(
              imageVector = Icons.Filled.Done,
              contentDescription = "Save",
            )
          }
        }

        Box(
          modifier = Modifier
            .padding(16.dp)
        ) {
          IconButton(
            onClick = {
              navController.navigate(InnerNavRoutes.Calendar.route)
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
      }

      Spacer(
        modifier = Modifier
          .padding(vertical = 40.dp)
      )
    }
  }
}