package com.example.inklink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.inklink.data.helper.SessionManager


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val sessionManager = SessionManager(application.applicationContext)

    var isLoggedIn: Boolean = sessionManager.isLoggedIn()
        private set

    fun login() {
        sessionManager.saveLoginState(true)
        isLoggedIn = true
    }

    fun logout() {
        sessionManager.clearSession()
        isLoggedIn = false
    }
}
