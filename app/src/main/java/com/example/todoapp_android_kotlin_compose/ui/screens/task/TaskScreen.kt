package com.example.todoapp_android_kotlin_compose.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.todoapp_android_kotlin_compose.ui.screens.task.app_bar.TaskAppBar
import com.example.todoapp_android_kotlin_compose.util.Action

@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
            TaskAppBar(navigateToListScreen = navigateToListScreen)
        },
        content = {

        }
    )
}