package com.example.inklink.view


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.inklink.navigation.AppNavigation
import com.example.inklink.ui.theme.InkLinkTheme
import com.example.inklink.viewmodel.MainViewModel


class MainActivity : ComponentActivity() {

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setContent {
            InkLinkTheme {
                AppNavigation(viewModel)
            }
        }
    }
}
