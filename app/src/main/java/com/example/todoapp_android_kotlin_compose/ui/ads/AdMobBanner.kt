package com.example.todoapp_android_kotlin_compose.ui.ads

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.viewinterop.AndroidView
import com.example.todoapp_android_kotlin_compose.BuildConfig
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdMobBanner() {
    val currentWidth = LocalConfiguration.current.screenWidthDp
    AndroidView(
        factory = {
            AdView(it).apply {
                setAdSize(AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, currentWidth))
                adUnitId = if (BuildConfig.DEBUG)
                    "ca-app-pub-3940256099942544/6300978111"
                else
                    "ca-app-pub-3940256099942544/6300978111" // todo get actual id
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}