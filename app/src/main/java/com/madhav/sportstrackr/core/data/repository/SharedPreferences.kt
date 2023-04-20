package com.madhav.sportstrackr.core.data.repository

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MySharedPreferences @Inject constructor(
    @ApplicationContext val context: Context
) {
    private val sharedPrefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPrefs.edit()


    companion object {
        private const val PREF_FILE_NAME = "my_pref_file"
        private const val PREF_KEY_ON_BOARDING_COMPLETED = "onboarding_completed"
    }

    fun saveOnBoardingCompleted(isCompleted: Boolean) {
        editor.putBoolean(PREF_KEY_ON_BOARDING_COMPLETED, isCompleted)
        editor.apply()
    }

    fun isOnBoardingCompleted(): Boolean {
        return sharedPrefs.getBoolean(PREF_KEY_ON_BOARDING_COMPLETED, false)
    }


}