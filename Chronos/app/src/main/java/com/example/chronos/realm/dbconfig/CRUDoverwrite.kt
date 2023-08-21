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
  override fun filterData(email: String): List<Chron> {
    return realm.query<Chron>("chronEmail = $0", email).find()
  }

  // Insert new chron data
  override suspend fun insertChron(chron: Chron) {
    realm.write {
      copyToRealm(chron)
    }
  }

  // Update existing chron data
  override suspend fun updateChron(email: String, password: String) {
    realm.write {
      val theChron = this.query<Chron>("chronEmail = $0", email).first().find()
      theChron?.chronPassword = password
    }
  }

  // Delete existing chron data
  override suspend fun deleteChron(id: ObjectId) {
    TODO()
  }
}