package com.example.todoapp_android_kotlin_compose.ui.screens.list.components

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.example.todoapp_android_kotlin_compose.util.Action
import kotlinx.coroutines.launch

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    handleDatabaseActions: () -> Unit,
    taskTitle: String,
    action: Action
) {
    handleDatabaseActions()

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION){
             scope.launch {
                 val snackarResult = scaffoldState.snackbarHostState.showSnackbar(
                     /* Todo */
                     message = "${action.name}: $taskTitle",
                     actionLabel = "Ok"
                 )
             }
        }
    }
}