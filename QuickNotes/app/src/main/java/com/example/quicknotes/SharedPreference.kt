package com.example.quicknotes

import android.content.Context
import android.content.SharedPreferences

object SharedPreference {

    const val PROFILE_NAME = "profile_name"
    const val SHARED_PREFERENCE = "notes_pref"

    fun savePrefString(key:String, value:String, sharedPreferences: SharedPreferences){
        with(
            sharedPreferences.edit()
        ){
            putString(key, value)
            apply()
        }
    }

   fun getPrefString(key:String, sharedPreferences: SharedPreferences):String {
       return sharedPreferences.getString(key, "") ?: ""
   }

    fun instanceSharedPref(context: Context):SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
    }
}