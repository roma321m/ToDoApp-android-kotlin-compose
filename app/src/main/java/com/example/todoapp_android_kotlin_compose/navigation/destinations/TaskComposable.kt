package com.example.todoapp_android_kotlin_compose.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.todoapp_android_kotlin_compose.navigation.Screens.Companion.TASK_ARGUMENT_KEY
import com.example.todoapp_android_kotlin_compose.navigation.Screens.Companion.TASK_SCREEN
import com.example.todoapp_android_kotlin_compose.ui.screens.task.TaskScreen
import com.example.todoapp_android_kotlin_compose.ui.viewmodels.SharedViewModel
import com.example.todoapp_android_kotlin_compose.util.Action
import com.example.todoapp_android_kotlin_compose.util.Constants.ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit,
    showAd: () -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        }),
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(
                    durationMillis = ENTER_NAVIGATION_ANIMATION_TIME_MILLIS
                ),
                initialOffsetX = { fullWidth -> -fullWidth }
            )
        }
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
