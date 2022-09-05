package com.example.todoapp_android_kotlin_compose.ui.screens.splash

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.todoapp_android_kotlin_compose.R
import com.example.todoapp_android_kotlin_compose.ui.theme.LOGO_SIZE
import com.example.todoapp_android_kotlin_compose.ui.theme.splashScreenBackground
import com.example.todoapp_android_kotlin_compose.util.Constants.SPLASH_SCREEN_ANIMATION_TIME_MILLIS
import com.example.todoapp_android_kotlin_compose.util.Constants.SPLASH_SCREEN_TIME_MILLIS
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToListScreen: () -> Unit
) {
    var startAnimation by remember { mutableStateOf(false) }
    val offsetState by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else 100.dp,
        animationSpec = tween(
            durationMillis = SPLASH_SCREEN_ANIMATION_TIME_MILLIS
        )
    )
    val alphaState by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = SPLASH_SCREEN_ANIMATION_TIME_MILLIS
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(SPLASH_SCREEN_TIME_MILLIS.toLong())
        navigateToListScreen()
    }
    
    Splash(offsetState = offsetState, alphaState = alphaState)
}

@Composable
fun Splash(
    offsetState: Dp,
    alphaState: Float
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.splashScreenBackground),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(LOGO_SIZE)
                .offset(y = offsetState)
                .alpha(alpha = alphaState),
            painter = painterResource(id = getLogo()),
            contentDescription = stringResource(R.string.splash_logo)
        )
    }
}

@Composable
fun getLogo(): Int {
    return if (isSystemInDarkTheme())
        R.drawable.ic_logo_dark
    else
        R.drawable.ic_logo_light
}

@Preview
@Composable
private fun SplashScreenPreview() {
    Splash(offsetState = 0.dp, alphaState = 1f)
}
