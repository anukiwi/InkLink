package com.example.inklink.data.model

import java.io.Serializable

data class Usuario(
    var id: String, //UUID en Supabase
    var nombre: String,
    var apellidos: String,
    var username: String,
    var password: String,
    var foto: String? = null,
    var descripcion: String? = null
) : Serializable

data class Historia(
    var id: String? = null, //UUID
    var titulo: String,
    var sinopsis: String,
    var likes: Int = 0,
    var visitas: Int = 0,
    var usuario_id: String //Foreign Key hacia Usuario
) : Serializable

data class Capitulo(
    var id: String, //UUID
    var texto: String,
    var hora: String, //Supabase devuelve timestamp como String
    var historia_id: String //Foreign Key hacia Historia
) : Serializable

data class Guardar(
    var usuario_id: String,
    var historia_id: String
) : Serializable


