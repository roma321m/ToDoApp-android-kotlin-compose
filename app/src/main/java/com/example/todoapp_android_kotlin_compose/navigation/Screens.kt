package com.example.todoapp_android_kotlin_compose.navigation

import androidx.navigation.NavController
import com.example.todoapp_android_kotlin_compose.util.Action

class Screens(navController: NavController) {

    companion object {
        const val LIST_SCREEN = "list/{action}"
        const val TASK_SCREEN = "task/{taskId}"

        const val LIST_ARGUMENT_KEY = "action"
        const val TASK_ARGUMENT_KEY = "taskId"
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
