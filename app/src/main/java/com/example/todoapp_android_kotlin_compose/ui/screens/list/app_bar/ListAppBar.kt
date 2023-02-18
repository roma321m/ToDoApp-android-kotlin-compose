package com.example.todoapp_android_kotlin_compose.ui.screens.list.app_bar

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.todoapp_android_kotlin_compose.ui.viewmodels.SharedViewModel
import com.example.todoapp_android_kotlin_compose.util.Action
import com.example.todoapp_android_kotlin_compose.util.SearchAppBarState
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String
) {
    val context = LocalContext.current
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchClicked = {
                    sharedViewModel.updateSearchAppBarState(newState = SearchAppBarState.OPENED)
                },
                onSortClicked = { sharedViewModel.persistSortState(it) },
                onDeleteAllConfirmed = {
                    sharedViewModel.updateAction(newAction = Action.DELETE_ALL)
                },
                onGoPremiumClicked = { sharedViewModel.premium(context) },
                onRemoveAdsClicked = { sharedViewModel.removeAds() },
                premiumActive = sharedViewModel.premiumActive,
                removeAdsActive = sharedViewModel.removeAdsActive
            )
        }
        else -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = { newText ->
                    sharedViewModel.updateSearchTextState(newText = newText)
                },
                onCloseClicked = {
                    sharedViewModel.updateSearchAppBarState(newState = SearchAppBarState.CLOSED)
                    sharedViewModel.updateSearchTextState(newText = "")
                },
                onSearchClicked = {
                    sharedViewModel.searchDatabase(searchQuery = it)
                }
            )
        }
    }
}
