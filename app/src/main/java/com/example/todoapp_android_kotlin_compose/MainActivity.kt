package com.example.todoapp_android_kotlin_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import com.example.todoapp_android_kotlin_compose.navigation.SetupNavigation
import com.example.todoapp_android_kotlin_compose.ui.ads.AdMobInterstitial
import com.example.todoapp_android_kotlin_compose.ui.theme.ToDoApp
import com.example.todoapp_android_kotlin_compose.ui.viewmodels.SharedViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalAnimationApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    @Inject lateinit var adMobInterstitialAd: AdMobInterstitial
    private lateinit var navController: NavHostController
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        adMobInterstitialAd.loadAd()

        setContent {
            ToDoApp {
                navController = rememberAnimatedNavController()
                SetupNavigation(
                    navController = navController,
                    sharedViewModel = sharedViewModel,
                    showAd = { adMobInterstitialAd.showAd(this) }
                )
            }
        }
    }
}