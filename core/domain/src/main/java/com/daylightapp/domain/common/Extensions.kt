package com.daylightapp.domain.common

import java.text.SimpleDateFormat
import java.util.*

fun Int.toDateFormat(dateFormat: String): String {
    val longToDate = Date(this.toLong() * 1000L)
    val sdf = SimpleDateFormat(dateFormat, Locale("tr"))
    sdf.timeZone = TimeZone.getTimeZone("Asia/Istanbul")
    return sdf.format(longToDate)
}