package com.daylightapp.data.source.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.daylightapp.common.datastore.LocalQuote
import com.daylightapp.common.datastore.LocationEntity
import com.daylightapp.data.common.Constants.AN_ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreDataSource {
    private object PreferencesKeys {
        val lon = stringPreferencesKey("lon")
        val lat = stringPreferencesKey("lat")
        val name = stringPreferencesKey("name")
        val startDestination = stringPreferencesKey("destination")
        val date = stringPreferencesKey("current_date")
        val quote = stringPreferencesKey("quote")
        val author = stringPreferencesKey("author")
    }
    override suspend fun writeCityDataStore(lat: String, lon: String,name : String) {
        try {
            dataStore.edit {
                it[PreferencesKeys.lat] = lat
                it[PreferencesKeys.lon] = lon
                it[PreferencesKeys.name] = name
            }
        }catch (e : Exception){
            Log.e("DataStore", "writeCityDataStore: ${e.localizedMessage}")
        }
    }

    override suspend fun writeNavStartDestination(destination : String) {
        try {
            dataStore.edit {
                it[PreferencesKeys.startDestination] = destination
            }
        }catch (e : Exception){
            Log.e("DataStore", "writeNavStart: ${e.localizedMessage}")
        }
    }



    override val readCityDataStore: Flow<LocationEntity>
        get() = dataStore.data.catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map {preference ->
            mapCityPreferencesKey(preference)
        }



    override val readNavStartDestination: Flow<String>
        get() =  dataStore.data.catch { exception ->
        if (exception is IOException) {
            Log.d("DataStore", exception.message.toString())
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map {preference ->
        preference[PreferencesKeys.startDestination] ?: "d3"
    }

    override suspend fun writeCurrentDate(currentDate: String, quote: String, author: String) {
        try {
            dataStore.edit {
                it[PreferencesKeys.date] = currentDate
                it[PreferencesKeys.quote] = quote
                it[PreferencesKeys.author] = author
            }
        }catch (e : Exception){
            Log.e("DataStore", "writeCurrentDate: ${e.localizedMessage}")
        }
    }

    override val readCurrentDate: Flow<LocalQuote>
        get() = dataStore.data.catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map {preference ->
           mapLocalQuote(preference)
        }

    private fun mapCityPreferencesKey(preferences: Preferences): LocationEntity {
        val lon = preferences[PreferencesKeys.lon] ?: "d0"
        val lat = preferences[PreferencesKeys.lat] ?: "d1"
        val name = preferences[PreferencesKeys.name] ?: "d2"
        return LocationEntity(lon, lat,name)
    }
    private fun mapLocalQuote(preferences: Preferences): LocalQuote {
        val date = preferences[PreferencesKeys.date] ?: AN_ERROR
        val quote = preferences[PreferencesKeys.quote] ?: AN_ERROR
        val author = preferences[PreferencesKeys.author] ?: AN_ERROR
        return LocalQuote(date, quote, author)
    }
}