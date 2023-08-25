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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.chronos.realm.realmclass.UserSession
import com.example.chronos.ui.navigations.ExtraRoutes
import com.example.chronos.ui.viewmodels.ProfileVM

@Composable
fun ProfilePage(navController: NavHostController, profileVM: ProfileVM = viewModel()) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFF100C09)),
    horizontalAlignment = Alignment.Start
  ) {
    Image(
      painter = painterResource(id = R.drawable.logo),
      contentDescription = "Chronos_Logo",
      modifier = Modifier
        .padding(top = 16.dp)
        .size(150.dp)
        .align(Alignment.CenterHorizontally)
    )

    Text(
      text = "PROFILE",
      style = TextStyle(
        color = Color(0xFFA49A8E),
        fontSize = 36.sp,
        fontFamily = FontFamily(Font(R.font.oswald))
      ),
      modifier = Modifier
        .padding(start = 24.dp, bottom = 16.dp)
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
          text = "Username: ${UserSession.sessionUsername}",
          style = TextStyle(
            fontFamily = FontFamily(Font(R.font.corm))
          ),
          modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
        )

        Text(
          text = "Email Address: ${UserSession.sessionEmail}",
          style = TextStyle(
            fontFamily = FontFamily(Font(R.font.corm))
          ),
          modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
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
            profileVM.clearAndLogOut(navController = navController)
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
            navController.navigate(ExtraRoutes.UpdateProfile.route)
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

    Text(
      text = "SETTING",
      style = TextStyle(
        color = Color(0xFFA49A8E),
        fontSize = 36.sp,
        fontFamily = FontFamily(Font(R.font.oswald))
      ),
      modifier = Modifier
        .padding(start = 24.dp, bottom = 16.dp)
    )

    Box(
      modifier = Modifier
        .padding(horizontal = 16.dp)
    ) {
      Box(
        modifier = Modifier
          .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
          .background(Color(0xFFE4DDD5))
          .fillMaxWidth()
      ) {
        Row(
          modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
          horizontalArrangement = Arrangement.Center
        ) {
          Column(
            modifier = Modifier
              .padding(4.dp)
          ) {
            Text(
              text = "Toggle Filter",
              style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.oswald))
              ),
              modifier = Modifier
                .padding(4.dp)
            )

            Text(
              text = "Arrange tasks based on time or priority",
              style = TextStyle(
                fontFamily = FontFamily(Font(R.font.corm))
              ),
              modifier = Modifier
                .padding(4.dp)
            )
          }

          Switch(
            checked = UserSession.sessionFilterQuery == true,
            onCheckedChange = {
              UserSession.sessionFilterQuery = true
              Log.d("Chron", UserSession.sessionFilterQuery.toString())
            },
            modifier = Modifier.padding(top = 4.dp, end = 4.dp, bottom = 4.dp, start = 12.dp)
          )
        }
      }
    }
  }
}