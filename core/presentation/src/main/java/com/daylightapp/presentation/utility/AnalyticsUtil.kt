package com.daylightapp.presentation.utility

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

object AnalyticsUtil {

    private val bundle = Bundle()

    fun eventLog(analytics: FirebaseAnalytics,nameValue : String){
        analytics.logEvent(nameValue, bundle)
        bundle.clear()
    }

    fun putBundleString(key : String,value : String){
        bundle.putString(key,value)
    }
}