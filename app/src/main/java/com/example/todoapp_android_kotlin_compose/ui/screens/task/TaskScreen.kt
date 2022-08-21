package com.example.todoapp_android_kotlin_compose.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.todoapp_android_kotlin_compose.data.models.ToDoTask
import com.example.todoapp_android_kotlin_compose.ui.screens.task.app_bar.TaskAppBar
import com.example.todoapp_android_kotlin_compose.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen = navigateToListScreen,
                selectedTask = selectedTask
            )
        },
        content = {

        }
    )
}
