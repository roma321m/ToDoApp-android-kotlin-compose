package com.example.todoapp_android_kotlin_compose.navigation

import androidx.navigation.NavController
import com.example.todoapp_android_kotlin_compose.util.Action
import com.example.todoapp_android_kotlin_compose.util.Constants.LIST_SCREEN

class Screens(navController: NavController) {

    val list: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true}
        }
    }

    val task: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/$taskId")
    }
}
