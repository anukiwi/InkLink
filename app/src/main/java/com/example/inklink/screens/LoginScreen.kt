package com.example.inklink.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.inklink.data.api.RetrofitClient
import com.example.inklink.data.model.Usuario
import com.example.inklink.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LoginScreen(navController: NavHostController, viewModel: MainViewModel) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.Center) {
        Text("Iniciar Sesión", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") })
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            RetrofitClient.instance.getUsuarioPorCredenciales(username, password)
                .enqueue(object : Callback<List<Usuario>> {
                    override fun onResponse(
                        call: Call<List<Usuario>>,
                        response: Response<List<Usuario>>
                    ) {
                        val usuarios = response.body()
                        if (usuarios != null && usuarios.isNotEmpty()) {
                            Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show()
                            viewModel.login()
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }

                        } else {
                            Toast.makeText(
                                context,
                                "Usuario o contraseña incorrectos",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                        Toast.makeText(context, "Error de conexión", Toast.LENGTH_LONG).show()
                    }
                })
        }) {
            Text("Entrar")
        }
    }
}

