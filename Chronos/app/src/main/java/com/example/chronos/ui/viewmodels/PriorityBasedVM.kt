package com.example.chronos.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.chronos.realm.dbconfig.CRUDoverwrite
import com.example.chronos.realm.dbconfig.Connection
import com.example.chronos.realm.realmclass.Epheron
import com.example.chronos.realm.realmclass.UserSession

class PriorityBasedVM: ViewModel() {
  private val realm = Connection.connectDB()
  private val repo = CRUDoverwrite(realm)
  private val epheronData = UserSession.sessionToken?.let { repo.fetchEpheron(it) }

  fun parseThroughPriorityBased(): String? {
    return UserSession.sessionSelectedDate
  }

  fun fetchListOfHigh(): List<Epheron> {
    val listOfHigh = epheronData?.filter { epheron ->
      epheron.epheronPriority == "HIGH"
    } ?: emptyList()

    return listOfHigh
  }

  fun fetchListOfMedium(): List<Epheron> {
    val listOfMedium = epheronData?.filter { epheron ->
      epheron.epheronPriority == "MEDIUM"
    } ?: emptyList()

    return listOfMedium
  }

  fun fetchListOfLow(): List<Epheron> {
    val listOfLow = epheronData?.filter { epheron ->
      epheron.epheronPriority == "LOW"
    } ?: emptyList()

    return listOfLow
  }

  fun fetchListOfComplete(): List<Epheron> {
    val listOfComplete = epheronData?.filter { epheron ->
      epheron.epheronIsComplete
    } ?: emptyList()

    return listOfComplete
  }
}