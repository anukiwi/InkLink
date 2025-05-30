package com.example.inklink.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HistoriaWithUser(
    val id: String?,
    val titulo: String,
    val sinopsis: String,
    val likes: Int = 0,
    val visitas: Int = 0,
    val usuario_id: String,
    @SerializedName("created_at")
    val usuario: List<Usuario>
) : Serializable
