package com.example.todoapp_android_kotlin_compose.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.todoapp_android_kotlin_compose.navigation.destinations.listComposable
import com.example.todoapp_android_kotlin_compose.navigation.destinations.splashComposable
import com.example.todoapp_android_kotlin_compose.navigation.destinations.taskComposable
import com.example.todoapp_android_kotlin_compose.ui.viewmodels.SharedViewModel
import com.example.todoapp_android_kotlin_compose.util.Constants.SPLASH_SCREEN

@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        splashComposable(
            navigateToListScreen = screen.splash
        )
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
    }
}
