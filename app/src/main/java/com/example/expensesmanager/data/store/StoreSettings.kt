package com.example.expensesmanager.data.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.expensesmanager.core.Store
import com.example.expensesmanager.domain.model.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreSettings @Inject constructor(
    @ApplicationContext private val context: Context
) : Store<Settings> {

    override fun get(): Flow<Settings> = context.dataStore.data.map { preferences ->
        Settings(
            isEnglish = preferences[IS_ENGLISH] ?: true,
            isDark = preferences[IS_DARK] ?: true,
            currency = preferences[CURRENCY] ?: ""
        )
    }

    override suspend fun save(name: Settings) {
        context.dataStore.edit { preferences ->
            preferences[IS_ENGLISH] = name.isEnglish
            preferences[IS_DARK] = name.isDark
            preferences[CURRENCY] = name.currency

        }
    }

    private companion object {

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            "settings preferences"
        )
        val IS_ENGLISH = booleanPreferencesKey("IS_ENGLISH")
        val IS_DARK = booleanPreferencesKey("IS_DARK")
        val CURRENCY = stringPreferencesKey("CURRENCY")
    }
}