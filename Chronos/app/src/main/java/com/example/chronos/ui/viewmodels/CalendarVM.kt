package com.example.chronos.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.chronos.realm.realmclass.UserSession

class CalendarVM: ViewModel() {
  fun parseToTimeBased(date: String) {
    UserSession.sessionSelectedDate = date
  }
}