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
    onDeleteAllConfirmed: () -> Unit,
    onGoPremiumClicked: () -> Unit,
    onRemoveAdsClicked: () -> Unit,
    premiumActive: Boolean = false,
    removeAdsActive: Boolean = false
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
                onDeleteAllConfirmed = onDeleteAllConfirmed,
                onGoPremiumClicked = onGoPremiumClicked,
                onRemoveAdsClicked = onRemoveAdsClicked,
                premiumActive = premiumActive,
                removeAdsActive = removeAdsActive
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllConfirmed: () -> Unit,
    onGoPremiumClicked: () -> Unit,
    onRemoveAdsClicked: () -> Unit,
    premiumActive: Boolean,
    removeAdsActive: Boolean
) {
    val context = LocalContext.current
    var openDeleteAllDialog by remember { mutableStateOf(false) }
    var openAboutDialog by remember { mutableStateOf(false) }
    var openAdsDialog by remember { mutableStateOf(false) }
    var openPremiumDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        title = stringResource(id = R.string.remove_all_tasks),
        message = stringResource(id = R.string.remove_all_tasks_confirmation),
        openDialog = openDeleteAllDialog,
        closeDialog = { openDeleteAllDialog = false },
        onYesClicked = { onDeleteAllConfirmed() }
    )

    DisplayAlertDialog(
        title = "Remove All Ads For 5$",
        message = "You can remove all the ads in the application with a one time purchase!",
        openDialog = openAdsDialog,
        closeDialog = { openAdsDialog = false },
        onYesClicked = { onRemoveAdsClicked() }
    )

    val premiumTitle = if (premiumActive)
        "Remove Premium Subscription"
    else
        "Go Premium For Only 5$ A Month"
    val premiumMessage = if (premiumActive)
        "Are you sure you want to remove the premium subscription? \n" +
                "You will lose the following functionality: \n" +
                "* Google Drive backups\n" +
                "* Sync across multiple devices\n" +
                "* Ads-free experience"
    else
        "With our premium package you will get \n" +
                "* Google Drive backups\n" +
                "* Sync across multiple devices\n" +
                "* Ads-free experience\n" +
                "Start now and get 7 days free of charge!"

    DisplayAlertDialog(
        title = premiumTitle,
        message = premiumMessage,
        openDialog = openPremiumDialog,
        closeDialog = { openPremiumDialog = false },
        onYesClicked = { onGoPremiumClicked() }
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
    ShopAction(
        onGoPremiumClicked = { openPremiumDialog = true },
        onRemoveAdsClicked = { openAdsDialog = true },
        premiumActive = premiumActive,
        removeAdsActive = removeAdsActive
    )
    SortAction(onSortClicked = onSortClicked)
    val ppUri = stringResource(R.string.p_p_uri)
    val tcUri = stringResource(R.string.t_c_uri)
    MoreActions(
        onDeleteAllClicked = { openDeleteAllDialog = true },
        onPrivacyPolicy = {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(ppUri)
            )
            context.startActivity(urlIntent)
        },
        onTermsAndConditionsClicked = {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(tcUri)
            )
            context.startActivity(urlIntent)
        },
        onAboutClicked = { openAboutDialog = true }
    )
}

@Composable
fun ShopAction(
    onGoPremiumClicked: () -> Unit,
    onRemoveAdsClicked: () -> Unit,
    premiumActive: Boolean = false,
    removeAdsActive: Boolean = false
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true }
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.ic_outline_shopping_cart
            ),
            contentDescription = stringResource(R.string.shop),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            if (premiumActive.not() && removeAdsActive.not()) {
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onRemoveAdsClicked()
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(MaterialTheme.spacing.medium),
                        text = stringResource(R.string.remove_ads),
                        style = Typography.subtitle2
                    )
                }
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onGoPremiumClicked()
                }
            ) {
                val text = if (premiumActive)
                    stringResource(R.string.remove_premium)
                else
                    stringResource(R.string.go_premium)
                Text(
                    modifier = Modifier.padding(MaterialTheme.spacing.medium),
                    text = text,
                    style = Typography.subtitle2
                )
            }
        }
    }
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
        onSearchClicked = {},
        onRemoveAdsClicked = {},
        onGoPremiumClicked = {}
    )
}
