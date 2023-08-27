package com.example.chronos.realm.dbconfig

import com.example.chronos.realm.realmclass.Chron
import com.example.chronos.realm.realmclass.Epheron
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import org.mongodb.kbson.BsonObjectId

class CRUDoverwrite(private val realm: Realm) : CRUDops {
  // For Chron
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

  // Update existing chron password with email
  override suspend fun updateChronPassword(email: String, password: String) {
    realm.write {
      val theChron = this.query<Chron>("chronEmail = $0", email).first().find()

      theChron?.chronPassword = password
    }
  }

  // Update existing chron profile
  override suspend fun updateChronProfile(chronId: String, username: String, email: String, password: String) {
    realm.write {
      val theChron = this.query<Chron>("_chron_id = $0", BsonObjectId(chronId)).first().find()

      theChron?.chronUsername = username
      theChron?.chronEmail = email
      theChron?.chronPassword = password
    }
  }

  // For Epheron
  // Fetch all epheron data related to chron
  override fun fetchEpheron(chronId: String): List<Epheron> {
    return realm.query<Epheron>("chronId = $0", chronId).find()
  }

  // Insert new epheron data related to user session
  override suspend fun insertEpheron(epheron: Epheron) {
    realm.write {
      copyToRealm(epheron)
    }
  }

  // Update existing epheron data
  override suspend fun updateEpheron(
    epheronId: String,
    title: String,
    description: String,
    startDate: String,
    endDate: String,
    duration: String,
    priority: String,
    isCompleted: Boolean
  ) {
    realm.write {
      val theEpheron = this.query<Epheron>("_epheron_id = $0", BsonObjectId(epheronId)).first().find()

      theEpheron?.epheronTitle = title
      theEpheron?.epheronDescription = description
      theEpheron?.epheronStart = startDate
      theEpheron?.epheronEnd = endDate
      theEpheron?.epheronDuration = duration
      theEpheron?.epheronPriority = priority
      theEpheron?.epheronIsComplete = isCompleted
    }
  }

  // Delete existing selected epheron data
  override suspend fun deleteEpheron(epheronId: String) {
    realm.write {
      val theEpheron = this.query<Epheron>("_epheron_id = $0", BsonObjectId(epheronId)).first().find()

      if (theEpheron != null) {
        delete(theEpheron)
      }
    }
  }
}