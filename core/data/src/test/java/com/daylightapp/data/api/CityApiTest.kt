package com.daylightapp.data.api

import com.daylightapp.data.Constants.FILE_NAME_CITY_LIST_RESPONSE
import com.daylightapp.data.api.city.CityApi
import com.daylightapp.data.enqueueResponse
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CityApiTest {
    private lateinit var cityApi: CityApi
    private val mockWebServer = MockWebServer()

    @Before
    fun setup() {
        mockWebServer.start(8080)
        cityApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CityApi::class.java)
        // fake queued response-sahte kuyruğa eklenmiş yanıt
        mockWebServer.enqueueResponse(FILE_NAME_CITY_LIST_RESPONSE)
    }

    @Test
    fun when_getCityList_is_response_notNull() = runBlocking {
        val response = cityApi.getAllCity()
        assertThat(response).isNotNull()
    }

    @Test
    fun when_getCityList_is_firstItem_expected() = runBlocking {
        val response = cityApi.getAllCity()
        val firstCityName = response.first().name
        val firstCityId = response.first().id
        assertThat(firstCityName).isEqualTo("Adana")
        assertThat(firstCityId).isEqualTo(1)
    }

    @Test
    fun when_getCityList_is_requestPath() = runBlocking {
        // request to mock web server-sunucuya yapılan istek
        val response = cityApi.getAllCity()
        // mock received mock server-sunucu tarafından alınan istek
        val request = mockWebServer.takeRequest()
        assertThat(request.path).isEqualTo("/Askeri-Muhendis/9db916d1632adbc85595af111ba30679/raw/4754e5f9d09dade2e6c461d7e960e13ef38eaa88/cities_of_turkey.json")
    }

    @After
    fun shutDown(){
        mockWebServer.shutdown()
    }
}