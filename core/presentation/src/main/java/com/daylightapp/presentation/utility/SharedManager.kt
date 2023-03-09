package com.daylightapp.presentation.utility

import android.content.Context
import android.content.Context.MODE_PRIVATE

class SharedManager(context : Context) {
    private val sharedPreferences = context.getSharedPreferences(SHARED_PREF,MODE_PRIVATE)

    fun setSharedPreference(key : String, putValue : String){
        sharedPreferences.edit().putString(key, putValue).apply()
    }
    fun getSharedPreference(key : String,defValue : String):String?{
        return sharedPreferences.getString(key, defValue)
    }

    companion object{
        private const val SHARED_PREF = "shared"
    }
}