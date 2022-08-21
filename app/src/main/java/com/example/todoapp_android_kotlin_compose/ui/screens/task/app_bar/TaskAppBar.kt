package com.example.todoapp_android_kotlin_compose.ui.screens.task.app_bar

import androidx.compose.runtime.Composable
import com.example.todoapp_android_kotlin_compose.data.models.ToDoTask
import com.example.todoapp_android_kotlin_compose.util.Action

@Composable
fun TaskAppBar(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    if (selectedTask == null) {
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    } else {
        ExistingTaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen
        )
    }
}
