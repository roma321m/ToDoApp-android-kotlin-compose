package com.example.todoapp_android_kotlin_compose.ui.screens.list.app_bar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.todoapp_android_kotlin_compose.R
import com.example.todoapp_android_kotlin_compose.components.DisplayAlertDialog
import com.example.todoapp_android_kotlin_compose.components.PriorityItem
import com.example.todoapp_android_kotlin_compose.data.models.Priority
import com.example.todoapp_android_kotlin_compose.ui.theme.*


@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllConfirmed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.tasks_title),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        actions = {
            ListAppBarActions(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteAllConfirmed = onDeleteAllConfirmed
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllConfirmed: () -> Unit
) {
    var openDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        title = stringResource(id = R.string.remove_all_tasks),
        message = stringResource(id = R.string.remove_all_tasks_confirmation),
        openDialog = openDialog,
        closeDialog = { openDialog = false },
        onYesClicked = { onDeleteAllConfirmed() }
    )

    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllAction(onDeleteAllConfirmed = { openDialog = true })
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(
        onClick = {
            onSearchClicked()
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search_tasks_button),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true }
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.ic_filter_list
            ),
            contentDescription = stringResource(R.string.sort_list_button),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Priority.values().slice(setOf(0,2,3)).forEach { priority ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onSortClicked(priority)
                    }
                ) {
                    PriorityItem(priority = priority)
                }
            }
        }
    }
}

@Composable
fun DeleteAllAction(
    onDeleteAllConfirmed: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true }
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.ic_more_vert
            ),
            contentDescription = stringResource(R.string.delete_all_tasks_button),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onDeleteAllConfirmed()
                }
            ) {
                Text(
                    modifier = Modifier.padding(MaterialTheme.spacing.medium),
                    text = stringResource(R.string.delete_all_tasks_button),
                    style = Typography.subtitle2
                )
            }
        }
    }
}

@Composable
@DevicePreviews
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(
        onSortClicked = {},
        onDeleteAllConfirmed = {},
        onSearchClicked = {}
    )
}
