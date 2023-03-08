package com.daylightapp.presentation.common

import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import coil.load
import coil.size.Scale

infix fun View.isVisibility(visible : Boolean){
    if (visible) this.visibility = View.VISIBLE else this.visibility = View.GONE
}
fun <T> LifecycleOwner.observeIfNotNull(liveData: LiveData<T>, function: (T) -> Unit) {
    liveData.observe(this) {
        it?.let {
            function.invoke(it)
        }
    }
}
infix fun View.setVisibility(visible : Any?){
    if (visible != null) this.visibility = View.VISIBLE else this.visibility = View.GONE
}

fun ImageView.loadImage(imageId: String) {
    val idToUrl = "http://openweathermap.org/img/wn/${imageId}@2x.png"
    load(idToUrl) {
        scale(Scale.FILL)
    }
}

fun ImageView.loadRemote(url : String){
    load(url){
        scale(Scale.FILL)
    }
}
