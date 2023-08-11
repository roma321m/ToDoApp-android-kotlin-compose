package com.example.todoapp_android_kotlin_compose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.todoapp_android_kotlin_compose.navigation.Screens.Companion.LIST_SCREEN
import com.example.todoapp_android_kotlin_compose.navigation.destinations.listComposable
import com.example.todoapp_android_kotlin_compose.navigation.destinations.taskComposable
import com.example.todoapp_android_kotlin_compose.ui.viewmodels.SharedViewModel

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    showAd: () -> Unit
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.task,
            sharedViewModel = sharedViewModel,
            showAd = showAd
        )
    }
}
