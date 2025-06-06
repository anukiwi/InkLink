package com.example.inklink.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.inklink.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

//esta pantalla solo se muestra si el usuario ESTÁ loggeado.
fun UserScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Perfil",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = {
                        // 1) Limpiar sesión en ViewModel
                        viewModel.logout()
                        // 2) Navegar a Login y limpiar backstack
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Cerrar sesión",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF65626B),
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("escribir") },
                containerColor = Color(0xFF65626B),
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Escribir Historia"
                )
            }
        },
        containerColor = Color(0xFF65626B)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFF65626B))
        ) {
            val user = viewModel.getCurrentUser()
            var descripcion by remember { mutableStateOf(user?.descripcion ?: "") }
            val isEditingDescripcion = remember { mutableStateOf(user?.descripcion.isNullOrEmpty()) }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(72.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(36.dp))
                        .padding(8.dp),
                    tint = Color.White
                )
                //mostrar los datos del usuario actual en su perfil
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "${user?.nombre ?: ""} ${user?.apellidos ?: ""}",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "@${user?.username ?: ""}",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.White.copy(alpha = 0.5f), thickness = 1.dp)
                Spacer(modifier = Modifier.height(8.dp))

                //funcion para añadir una descripción
                //1. al registrarse el usuario no tiene descripcion hasta que la añade,
                //el primer mensaje que se muestra es para añadir la descripción
                //2. una vez guardas la descripción se añade a la bdd y te permite cambiarla
                //las veces que quieras
                if (isEditingDescripcion.value) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .background(Color(0xFF65626B)), // Mismo fondo que pantalla
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = descripcion,
                            onValueChange = { descripcion = it },
                            label = { Text("Añadir descripción", color = Color.White) },
                            singleLine = false,
                            maxLines = 3,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedLabelColor = Color.White,
                                unfocusedLabelColor = Color.White,
                                containerColor = Color(0xFF65626B),
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White.copy(alpha = 0.5f)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                viewModel.actualizarDescripcion(descripcion)
                                isEditingDescripcion.value = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            )
                        ) {
                            Text("Guardar")
                        }
                    }
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = descripcion,
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextButton(
                            onClick = { isEditingDescripcion.value = true }
                        ) {
                            Text("Editar descripción", color = Color.White)
                        }
                    }
                }
                }

                // Fondo claro para las secciones inferiores
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF2F0ED))
            ) {
                SectionTitle("Historias publicadas")
                LazyColumn(
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(2) { i ->
                        RecommendedCard(
                            autor = "pacopul",
                            tiempo = "hace ${i + 1} días",
                            titulo = "Cómo ser un buen profesor",
                            descripcion = "Si eres profesor o quieres serlo, te animo a que participes y escribas un capítulo.",
                            likes = 2000,
                            capitulos = 7,
                            vistas = 12000
                        )
                    }
                }

                SectionTitle("Historias guardadas")
                LazyColumn(
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(2) { i ->
                        RecommendedCard(
                            autor = "manuelrodriguez",
                            tiempo = "hace 7 horas",
                            titulo = "Esto no es una historia de terror",
                            descripcion = "¿Cómo te quedas si te cuento que esta historia realmente no es de terror?",
                            likes = 500,
                            capitulos = 3,
                            vistas = 3000
                        )
                    }
                }
            }
        }
    }
}

//aquí hice las estadísticas de los usuarios pero no las uso ya que ocupan mucho espacio
@Composable
fun ProfileStat(count: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(text = label, color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF65626D),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    )
}
