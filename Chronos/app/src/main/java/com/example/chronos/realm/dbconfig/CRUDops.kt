package com.example.chronos.realm.dbconfig

import com.example.chronos.realm.realmclass.Chron
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface CRUDops {
  fun fetchData() : List<Chron>
  fun filterData(email: String) : List<Chron>

  suspend fun insertChron(chron: Chron)
  suspend fun updateChronPassword(email: String, password: String)
  suspend fun updateChronProfile(chronId: String, username: String, email: String, password: String)
  suspend fun deleteChron(id: ObjectId)
}