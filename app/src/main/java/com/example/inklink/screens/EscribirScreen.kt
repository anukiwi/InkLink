package com.example.inklink.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.inklink.data.model.Historia
import com.example.inklink.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EscribirScreen(navController: NavHostController, viewModel: MainViewModel) {
    var titulo by remember { mutableStateOf("") }
    var sinopsis by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Historia", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF65626B),
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFF2F0ED),
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color(0xFFF2F0ED)),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF65626B),
                        focusedLabelColor = Color(0xFF65626B)
                    )
                )

                OutlinedTextField(
                    value = sinopsis,
                    onValueChange = { sinopsis = it },
                    label = { Text("Sinopsis") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF65626B),
                        focusedLabelColor = Color(0xFF65626B)
                    ),
                    maxLines = 6
                )

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = {
                        val usuario = viewModel.getCurrentUser()
                        if (usuario != null && titulo.isNotBlank() && sinopsis.isNotBlank()) {
                            val historia = Historia(
                                titulo = titulo,
                                sinopsis = sinopsis,
                                usuario_id = usuario.id
                            )
                            viewModel.publicarHistoria(historia) { success ->
                                if (success) {
                                    Toast.makeText(
                                        context,
                                        "Historia publicada",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.navigate("home") {
                                        popUpTo("escribir") { inclusive = true }
                                    }
                                } else {
                                    Toast.makeText(context, "Error al publicar", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Completa título y sinopsis",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier.align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF65626B),
                        contentColor = Color.White
                    )
                ) {
                    Text("Publicar")
                }
            }
        }
    )
}
