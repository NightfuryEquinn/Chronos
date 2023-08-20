package com.example.chronos.realm.dbconfig

import com.example.chronos.realm.realmclass.Chron
import io.realm.kotlin.Realm
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

// Insert new chron to realm
class CRUDoverwrite(private val realm: Realm) : CRUDops {
  override fun fetchData(): Flow<List<Chron>> {
    TODO()
  }

  override fun filterData(name: String): Flow<List<Chron>> {
    TODO()
  }

  override suspend fun insertChron(chron: Chron) {
    realm.write {
      copyToRealm(chron)
    }
  }

  override suspend fun updateChron(chron: Chron) {
    TODO()
  }

  override suspend fun deleteChron(id: ObjectId) {
    TODO()
  }
}