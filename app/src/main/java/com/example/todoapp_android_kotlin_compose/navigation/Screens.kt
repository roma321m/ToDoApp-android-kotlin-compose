package com.example.todoapp_android_kotlin_compose.navigation

import androidx.navigation.NavController
import com.example.todoapp_android_kotlin_compose.util.Action
import com.example.todoapp_android_kotlin_compose.util.Constants.LIST_SCREEN
import com.example.todoapp_android_kotlin_compose.util.Constants.SPLASH_SCREEN

class Screens(navController: NavController) {
    val splash: () -> Unit = {
        navController.navigate(route = "list/${Action.NO_ACTION}") {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }

    val list: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/$taskId")
    }

    val task: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }
}
