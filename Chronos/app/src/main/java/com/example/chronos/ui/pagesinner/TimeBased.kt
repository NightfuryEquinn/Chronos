package com.example.chronos.ui.pagesinner

import android.util.Log
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.chronos.R
import com.example.chronos.ui.navigations.InnerNavRoutes
import com.example.chronos.ui.task.PriorityTaskBlockComponent
import com.example.chronos.ui.task.TimeTaskBlockComponent
import com.example.chronos.ui.viewmodels.CalendarVM
import com.example.chronos.ui.viewmodels.TimeBasedVM

@Composable
fun TimeBasedPage(navController: NavHostController, timeBasedVM: TimeBasedVM = viewModel()) {
  val selectedDate = timeBasedVM.parseThroughTimeBased()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFF100C09))
      .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(
      modifier = Modifier
        .padding(vertical = 16.dp)
    ) {
      Column(
        modifier = Modifier
          .padding(top = 24.dp, end = 24.dp, bottom = 8.dp, start = 16.dp),
        verticalArrangement = Arrangement.Center
      ) {
        if (selectedDate != null) {
          Text(
            text = selectedDate,
            style = TextStyle(
              color = Color(0xFFA49A8E),
              fontSize = 24.sp,
              fontFamily = FontFamily(Font(R.font.oswald))
            ),
            modifier = Modifier
              .padding(bottom = 12.dp)
          )
        }

        Text(
          text = "Make every seconds count",
          style = TextStyle(
            color = Color(0xFFA49A8E),
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.corm))
          )
        )
      }

      Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Chronos_Logo",
        modifier = Modifier
          .size(125.dp)
      )
    }
    
    Box(
      modifier = Modifier
        .padding(horizontal = 24.dp)
    ) {
      Text(
        text = "PENDING",
        style = TextStyle(
          color = Color(0xFFE4DDD5),
          fontSize = 20.sp,
          fontFamily = FontFamily(Font(R.font.oswald))
        ),
        modifier = Modifier
          .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
          .background(Color(0xFF504848))
          .fillMaxWidth()
          .padding(horizontal = 12.dp, vertical = 4.dp)
      )
    }
    
    TimeTaskBlockComponent()

    Box(
      modifier = Modifier
        .padding(top = 16.dp, end = 24.dp, start = 24.dp)
    ) {
      Text(
        text = "COMPLETED",
        style = TextStyle(
          color = Color(0xFFE4DDD5),
          fontSize = 20.sp,
          fontFamily = FontFamily(Font(R.font.oswald))
        ),
        modifier = Modifier
          .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
          .background(Color(0xFF504848))
          .fillMaxWidth()
          .padding(horizontal = 12.dp, vertical = 4.dp)
      )
    }

    PriorityTaskBlockComponent()

    Box(
      modifier = Modifier
        .padding(horizontal = 24.dp, vertical = 36.dp)
        .fillMaxWidth()
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth(),
        horizontalAlignment = Alignment.End
      ) {
        IconButton(
          onClick = {
            navController.navigate(InnerNavRoutes.Calendar.route)

            Log.d("Chron", "Back")
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
        .padding(bottom = 72.dp)
    )
  }
}