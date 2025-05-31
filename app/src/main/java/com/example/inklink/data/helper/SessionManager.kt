package com.example.inklink.data.helper

import android.content.Context
import android.content.SharedPreferences
import com.example.inklink.data.model.Usuario


//Clase para gestionar la sesión del usuario utilizando SharedPreferences
class SessionManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    //Guarda el estado de inicio de sesión y el usuario actual
    fun saveLoginState(isLoggedIn: Boolean) {
        prefs.edit().putBoolean("is_logged_in", isLoggedIn).apply()
    }

    //C
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("is_logged_in", false)
    }

    //Limpia las preferencias de inicio de sesión // CERRAR SESIÓN
    fun clearSession() {
        prefs.edit().clear().apply()
    }

    //Guarda un usuario en las preferencias, guarda todos los datos del usuario
    fun saveUser(usuario: Usuario) {
        val editor = prefs.edit()
        editor.putString("user_id", usuario.id)
        editor.putString("user_nombre", usuario.nombre)
        editor.putString("user_apellidos", usuario.apellidos)
        editor.putString("user_username", usuario.username)
        editor.putString("user_password", usuario.password)
        editor.putString("user_foto", usuario.foto)
        editor.putString("user_descripcion", usuario.descripcion)
        editor.apply()
    }

    //Devuelve todos los datos del usuario
    fun getUser(): Usuario? {
        val id = prefs.getString("user_id", null) ?: return null
        val nombre = prefs.getString("user_nombre", "") ?: ""
        val apellidos = prefs.getString("user_apellidos", "") ?: ""
        val username = prefs.getString("user_username", "") ?: ""
        val password = prefs.getString("user_password", "") ?: ""
        val foto = prefs.getString("user_foto", null)
        val descripcion = prefs.getString("user_descripcion", null)

        return Usuario(id, nombre, apellidos, username, password, foto, descripcion)
    }
}
