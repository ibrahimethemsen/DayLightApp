package com.daylightapp.domain.common

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun Int.toDateFormat(dateFormat: String): String {
    val longToDate = Date(this.toLong() * 1000L)
    val sdf = SimpleDateFormat(dateFormat, Locale("tr"))
    sdf.timeZone = TimeZone.getTimeZone("Asia/Istanbul")
    return sdf.format(longToDate)
}

fun Double.milToKmSpeed(): String {
    val windSpeed = this * 1.60934
    val decimalFormat = DecimalFormat("#.##")
    return decimalFormat.format(windSpeed).plus(" km/s")
}

fun Double.kelvinToCelcius() : String{
    return this.minus(273.15).toInt().toString().plus("°C")
}

fun String.currentDateFormat(): String {
    val currentDate = Calendar.getInstance().time
    return SimpleDateFormat(this, Locale("tr")).format(currentDate)
}