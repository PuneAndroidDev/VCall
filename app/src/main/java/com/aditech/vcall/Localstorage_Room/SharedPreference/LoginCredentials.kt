package com.co_vision.co_vision.Localstorage_Room.SharedPreference

import android.content.Context
import android.content.SharedPreferences


object LoginCredentials {

    const val LogPreference: String = "logpreference"
    private const  val TOKEN: String = "TOKEN"
    private const  val UID: String = "UID"
    private const  val Password: String = "PASSWORD"
    private const val FirstName: String = "firstname"
    lateinit  var sharedPreferences: SharedPreferences

    fun Context.setToken(token:String){
        sharedPreferences = getSharedPreferences(LogPreference, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(TOKEN, token)
        editor.apply()
    }

    fun Context.loginin(): Boolean {
        sharedPreferences = getSharedPreferences(LogPreference, Context.MODE_PRIVATE)
        val s = sharedPreferences.getString(UID, "NAN")
        val p = sharedPreferences.getString(Password, "NAN")
        if (s.equals("NAN")) {
            return false
        }
        if (p.equals("NAN")) {
            return false
        }
        return true
    }


    fun Context.logout(): Boolean {
        val preferences = getSharedPreferences(LogPreference, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
        return true
    }

    fun Context.setCredentials(
        uid: String
    ) {
        sharedPreferences = getSharedPreferences(LogPreference, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(UID, uid)
        editor.apply()
    }

    fun Context.init() {
        sharedPreferences = getSharedPreferences(LogPreference, Context.MODE_PRIVATE)
    }


}