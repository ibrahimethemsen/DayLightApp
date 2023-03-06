package com.daylightapp.data.common

import com.daylightapp.data.common.Constants.DAY_MONTH_FORMAT
import com.daylightapp.data.common.Constants.HOUR_MINUTE_FORMAT
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExtensionsTest {
    @Test
    fun when_toDateFormat_is_locationTrFormat(){
        val date = (1675294863).toDateFormat(HOUR_MINUTE_FORMAT)
        assertThat(date).isEqualTo("ÖÖ 02:41")
    }

    /*@Test
    fun when_milToKmSpeed_is_kmSpeed(){
        // . ve , !
        val milToKm = (4.12).milToKmSpeed()
        assertThat(milToKm).isEqualTo("6,63 km/s")
    }*/

    @Test
    fun when_kelvinToCelcius_is_celvin(){
        val celcius = (290.12).kelvinToCelcius()
        assertThat(celcius).isEqualTo("16°C")
    }

    /*@Test
    fun when_currentDateFormat_is_dayMontFormatDate(){
        val currentDate = DAY_MONTH_FORMAT.currentDateFormat()
        assertThat(currentDate).isEqualTo("02-Şub")
    }*/
}