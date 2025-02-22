package com.project.sportify.data.sharedprefrences

import android.content.Context
import com.google.gson.Gson
import com.project.sportify.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) : IPreferencesDataSource {

    private val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    override fun shouldNavigateToWhichScreen(): Boolean {
        return sharedPreferences.getBoolean(Constants.SharedPreference.IS_OPENED, false)
    }

    override fun isDarkMode(): Boolean {
        return sharedPreferences.getBoolean(Constants.SharedPreference.IS_DARK_MODE, false)
    }

    override fun isOpened(): Boolean {
        return sharedPreferences.getBoolean(Constants.SharedPreference.IS_OPENED, false)
    }

    override fun open() {
        sharedPreferences.edit().putBoolean(Constants.SharedPreference.IS_OPENED, true).apply()
    }

}

