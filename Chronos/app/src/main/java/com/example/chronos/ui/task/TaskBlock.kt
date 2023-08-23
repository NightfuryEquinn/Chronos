package com.example.chronos.ui.task

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chronos.R

@Composable
fun TimeTaskBlockComponent() {
  Box(
    modifier = Modifier
      .padding(horizontal = 24.dp, vertical = 4.dp)
  ) {
    Column(
      modifier = Modifier
        .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
        .background(Color(0xFFE4DDD5))
        .fillMaxWidth()
        .padding(vertical = 4.dp)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(
          modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
          Text(
            text = "Meeting with the Minister",
            style = TextStyle(
              fontSize = 20.sp,
              fontFamily = FontFamily(Font(R.font.oswald))
            ),
            modifier = Modifier
              .padding(bottom = 4.dp)
          )

          Text(
            text = "Time: 09:00 - 10:00",
            style = TextStyle(
              fontFamily = FontFamily(Font(R.font.corm))
            ),
            modifier = Modifier
              .padding(bottom = 4.dp)
          )

          Text(
            text = "Duration: 1 hr",
            style = TextStyle(
              fontFamily = FontFamily(Font(R.font.corm))
            ),
            modifier = Modifier
              .padding(bottom = 4.dp)
          )
        }

        IconButton(
          onClick = { Log.d("Chron", "Edit Task") },
          modifier = Modifier
            .padding(end = 16.dp)
        ) {
          Icon(
            imageVector = Icons.Rounded.Edit,
            contentDescription = "Edit",
          )
        }
      }

      Text(
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        style = TextStyle(
          fontFamily = FontFamily(Font(R.font.corm)),
          textAlign = TextAlign.Justify
        ),
        modifier = Modifier
          .padding(horizontal = 12.dp, vertical = 8.dp)
      )
    }
  }
}

@Composable
fun PriorityTaskBlockComponent() {
  Box(
    modifier = Modifier
      .padding(horizontal = 24.dp, vertical = 4.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
        .background(Color(0xFFE4DDD5))
        .fillMaxWidth()
        .padding(vertical = 2.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "Meeting with the Minister",
        style = TextStyle(
          fontSize = 20.sp,
          fontFamily = FontFamily(Font(R.font.oswald))
        ),
        modifier = Modifier
          .padding(horizontal = 12.dp)
      )

      IconButton(
        onClick = { Log.d("Chron", "Edit Task") },
        modifier = Modifier
          .padding(end = 16.dp)
      ) {
        Icon(
          imageVector = Icons.Rounded.Edit,
          contentDescription = "Edit",
        )
      }
    }
  }
}