package com.arthur.totalplaytest.utils

import android.app.Application
import android.content.Context

class AppPreferences(application: Application) {
    companion object {
        private const val USER_TOKEN = "userToken"
    }

    private val prefs = application.getSharedPreferences("sharedData", Context.MODE_PRIVATE)

    fun clearAllUserData() {
        val keys = arrayOf(
            USER_TOKEN
        )
        keys.onEach { prefs.edit().remove(it).apply() }
    }

    fun setUserToken(userToken: String) = prefs.edit().putString(USER_TOKEN, userToken).apply()

    fun getUserToken(): String? = prefs.getString(USER_TOKEN, "")
}