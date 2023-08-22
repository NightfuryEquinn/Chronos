package com.example.chronos.realm.realmclass

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Epheron : RealmObject {
  @PrimaryKey
  var _epheron_id : ObjectId = ObjectId()

  var epheron_title: String = ""
  var epheron_isComplete : Boolean = false
  var epheron_start : String = ""
  var epheron_end : String = ""
  var epheron_duration : String = ""
  var epheron_description : String = ""
  var epheron_priority: String = ""

  var chron_id : Chron? = null
}