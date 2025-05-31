package com.example.inklink.data.api

import com.example.inklink.data.model.Historia
import com.example.inklink.data.model.HistoriaWithUser
import com.example.inklink.data.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

//Define una interfaz de Retrofit para conectarse a Supabase usando mi API REST
interface SupabaseApi {
    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im54aGZtYWZ5cGNzZWp3Z2Fland3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDcwNzg5NzAsImV4cCI6MjA2MjY1NDk3MH0.C_IAkLvIb9a3NGEUbF9PQK_x4S0WLfmA6x-wotAnzlU",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im54aGZtYWZ5cGNzZWp3Z2Fland3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDcwNzg5NzAsImV4cCI6MjA2MjY1NDk3MH0.C_IAkLvIb9a3NGEUbF9PQK_x4S0WLfmA6x-wotAnzlU",
        "Accept: application/json",
        "Content-Type: application/json"

    )
    //Realiza una petición POST a la tabla usuario
    @POST("rest/v1/usuario")
    fun registrarUsuario(@Body usuario: Usuario): Call<Void>

    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im54aGZtYWZ5cGNzZWp3Z2Fland3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDcwNzg5NzAsImV4cCI6MjA2MjY1NDk3MH0.C_IAkLvIb9a3NGEUbF9PQK_x4S0WLfmA6x-wotAnzlU",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im54aGZtYWZ5cGNzZWp3Z2Fland3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDcwNzg5NzAsImV4cCI6MjA2MjY1NDk3MH0.C_IAkLvIb9a3NGEUbF9PQK_x4S0WLfmA6x-wotAnzlU",
        "Accept: application/json",
    )

    //obtener usuario por credenciales
    @GET("rest/v1/usuario")
    fun getUsuarioPorCredenciales(
        @Query("select") select: String = "*",
        @QueryMap filters: Map<String, String>
    ): Call<List<Usuario>>

    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im54aGZtYWZ5cGNzZWp3Z2Fland3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDcwNzg5NzAsImV4cCI6MjA2MjY1NDk3MH0.C_IAkLvIb9a3NGEUbF9PQK_x4S0WLfmA6x-wotAnzlU",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im54aGZtYWZ5cGNzZWp3Z2Fland3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDcwNzg5NzAsImV4cCI6MjA2MjY1NDk3MH0.C_IAkLvIb9a3NGEUbF9PQK_x4S0WLfmA6x-wotAnzlU",
        "Accept: application/json",
        "Content-Type: application/json"
    )

    //Aquí utilizo PATCH para modificar parcialmente un usuario
    @retrofit2.http.PATCH("rest/v1/usuario")
    fun actualizarDescripcionUsuario(
        @Query("id") id: String,
        @Body body: Map<String, String>
    ): Call<Void>

    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im54aGZtYWZ5cGNzZWp3Z2Fland3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDcwNzg5NzAsImV4cCI6MjA2MjY1NDk3MH0.C_IAkLvIb9a3NGEUbF9PQK_x4S0WLfmA6x-wotAnzlU",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im54aGZtYWZ5cGNzZWp3Z2Fland3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDcwNzg5NzAsImV4cCI6MjA2MjY1NDk3MH0.C_IAkLvIb9a3NGEUbF9PQK_x4S0WLfmA6x-wotAnzlU",
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("rest/v1/historia")
    fun publicarHistoria(@Body historia: Historia): Call<Void>


    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im54aGZtYWZ5cGNzZWp3Z2Fland3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDcwNzg5NzAsImV4cCI6MjA2MjY1NDk3MH0.C_IAkLvIb9a3NGEUbF9PQK_x4S0WLfmA6x-wotAnzlU",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im54aGZtYWZ5cGNzZWp3Z2Fland3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDcwNzg5NzAsImV4cCI6MjA2MjY1NDk3MH0.C_IAkLvIb9a3NGEUbF9PQK_x4S0WLfmA6x-wotAnzlU",
        "Accept: application/json"
    )

    //Obtener historias
    @GET("/rest/v1/historia?select=*")
    fun obtenerHistorias(): Call<List<Historia>>

    @Headers(
        "apikey: TU_API_KEY",
        "Authorization: Bearer TU_BEARER",
        "Accept: application/json"
    )
    @GET("rest/v1/historia")
    fun obtenerHistoriasConUsuario(
        @Query("select") select: String = "*,created_at,usuario:usuario_id(id,nombre,apellidos)"
    ): Call<List<HistoriaWithUser>>
}

