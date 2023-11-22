package com.gufeczek.summonertracker.core.util.preference

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "PreferenceDataStore")

class PreferenceHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataSource = context.dataStore

    suspend fun <T> getPreference(key: Preferences.Key<T>): T? = dataSource.data.first()[key]

    suspend fun <T> putPreference(key: Preferences.Key<T>, value: T) {
        dataSource.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun <T> deletePreference(key: Preferences.Key<T>) {
        dataSource.edit { preferences ->
            preferences.remove(key)
        }
    }
}
