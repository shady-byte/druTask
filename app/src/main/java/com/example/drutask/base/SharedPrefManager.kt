package com.example.drutask.base

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesManager @Inject constructor(@ApplicationContext context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(APP_PREFERENCE_FILE, Context.MODE_PRIVATE)

    companion object {
        private const val APP_PREFERENCE_FILE = "AppPrefs"
        private const val KEY_LAST_UPDATE_TIME = "lastUpdateTime"
    }

    fun getLastUpdateTime(): Long {
        return prefs.getLong(KEY_LAST_UPDATE_TIME, 0)
    }

    fun setLastUpdateTime() {
        prefs.edit().putLong(KEY_LAST_UPDATE_TIME, System.currentTimeMillis()).apply()
    }
}