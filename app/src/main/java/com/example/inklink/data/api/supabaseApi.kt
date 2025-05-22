package com.example.inklink.data.api

import com.example.inklink.data.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface SupabaseApi {
    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im54aGZtYWZ5cGNzZWp3Z2Fland3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDcwNzg5NzAsImV4cCI6MjA2MjY1NDk3MH0.C_IAkLvIb9a3NGEUbF9PQK_x4S0WLfmA6x-wotAnzlU",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im54aGZtYWZ5cGNzZWp3Z2Fland3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDcwNzg5NzAsImV4cCI6MjA2MjY1NDk3MH0.C_IAkLvIb9a3NGEUbF9PQK_x4S0WLfmA6x-wotAnzlU",
        "Accept: application/json"
    )
    @POST("rest/v1/usuario")
    fun insertarUsuario(
        @Body usuario: Usuario
    ): Call<List<Usuario>>
}