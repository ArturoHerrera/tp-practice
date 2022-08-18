package com.arthur.totalplaytest.data.repository.data_source

import com.arthur.totalplaytest.data.repository.SessionLocalDataSource
import com.arthur.totalplaytest.utils.AppPreferences

class SessionDataLocalDataSource(
    private val prefs: AppPreferences
) : SessionLocalDataSource {

    //TODO Improve logout, only for demostration
    override suspend fun logOut(): Boolean {
        return try {
            prefs.clearAllUserData()
            true
        } catch (e: Exception) {
            false
        }
    }
}