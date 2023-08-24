package com.example.chronos.ui.pagesinner

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.chronos.R
import com.example.chronos.realm.realmclass.Epheron
import com.example.chronos.realm.realmclass.UserSession
import com.example.chronos.ui.viewmodels.CreateVM
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import com.maxkeppeler.sheets.list.ListDialog
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CreatePage(createVM: CreateVM = viewModel()) {
  // State variables
  var title by remember { mutableStateOf("") }
  var description by remember { mutableStateOf("") }
  var startDate by remember { mutableStateOf("") }
  var endDate by remember { mutableStateOf("") }
  var priority by remember { mutableStateOf("") }

  // Access keyboard
  val keyboardController = LocalSoftwareKeyboardController.current

  // Validations
  val allFieldsNotEmpty = title.isNotBlank() && description.isNotBlank() && startDate.isNotBlank() && endDate.isNotBlank() && priority.isNotBlank()

  // Calendar date time dialog
  val startDateTimeState = rememberUseCaseState()
  val endDateTimeState = rememberUseCaseState()

  var displayStartDate by remember { mutableStateOf("") }
  var displayEndDate by remember { mutableStateOf ("") }

  var calculateStartDate by remember { mutableStateOf<LocalDateTime?>(null) }
  var calculateEndDate by remember { mutableStateOf<LocalDateTime?>(null) }

  DateTimeDialog(
    state = startDateTimeState,
    selection = DateTimeSelection.DateTime { newDateTime ->
      calculateStartDate = newDateTime
      startDate = newDateTime.toString()
      displayStartDate = "${newDateTime.year}-${newDateTime.monthValue}-${newDateTime.dayOfMonth} ${newDateTime.hour}:${newDateTime.minute}"
    }
  )

  DateTimeDialog(
    state = endDateTimeState,
    selection = DateTimeSelection.DateTime { newDateTime ->
      calculateEndDate = newDateTime
      endDate = newDateTime.toString()
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
      priority = option.titleText
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

    Text(
      text = "CREATING TASK",
      style = TextStyle(
        color = Color(0xFFA49A8E),
        fontSize = 36.sp,
        fontFamily = FontFamily(Font(R.font.oswald))
      ),
      modifier = Modifier
        .padding(bottom = 8.dp)
    )

    TextField(
      value = title,
      onValueChange = { title = it },
      label = {
        Text(
          text = "TITLE",
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
      value = description,
      onValueChange = { description = it },
      label = {
        Text(
          text = "DESCRIPTION",
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

    Button(
      onClick = {
        val newEpheron = Epheron().apply {
          epheronTitle = title
          epheronStart = startDate
          epheronEnd = endDate
          epheronDuration = createVM.calculateDuration(calculateStartDate, calculateEndDate)
          epheronDescription = description
          epheronPriority = priority
          chronId = UserSession.sessionToken
        }
        createVM.insertEpheron(newEpheron)

        Log.d("Epheron", "Save, ${newEpheron.epheronDuration}")

        title = ""
        displayStartDate = ""
        displayEndDate = ""
        description = ""
        displayOption = ""
      },
      enabled = allFieldsNotEmpty,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 96.dp, vertical = 24.dp)
    ) {
      Text(
        text = "SAVE",
        style = TextStyle(
          fontSize = 16.sp,
          fontFamily = FontFamily(Font(R.font.oswald))
        )
      )
    }
  }
}