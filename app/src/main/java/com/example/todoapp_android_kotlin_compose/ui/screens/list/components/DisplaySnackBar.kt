package com.example.todoapp_android_kotlin_compose.ui.screens.list.components

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.example.todoapp_android_kotlin_compose.util.Action
import kotlinx.coroutines.launch

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    handleDatabaseActions: () -> Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
) {
    handleDatabaseActions()

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION){
             scope.launch {
                 val snackarResult = scaffoldState.snackbarHostState.showSnackbar(
                     message = setMessage(
                         action = action,
                         taskTitle = taskTitle
                     ),
                     actionLabel = setActionLabel(action = action)
                 )
                 undoDeletedTask(
                     action = action,
                     snackBarResult = snackarResult,
                     onUndoClicked = onUndoClicked
                 )
             }
        }
    }
}

private fun setMessage(action: Action, taskTitle: String): String {
    return when (action) {
        Action.DELETE_ALL -> "All Tasks Removed"
        else -> "${action.name}: $taskTitle"
    }
}

private fun setActionLabel(action: Action): String {
    return if (action.name == "DELETE"){
        "UNDO"
    } else {
        "OK"
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if(snackBarResult == SnackbarResult.ActionPerformed &&
           action == Action.DELETE ) {
        onUndoClicked(Action.UNDO)
    }
}
