package com.example.inklink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.inklink.data.helper.SessionManager
import com.example.inklink.data.model.Usuario

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val sessionManager = SessionManager(application.applicationContext)

    var isLoggedIn: Boolean = sessionManager.isLoggedIn()
        private set

    var usuarioActual: Usuario? = sessionManager.getUser()
        private set

    fun login(usuario: Usuario) {
        sessionManager.saveLoginState(true)
        sessionManager.saveUser(usuario)
        isLoggedIn = true
        usuarioActual = usuario
    }

    fun logout() {
        sessionManager.clearSession()
        isLoggedIn = false
        usuarioActual = null
    }

    fun getCurrentUser(): Usuario? {
        return usuarioActual
    }

    fun actualizarDescripcion(nuevaDescripcion: String) {
        usuarioActual?.let {
            val actualizado = it.copy(descripcion = nuevaDescripcion)
            sessionManager.saveUser(actualizado)
            usuarioActual = actualizado
        }
    }
}
