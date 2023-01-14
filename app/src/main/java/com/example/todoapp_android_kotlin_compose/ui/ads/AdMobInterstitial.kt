package com.example.todoapp_android_kotlin_compose.ui.ads

import android.app.Activity
import android.content.Context
import com.example.todoapp_android_kotlin_compose.BuildConfig
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import javax.inject.Inject

class AdMobInterstitial @Inject constructor(
    private val context: Context
) {
    var mInterstitialAd: InterstitialAd? = null

    fun loadAd() {
        val adUnitId = if (BuildConfig.DEBUG)
            "ca-app-pub-3940256099942544/1033173712"
        else
            "ca-app-pub-3940256099942544/1033173712" // todo - actual ad id
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(context, adUnitId, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(ad: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(ad: InterstitialAd) {
                mInterstitialAd = ad
            }
        })
    }

    fun showAd(activity: Activity) {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    mInterstitialAd = null
                    loadAd()
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    super.onAdFailedToShowFullScreenContent(p0)
                    mInterstitialAd = null
                }

                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                }
            }
            mInterstitialAd?.show(activity)
        } else {
            loadAd()
        }
    }
}