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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.chronos.R
import com.example.chronos.ui.task.PriorityTaskBlockComponent
import com.example.chronos.ui.task.TimeTaskBlockComponent

@Composable
fun TimeBasedPage() {
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
        Text(
          text = "19 SEPTEMBER 2023",
          style = TextStyle(
            color = Color(0xFFA49A8E),
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.oswald))
          ),
          modifier = Modifier
            .padding(bottom = 12.dp)
        )

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
  }
}