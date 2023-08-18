package com.example.chronos.realm.realmclass

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Epheron : RealmObject {
  @PrimaryKey
  var _epheron_id : ObjectId = ObjectId()
  var isComplete : Boolean = false
  var startDate : String = ""
  var endDate : String = ""
  var duration : String = ""
  var description : String = ""
  var chron_id : Chron? = null
}