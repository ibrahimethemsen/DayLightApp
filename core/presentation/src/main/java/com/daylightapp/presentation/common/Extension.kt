package com.daylightapp.presentation.common

import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.load
import coil.size.Scale
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

infix fun View.isVisibility(visible: Boolean) {
    if (visible) this.visibility = View.VISIBLE else this.visibility = View.GONE
}

fun <T> LifecycleOwner.observeIfNotNull(liveData: LiveData<T>, function: (T) -> Unit) {
    liveData.observe(this) {
        it?.let {
            function.invoke(it)
        }
    }
}

infix fun View.setVisibility(visible: Any?) {
    if (visible != null) this.visibility = View.VISIBLE else this.visibility = View.GONE
}

fun ImageView.loadImage(imageId: String) {
    val idToUrl = "http://openweathermap.org/img/wn/${imageId}@2x.png"
    load(idToUrl) {
        scale(Scale.FILL)
    }
}

fun ImageView.loadRemote(url: String) {
    load(url) {
        scale(Scale.FILL)
    }
}

inline fun <reified T> FirebaseRemoteConfig.fetchToLiveData(
    key: String,
    gson: Gson,
    liveData: MutableLiveData<T>
) {
    this.fetchAndActivate().addOnCompleteListener {
        if (it.isSuccessful) {
            val keyString = this.getString(key)
            val type = object : TypeToken<T>() {}.type
            val jsonModel = gson.fromJson<T>(keyString, type)
            liveData.postValue(jsonModel)
        }
    }
}
