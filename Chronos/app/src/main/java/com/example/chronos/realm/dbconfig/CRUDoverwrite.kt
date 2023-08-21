package com.example.chronos.realm.dbconfig

import com.example.chronos.realm.realmclass.Chron
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import org.mongodb.kbson.ObjectId


class CRUDoverwrite(private val realm: Realm) : CRUDops {
  // Fetch all chron data
  override fun fetchData(): List<Chron> {
    return realm.query<Chron>().find()
  }

  // Filter through specific chron data
  override fun filterData(name: String): List<Chron> {
    TODO()
  }

  // Insert new chron data
  override suspend fun insertChron(chron: Chron) {
    realm.write {
      copyToRealm(chron)
    }
  }

  // Update existing chron data
  override suspend fun updateChron(chron: Chron) {
    TODO()
  }

  // Delete existing chron data
  override suspend fun deleteChron(id: ObjectId) {
    TODO()
  }
}