package com.example.chronos.realm.dbconfig

import com.example.chronos.realm.realmclass.Chron
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface CRUDops {
  fun fetchData() : List<Chron>
  fun filterData(name: String) : List<Chron>

  suspend fun insertChron(chron: Chron)
  suspend fun updateChron(chron: Chron)
  suspend fun deleteChron(id: ObjectId)
}