package com.example.chronos.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.chronos.realm.realmclass.UserSession

class TimeBasedVM: ViewModel() {
  fun parseThroughTimeBased(): String? {
    return UserSession.sessionSelectedDate
  }
}