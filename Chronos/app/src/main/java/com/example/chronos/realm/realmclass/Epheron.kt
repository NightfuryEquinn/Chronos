package com.example.chronos.realm.realmclass

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Epheron : RealmObject {
  @PrimaryKey
  var _epheron_id : ObjectId = ObjectId()

  var epheron_isComplete : Boolean = false
  var epheron_startDate : String = ""
  var epheron_endDate : String = ""
  var epheron_duration : String = ""
  var epheron_description : String = ""
  var chron_id : Chron? = null
}