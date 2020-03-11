package com.parthbhardwaj.trendinggithubrepo.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(val  context: Context) {

    private val preference_name = "githubtrend"
    val sharedPref: SharedPreferences =
        context.getSharedPreferences(preference_name, Context.MODE_PRIVATE)

    @SuppressLint("CommitPrefEdits")
    fun save(KEY_NAME: String, text: Long) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putLong(KEY_NAME, text)
        editor.apply()
    }

    fun getValueLong(KEY_NAME: String): Long? {
        return sharedPref.getLong(KEY_NAME, 0)
    }
}