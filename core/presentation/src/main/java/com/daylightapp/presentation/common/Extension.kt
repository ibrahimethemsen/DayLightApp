package com.daylightapp.presentation.common

import android.view.View
import android.widget.ImageView
import coil.load
import coil.size.Scale

infix fun View.isVisibility(visible : Boolean){
    if (visible) this.visibility = View.VISIBLE else this.visibility = View.GONE
}

infix fun View.nullVisibility(visible : Any?){
    if (visible != null) this.visibility = View.VISIBLE else this.visibility = View.GONE
}

fun ImageView.loadImage(imageId: String) {
    val idToUrl = "http://openweathermap.org/img/wn/${imageId}@2x.png"
    load(idToUrl) {
        scale(Scale.FILL)
    }
}
