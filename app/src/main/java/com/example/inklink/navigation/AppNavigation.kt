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
        }
    }

sealed class AppScreens(val route: String) {
    object HomeScreen : AppScreens(route = "HomeScreen")
    object UserScreen : AppScreens(route = "UserScreen")

}

