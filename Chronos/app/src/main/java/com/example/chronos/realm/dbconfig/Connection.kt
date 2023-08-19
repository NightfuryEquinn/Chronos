package com.example.chronos.realm.dbconfig

import com.example.chronos.realm.realmclass.Chron
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object Connection {
  // Connect Realm DB
  fun connectDB() : Realm {
    val config = RealmConfiguration.Builder(setOf(Chron::class)).build()

    return Realm.open(config)
  }
}