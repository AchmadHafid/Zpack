package io.github.achmadhafid.zpack.extension

import android.annotation.SuppressLint
import android.content.Context

inline val Context.isConnected: Boolean?
    get() {
        if (atLeastQ()) d(
            "This function is deprecated in Q, please use NetworkCallback" +
                "\nSee: https://developer.android.com/reference/android/net/NetworkInfo.html"
        )
        @Suppress("DEPRECATION")
        return connectivityManager.activeNetworkInfo?.isConnectedOrConnecting
    }

inline val Context.isMobileDataEnabled: Boolean?
    @SuppressLint("MissingPermission")
    get() = telephonyManager.isDataEnabled

inline val Context.isWifiEnabled
    get() = wifiManager.isWifiEnabled
