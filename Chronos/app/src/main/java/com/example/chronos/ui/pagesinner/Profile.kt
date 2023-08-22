package com.example.chronos.ui.pagesinner

import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@Composable
fun ProfilePage() {
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
      text = "PROFILE",
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
          .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
          .background(Color(0xFFE4DDD5))
          .fillMaxWidth()
      ) {
        Text(
          text = "Username: ",
          style = TextStyle(
            fontFamily = FontFamily(Font(R.font.corm))
          ),
          modifier = Modifier
            .padding(16.dp)
        )

        Text(
          text = "Email Address: ",
          style = TextStyle(
            fontFamily = FontFamily(Font(R.font.corm))
          ),
          modifier = Modifier
            .padding(16.dp)
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
            Log.d("Chron", "Log Out")
          },
          modifier = Modifier
            .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
            .background(Color(0xFFE4DDD5))
        ) {
          Icon(
            imageVector = Icons.Filled.ExitToApp,
            contentDescription = "Log Out",
          )
        }
      }

      Box(
        modifier = Modifier
          .padding(16.dp)
      ) {
        IconButton(
          onClick = {
            Log.d("Chron", "Edit Profile")
          },
          modifier = Modifier
            .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
            .background(Color(0xFFE4DDD5))
        ) {
          Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "Edit Profile",
          )
        }
      }
    }
  }
}