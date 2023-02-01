package com.daylightapp.data.source.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.daylightapp.common.datastore.LocationEntity
import com.daylightapp.data.common.Constants.AN_ERROR
import com.daylightapp.data.di.IoDispatcher
import com.daylightapp.domain.entity.quote.QuoteEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class DataStoreDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : DataStoreDataSource {
    private object PreferencesKeys {
        val lon = stringPreferencesKey("lon")
        val lat = stringPreferencesKey("lat")
        val name = stringPreferencesKey("name")
        val startDestination = stringPreferencesKey("destination")
        val date = stringPreferencesKey("date_current")
        val quote = stringPreferencesKey("quote")
        val author = stringPreferencesKey("author")
    }

    override suspend fun writeCityDataStore(lat: String, lon: String, name: String) {
        withContext(ioDispatcher) {
            try {
                dataStore.edit {
                    it[PreferencesKeys.lat] = lat
                    it[PreferencesKeys.lon] = lon
                    it[PreferencesKeys.name] = name
                }
            } catch (e: Exception) {
                Log.e("DataStore", "writeCityDataStore: ${e.localizedMessage}")
            }
        }
    }

    override suspend fun writeNavStartDestination(destination: String) {
        withContext(ioDispatcher) {
            try {
                dataStore.edit {
                    it[PreferencesKeys.startDestination] = destination
                }
            } catch (e: Exception) {
                Log.e("DataStore", "writeNavStart: ${e.localizedMessage}")
            }
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
        }.map { preference ->
            mapCityPreferencesKey(preference)
        }.flowOn(ioDispatcher)


    override val readNavStartDestination: Flow<String>
        get() = dataStore.data.catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preference ->
            preference[PreferencesKeys.startDestination] ?: "d3"
        }.flowOn(ioDispatcher)

    override suspend fun writeCurrentDate(currentDate: String) {
        withContext(ioDispatcher) {
            try {
                dataStore.edit {
                    it[PreferencesKeys.date] = currentDate
                }
            } catch (e: Exception) {
                Log.e("DataStore", "writeCurrentDate: ${e.localizedMessage}")
            }
        }
    }

    override val readCurrentDate: Flow<String>
        get() = dataStore.data.catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preference ->
            preference[PreferencesKeys.date] ?: AN_ERROR
        }.flowOn(ioDispatcher)

    override suspend fun writeLocalQuote(quote: String, author: String) {
        withContext(ioDispatcher) {
            try {
                dataStore.edit {
                    it[PreferencesKeys.quote] = quote
                    it[PreferencesKeys.author] = author
                }
            } catch (e: Exception) {
                Log.e("DataStore", "writeCurrentDate: ${e.localizedMessage}")
            }
        }
    }

    override val readLocalQuote: Flow<QuoteEntity>
        get() = dataStore.data.catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preference ->
            mapLocalQuote(preference)
        }

    private fun mapCityPreferencesKey(preferences: Preferences): LocationEntity {
        val lon = preferences[PreferencesKeys.lon] ?: "d0"
        val lat = preferences[PreferencesKeys.lat] ?: "d1"
        val name = preferences[PreferencesKeys.name] ?: "d2"
        return LocationEntity(lon, lat, name)
    }

    private fun mapLocalQuote(preferences: Preferences): QuoteEntity {
        val quote = preferences[PreferencesKeys.quote] ?: AN_ERROR
        val author = preferences[PreferencesKeys.author] ?: AN_ERROR
        return QuoteEntity(quote, author)
    }
}