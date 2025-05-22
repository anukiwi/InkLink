package com.example.inklink.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.inklink.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text(text = "Hola, pepito", fontSize = 20.sp)
                            Text(
                                text = "encuentra historias para leer",
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Perfil",
                            modifier = Modifier
                                .size(32.dp)
                                .clickable { /* ir a perfil */ }
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF888888),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFEFEFEF))
        ) {
            // Filtros
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                listOf("Popular", "Recomendado", "Nuevo").forEach {
                    FilterButton(it)
                }
            }

            // Historias Populares
            Text(
                text = "Historias Populares",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF65626D),
                modifier = Modifier.padding(start = 12.dp, top = 8.dp)
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(5) { i ->
                    PopularCard("Historia $i", "${20 + i} capítulos")
                }
            }

            // Historias Recomendadas
            Text(
                text = "Historias recomendadas",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF65626D),
                modifier = Modifier.padding(start = 12.dp, top = 8.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(5) { i ->
                    RecommendedCard(
                        autor = "usuario$i",
                        tiempo = "hace ${i + 1} horas",
                        titulo = "Historia interesante $i",
                        descripcion = "Un pequeño resumen de lo que trata esta historia $i...",
                        likes = 40 + i,
                        capitulos = 10 + i,
                        vistas = 1000 * (i + 1)
                    )
                }
            }

            // Historias Nuevas
            Text(
                text = "Historias nuevas",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF65626D),
                modifier = Modifier.padding(start = 12.dp, top = 8.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(3) { i ->
                    NewStoryCard("Nueva historia $i", "autor$i")
                }
            }
        }
    }
}

@Composable
fun FilterButton(text: String) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFA69C9A)),
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontSize = 14.sp
        )
    }
}

@Composable
fun PopularCard(titulo: String, capitulos: String) {
    Card(
        modifier = Modifier.size(width = 140.dp, height = 100.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF65626B))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = titulo, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = capitulos, color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
        }
    }
}

@Composable
fun RecommendedCard(
    autor: String,
    tiempo: String,
    titulo: String,
    descripcion: String,
    likes: Int,
    capitulos: Int,
    vistas: Int
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFA69C9A))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = titulo, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "$autor • $tiempo", fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = descripcion, maxLines = 2, fontSize = 14.sp, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "❤ $likes", fontSize = 12.sp, color = Color.White)
                Text(text = "📖 $capitulos", fontSize = 12.sp, color = Color.White)
                Text(text = "👁 $vistas", fontSize = 12.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun NewStoryCard(titulo: String, autor: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = titulo, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.DarkGray)
            Text(text = "por $autor", fontSize = 12.sp, color = Color.Gray)
        }
    }
}
