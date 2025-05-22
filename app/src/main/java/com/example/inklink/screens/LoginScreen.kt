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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.inklink.viewmodel.DataViewModel

@Composable
fun LoginScreen(viewModel: DataViewModel, navController: NavHostController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.Center) {
        Text("Iniciar Sesión", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Usuario") })
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") }, visualTransformation = PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            viewModel.loginUser(username, password) { success, userId ->
                if (success) {
                    Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show()
                    navController.navigate("home") // o guarda userId como sesión
                } else {
                    Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
                }
            }
        }) {
            Text("Entrar")
        }
    }
}
