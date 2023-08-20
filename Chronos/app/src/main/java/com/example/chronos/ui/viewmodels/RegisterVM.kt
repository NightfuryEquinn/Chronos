package com.example.chronos.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chronos.realm.dbconfig.CRUDoverwrite
import com.example.chronos.realm.dbconfig.Connection
import com.example.chronos.realm.realmclass.Chron
import kotlinx.coroutines.launch

class RegisterVM: ViewModel() {
  // Insert new chron
  fun insertChron(newChron: Chron) {
    viewModelScope.launch {
      val repo = CRUDoverwrite(Connection.connectDB())
      repo.insertChron(newChron)
    }

    Log.d("Chron", newChron.toString())
  }
}