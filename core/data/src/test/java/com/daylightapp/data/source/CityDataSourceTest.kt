package com.daylightapp.data.source

import com.daylightapp.common.NetworkResult
import com.daylightapp.data.api.city.CityApi
import com.daylightapp.data.testCityList
import com.daylightapp.data.source.city.CityDataSourceImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CityDataSourceTest {
    @Mock
    private lateinit var cityApi : CityApi
    private lateinit var cityDataSource : CityDataSourceImpl

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        cityDataSource = CityDataSourceImpl(cityApi)
    }

    @Test
    fun when_cityDataSource_is_success_response() = runBlocking{
        Mockito.`when`(cityApi.getAllCity()).thenReturn(
            testCityList
        )
        val response = cityDataSource.getAllCity()

        assertThat(response).isInstanceOf(NetworkResult.Success::class.java)
    }

    @Test
    fun when_cityDataSource_is_error_response() = runBlocking {
        Mockito.`when`(cityApi.getAllCity()).thenReturn(
            null
        )
        val response = cityDataSource.getAllCity()
        assertThat(response).isInstanceOf(NetworkResult.Error::class.java)
    }
}