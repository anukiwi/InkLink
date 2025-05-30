package com.example.inklink.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.inklink.R
import com.example.inklink.data.api.RetrofitClient
import com.example.inklink.data.model.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController) {
    val context = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Registro",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color(0xFF65626B),
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF65626B),
                focusedLabelColor = Color(0xFF65626B)
            )

        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = apellidos,
            onValueChange = { apellidos = it },
            label = { Text("Apellidos") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF65626B),
                focusedLabelColor = Color(0xFF65626B)
            )
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de usuario") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF65626B),
                focusedLabelColor = Color(0xFF65626B)
            )
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF65626B),
                focusedLabelColor = Color(0xFF65626B)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                if (nombre.isBlank() || apellidos.isBlank() || username.isBlank() || password.isBlank()) {
                    Toast.makeText(context, "Por favor rellena todos los campos", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                val nuevoUsuario = Usuario(
                    id = UUID.randomUUID().toString(),
                    nombre = nombre,
                    apellidos = apellidos,
                    username = username,
                    password = password
                )

                RetrofitClient.instance.registrarUsuario(nuevoUsuario)
                    .enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            if (response.isSuccessful) {
                                Toast.makeText(context, "Registro exitoso", Toast.LENGTH_LONG)
                                    .show()
                                navController.navigate("login") {
                                    popUpTo("register") { inclusive = true }
                                }
                            } else {
                                Toast.makeText(context, "Error en el registro", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Toast.makeText(context, "Error de conexión", Toast.LENGTH_LONG).show()
                        }
                    })
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF65626B),
                contentColor = colorResource(id = R.color.white)
            )
        ) {
            Text("Registrarse", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¿Ya tienes tienes cuenta?",
                fontSize = 14.sp,
                color = colorResource(id = R.color.black)
            )
            Text(
                text = "Inicia sesión",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF65626B),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable { navController.navigate("login") }
            )
        }

    }
}
