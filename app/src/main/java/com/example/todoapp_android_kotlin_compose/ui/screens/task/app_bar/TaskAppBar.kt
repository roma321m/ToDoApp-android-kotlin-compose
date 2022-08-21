package com.example.todoapp_android_kotlin_compose.ui.screens.task.app_bar

import androidx.compose.runtime.Composable
import com.example.todoapp_android_kotlin_compose.util.Action

@Composable
fun TaskAppBar(
    navigateToListScreen: (Action) -> Unit
) {
    NewTaskAppBar(navigateToListScreen = navigateToListScreen)
}
