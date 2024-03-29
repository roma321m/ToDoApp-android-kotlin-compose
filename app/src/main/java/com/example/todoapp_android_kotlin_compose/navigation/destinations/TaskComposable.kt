package com.example.todoapp_android_kotlin_compose.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todoapp_android_kotlin_compose.navigation.Screens.Companion.TASK_ARGUMENT_KEY
import com.example.todoapp_android_kotlin_compose.navigation.Screens.Companion.TASK_SCREEN
import com.example.todoapp_android_kotlin_compose.ui.screens.task.TaskScreen
import com.example.todoapp_android_kotlin_compose.ui.viewmodels.SharedViewModel
import com.example.todoapp_android_kotlin_compose.util.Action

@ExperimentalAnimationApi
fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit,
    showAd: () -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(
            navArgument(TASK_ARGUMENT_KEY) {
                type = NavType.IntType
            }
        )
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        LaunchedEffect(key1 = taskId) {
            sharedViewModel.getSelectedTask(taskId = taskId)
        }
        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask) {
            if (selectedTask != null || taskId == -1) {
                sharedViewModel.updateTaskFields(selectedTask = selectedTask)
            }
        }

        LaunchedEffect(Unit) {
            if (sharedViewModel.removeAdsActive.not() && sharedViewModel.premiumActive.not()) {
                showAd()
            }
        }

        TaskScreen(
            navigateToListScreen = navigateToListScreen,
            sharedViewModel = sharedViewModel,
            selectedTask = selectedTask
        )
    }
}
