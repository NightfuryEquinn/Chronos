package com.example.chronos.realm.realmclass

object UserSession {
  // Compulsory session variables
  var sessionToken: String? = null
  var sessionUsername: String? = null
  var sessionEmail: String? = null
  var sessionPassword: String? = null

  // Optional session variables
  var sessionSelectedDate: String? = null
  var sessionFilterQuery: Boolean? = false

  var sessionEditTask: String? = null
  var sessionEditTaskTitle: String? = null
  var sessionEditTaskDescription: String? = null
}