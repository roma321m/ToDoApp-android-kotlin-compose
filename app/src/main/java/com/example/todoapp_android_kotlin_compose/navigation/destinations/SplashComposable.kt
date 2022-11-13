package com.example.todoapp_android_kotlin_compose.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.example.todoapp_android_kotlin_compose.navigation.Screens.Companion.SPLASH_SCREEN
import com.google.accompanist.navigation.animation.composable
import com.example.todoapp_android_kotlin_compose.ui.screens.splash.SplashScreen
import com.example.todoapp_android_kotlin_compose.util.Constants.EXIT_NAVIGATION_ANIMATION_TIME_MILLIS

@ExperimentalAnimationApi
fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(
        route = SPLASH_SCREEN,
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(EXIT_NAVIGATION_ANIMATION_TIME_MILLIS)
            )
        }
    ) {
        SplashScreen(navigateToListScreen = navigateToListScreen)
    }
}
