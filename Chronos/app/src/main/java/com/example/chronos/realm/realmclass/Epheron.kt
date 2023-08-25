package com.example.chronos.realm.realmclass

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Epheron : RealmObject {
  @PrimaryKey
  var _epheron_id : ObjectId = ObjectId()

  var epheronTitle: String = ""
  var epheronIsComplete : Boolean = false
  var epheronStart : String = ""
  var epheronEnd : String = ""
  var epheronDuration : String = ""
  var epheronDescription : String = ""
  var epheronPriority: String = ""

  var chronId : String? = null
}