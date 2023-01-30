package com.daylightapp.presentation.common

import android.view.View
import android.widget.ImageView
import java.text.SimpleDateFormat
import coil.load
import coil.size.Scale
import java.util.*

infix fun View.isVisibility(visible : Boolean){
    if (visible) this.visibility = View.VISIBLE else this.visibility = View.GONE
}

infix fun View.nullVisibility(visible : Any?){
    if (visible != null) this.visibility = View.VISIBLE else this.visibility = View.GONE
}

fun Int.toDateFormat(dateFormat: String): String {
    val longToDate = Date(this.toLong() * 1000L)
    val sdf = SimpleDateFormat(dateFormat, Locale("tr"))
    sdf.timeZone = TimeZone.getTimeZone("Asia/Istanbul")
    return sdf.format(longToDate)
}

fun ImageView.loadImage(imageId: String) {
    val idToUrl = "http://openweathermap.org/img/wn/${imageId}@2x.png"
    load(idToUrl) {
        scale(Scale.FILL)
    }
}
