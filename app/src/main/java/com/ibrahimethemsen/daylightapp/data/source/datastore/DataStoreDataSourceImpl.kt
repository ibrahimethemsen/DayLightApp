package com.ibrahimethemsen.daylightapp.data.source.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ibrahimethemsen.daylightapp.common.Constants.ONBOARDING_FRAGMENT
import com.ibrahimethemsen.daylightapp.data.dto.datastore.LocationEntity
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

    override suspend fun writeNavStartDestination(destination : String) {
        try {
            dataStore.edit {
                it[PreferencesKeys.startDestination] = destination
            }
        }catch (e : Exception){
            Log.e("DataStore", "writeCityDataStore: ${e.localizedMessage}")
        }
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
        preference[PreferencesKeys.startDestination] ?: ONBOARDING_FRAGMENT
    }

    private fun mapCityPreferencesKey(preferences: Preferences): LocationEntity {
        val lon = preferences[PreferencesKeys.lon] ?: "d0"
        val lat = preferences[PreferencesKeys.lat] ?: "d1"
        val name = preferences[PreferencesKeys.name] ?: "d2"
        return LocationEntity(lon, lat,name)
    }
}