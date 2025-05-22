package com.example.inklink.viewmodel

import androidx.core.view.WindowInsetsAnimationCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inklink.data.api.RetrofitClient
import com.example.inklink.data.model.Usuario
import kotlinx.coroutines.launch

class DataViewModel : ViewModel() {

    fun registrarUsuario(usuario: Usuario, onResult: (Boolean) -> Unit) {
        val call = RetrofitClient.instance.insertarUsuario(usuario)
        call.enqueue(object : WindowInsetsAnimationCompat.Callback<List<Usuario>> {
            override fun onResponse(
                call: Call<List<Usuario>>,
                response: Response<List<Usuario>>
            ) {
                if (response.isSuccessful) {
                    onResult(true)
                } else {
                    onResult(false)
                }
            }

            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                onResult(false)
            }
        })
    }

    fun loginUser(username: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            try {
                val result = supabase
                    .from("usuario")
                    .select("*")
                    .eq("username", username)
                    .eq("password", password) // ⚠️ en producción, compara hash
                    .limit(1)
                    .execute()

                val user = result.data?.jsonArray?.firstOrNull()?.jsonObject
                if (user != null) {
                    val userId = user["id"]?.jsonPrimitive?.content
                    onResult(true, userId)
                } else {
                    onResult(false, null)
                }
            } catch (e: Exception) {
                onResult(false, null)
            }
        }
    }



}