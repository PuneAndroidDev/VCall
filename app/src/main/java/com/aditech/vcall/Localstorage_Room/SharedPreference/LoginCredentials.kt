package com.co_vision.co_vision.Localstorage_Room.SharedPreference

import android.content.Context
import android.content.SharedPreferences


object LoginCredentials {

    const val LogPreference: String = "logpreference"
    private const  val TOKEN: String = "TOKEN"
    lateinit  var sharedPreferences: SharedPreferences

    fun Context.setToken(token:String){
        sharedPreferences = getSharedPreferences(LogPreference, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(TOKEN, token)
        editor.apply()
    }

    fun Context.logout(): Boolean {
        val preferences = getSharedPreferences(LogPreference, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
        return true
    }

    fun Context.init() {
        sharedPreferences = getSharedPreferences(LogPreference, Context.MODE_PRIVATE)
    }


}