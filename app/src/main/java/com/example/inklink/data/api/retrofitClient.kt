package com.example.inklink.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://nxhfmafypcsejwgaejww.supabase.co"


    //Crea una instancia de la interfaz SupabaseApi de manera diferida
    //La instancia se crea solo la primera vez que se accede a ella
    val instance: SupabaseApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SupabaseApi::class.java)
    }
}