package com.example.inklink.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.inklink.data.api.RetrofitClient
import com.example.inklink.data.api.SupabaseApi
import com.example.inklink.data.helper.SessionManager
import com.example.inklink.data.model.Historia
import com.example.inklink.data.model.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val sessionManager = SessionManager(application.applicationContext)
    private val api: SupabaseApi = RetrofitClient.instance


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
        val usuario = sessionManager.getUser()
        if (usuario != null) {
            // Llamada al servidor
            val call = api.actualizarDescripcionUsuario(
                id = "eq.${usuario.id}",
                body = mapOf("descripcion" to nuevaDescripcion)
            )

            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // Actualiza en local si Supabase lo aceptó
                        val actualizado = usuario.copy(descripcion = nuevaDescripcion)
                        sessionManager.saveUser(actualizado)
                        usuarioActual = actualizado
                    } else {
                        Log.e("API", "Error Supabase: ${response.code()} - ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("API", "Fallo de red: ${t.localizedMessage}")
                }
            })
        }
    }
    fun publicarHistoria(historia: Historia, callback: (Boolean) -> Unit) {
        val call = api.publicarHistoria(historia)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                callback(response.isSuccessful)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback(false)
            }
        })
    }
}
