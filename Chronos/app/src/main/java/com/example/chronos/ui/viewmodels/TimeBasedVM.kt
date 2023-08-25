package com.example.chronos.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chronos.realm.dbconfig.CRUDoverwrite
import com.example.chronos.realm.dbconfig.Connection
import com.example.chronos.realm.realmclass.Epheron
import com.example.chronos.realm.realmclass.UserSession
import kotlinx.datetime.toLocalDateTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class TimeBasedVM: ViewModel() {
  private val realm = Connection.connectDB()
  private val repo = CRUDoverwrite(realm)
  private val epheronData = UserSession.sessionToken?.let { repo.fetchEpheron(it) }

  fun parseThroughTimeBased(): String? {
    return UserSession.sessionSelectedDate
  }

  fun fetchListOfPending(): List<Epheron> {
    val dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH)
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")

    // The selected date from user
    val selectedDate = LocalDate.parse(UserSession.sessionSelectedDate, dateFormatter)

    val listOfPending = epheronData?.filter { epheron ->
      // The start and end date of epheron
      val selectedStartDate = LocalDateTime.parse(epheron.epheronStart, dateTimeFormatter).toLocalDate()
      val selectedEndDate = LocalDateTime.parse(epheron.epheronEnd, dateTimeFormatter).toLocalDate()

      !epheron.epheronIsComplete && selectedDate in selectedStartDate..selectedEndDate
    } ?: emptyList()

    return listOfPending
  }

  fun fetchListOfComplete(): List<Epheron> {
    val listOfComplete = epheronData?.filter { epheron ->
      epheron.epheronIsComplete
    } ?: emptyList()

    return listOfComplete
  }
}