package com.example.todoapp_android_kotlin_compose.ui.screens.list.app_bar

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.todoapp_android_kotlin_compose.R
import com.example.todoapp_android_kotlin_compose.components.DisplayAlertDialog
import com.example.todoapp_android_kotlin_compose.components.DisplayDialog
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
    val context = LocalContext.current
    var openDeleteAllDialog by remember { mutableStateOf(false) }
    var openAboutDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        title = stringResource(id = R.string.remove_all_tasks),
        message = stringResource(id = R.string.remove_all_tasks_confirmation),
        openDialog = openDeleteAllDialog,
        closeDialog = { openDeleteAllDialog = false },
        onYesClicked = { onDeleteAllConfirmed() }
    )

    DisplayDialog(
        openDialog = openAboutDialog,
        closeDialog = { openAboutDialog = false },
        fullText = "Hello, this is Roman's application. This app was made as part of a course in android development. \n" +
                "Blow are all the credits and sources that was used in the making of this application.\n" +
                "\n" +
                "To do icons created by Freepik - Flaticon\n" +
                "Youtube guide Stevdza-San",
        hyperLinks = mutableMapOf(
            "Flaticon" to "https://www.flaticon.com/free-icons/to-do",
            "Stevdza-San" to "https://www.youtube.com/@StevdzaSan"
        ),
        fontSize = 18.sp,
        textStyle = MaterialTheme.typography.body1,
        title = "About TODO-list"
    )

    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    MoreActions(
        onDeleteAllClicked = { openDeleteAllDialog = true },
        onPrivacyPolicy = {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://sites.google.com/view/todo-app-1/%D7%91%D7%99%D7%AA")
            )
            context.startActivity(urlIntent)
        },
        onTermsAndConditionsClicked = {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://sites.google.com/view/todo-app-2/%D7%91%D7%99%D7%AA")
            )
            context.startActivity(urlIntent)
        },
        onAboutClicked = { openAboutDialog = true }
    )
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
            Priority.values().slice(setOf(0, 2, 3)).forEach { priority ->
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
fun MoreActions(
    onDeleteAllClicked: () -> Unit,
    onPrivacyPolicy: () -> Unit,
    onAboutClicked: () -> Unit,
    onTermsAndConditionsClicked: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true }
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.ic_more_vert
            ),
            contentDescription = stringResource(R.string.more_actions),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onPrivacyPolicy()
                }
            ) {
                Text(
                    modifier = Modifier.padding(MaterialTheme.spacing.medium),
                    text = stringResource(R.string.privacy_policy),
                    style = Typography.subtitle2
                )
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onTermsAndConditionsClicked()
                }
            ) {
                Text(
                    modifier = Modifier.padding(MaterialTheme.spacing.medium),
                    text = stringResource(R.string.terms_and_conditions),
                    style = Typography.subtitle2
                )
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onAboutClicked()
                }
            ) {
                Text(
                    modifier = Modifier.padding(MaterialTheme.spacing.medium),
                    text = stringResource(R.string.about),
                    style = Typography.subtitle2
                )
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onDeleteAllClicked()
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
