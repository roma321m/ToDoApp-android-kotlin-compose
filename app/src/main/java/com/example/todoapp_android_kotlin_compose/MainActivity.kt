package com.example.todoapp_android_kotlin_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todoapp_android_kotlin_compose.navigation.SetupNavigation
import com.example.todoapp_android_kotlin_compose.ui.theme.ToDoApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoApp {
                navController = rememberNavController()
                SetupNavigation(navController = navController)
            }
        }
    }
}