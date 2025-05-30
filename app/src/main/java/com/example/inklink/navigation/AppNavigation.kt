package com.example.inklink.navigation


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.inklink.screens.HomeScreen
import com.example.inklink.viewmodel.MainViewModel
import androidx.navigation.compose.rememberNavController
import com.example.inklink.screens.EscribirScreen
import com.example.inklink.screens.LoginScreen
import com.example.inklink.screens.RegisterScreen
import com.example.inklink.screens.UserScreen


@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(viewModel: MainViewModel) {

    val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = AppScreens.HomeScreen.route
        ) {
            composable(AppScreens.HomeScreen.route) {
                HomeScreen(navController, viewModel)
            }
            composable(AppScreens.UserScreen.route) {
                UserScreen(navController, viewModel)
            }
            composable(AppScreens.RegisterScreen.route) {
                RegisterScreen(navController) // Solo pasamos navController, no viewModel
            }
            composable(AppScreens.LoginScreen.route) {
                LoginScreen(navController, viewModel)
            }
            composable(AppScreens.EscribirScreen.route) {
                EscribirScreen(navController, viewModel)
            }
        }
    }

sealed class AppScreens(val route: String) {
    object HomeScreen : AppScreens(route = "home")
    object UserScreen : AppScreens(route = "user")
    object RegisterScreen : AppScreens(route = "register")
    object LoginScreen : AppScreens(route = "login")
    object EscribirScreen : AppScreens("escribir")

}

