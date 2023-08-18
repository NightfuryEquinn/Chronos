package com.example.chronos.realm.realmclass

import android.annotation.SuppressLint
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.text.SimpleDateFormat
import java.util.Date

class Chron : RealmObject {
  @PrimaryKey
  var _chron_id : ObjectId = ObjectId()
  var username : String = ""
  var email : String = ""
  var password : String = ""

  @SuppressLint("SimpleDateFormat")
  var registerDate : String = SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( Date() )
}