package com.ibrahimethemsen.daylightapp.data.api.city

import com.ibrahimethemsen.daylightapp.data.dto.city.City
import retrofit2.http.GET

interface CityApi {

    @GET("Askeri-Muhendis/9db916d1632adbc85595af111ba30679/raw/4754e5f9d09dade2e6c461d7e960e13ef38eaa88/cities_of_turkey.json")
    suspend fun getAllCity(): List<City>
}