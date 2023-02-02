package com.daylightapp.data.mapper

import com.daylightapp.data.cityList
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class CityListMapperTest {
    private lateinit var cityListMapper : CityListMapperImpl

    @Before
    fun setup(){
        cityListMapper = CityListMapperImpl()

    }

    @Test
    fun when_name_is_mapping_city_mapper(){
        val cityListName = cityList.first().name
        val cityMapperName = cityListMapper.map(cityList).first().name
        assertThat(cityListName).isEqualTo(cityMapperName)
    }

    @Test
    fun when_lon_is_mapping_city_lon(){
        val cityListLon = cityList.first().longitude
        val cityMapperLon = cityListMapper.map(cityList).first().lon
        assertThat(cityListLon).isEqualTo(cityMapperLon)
    }

    @Test
    fun when_lat_is_mapping_city_lat(){
        val cityListLat = cityList.first().latitude
        val cityMapperLat = cityListMapper.map(cityList).first().lat
        assertThat(cityListLat).isEqualTo(cityMapperLat)
    }

    @Test
    fun when_plate_is_mapping_city_plat(){
        val cityListPlate = cityList.first().id.toString()
        val cityMapperPlate = cityListMapper.map(cityList).first().plate
        assertThat(cityListPlate).isEqualTo(cityMapperPlate)
    }
}