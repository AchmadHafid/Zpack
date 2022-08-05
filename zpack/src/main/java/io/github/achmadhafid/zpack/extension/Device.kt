package io.github.achmadhafid.zpack.extension

import android.content.Context
import android.provider.Settings
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

@Suppress("DEPRECATION")
inline val Context.hasSoftNavigationKeys: Boolean
    get() {
        val display = windowManager.defaultDisplay

        val realMetrics = DisplayMetrics()
        display.getRealMetrics(realMetrics)

        val displayMetrics = DisplayMetrics()
        display.getMetrics(displayMetrics)

        return realMetrics.widthPixels > displayMetrics.widthPixels ||
                realMetrics.heightPixels > displayMetrics.heightPixels
    }

inline val Context.isScreenOn
    get() = powerManager.isInteractive

inline val Context.isDeviceLocked
    get() = if (atLeastLollipopMR1()) keyGuardManager.isDeviceLocked
    else keyGuardManager.isKeyguardLocked

inline val Context.displayWidth: Int
    get() = resources.displayMetrics.widthPixels

inline val Context.displayHeight: Int
    get() = resources.displayMetrics.heightPixels

inline val Context.screenHeightPixel: Int
    get() = realDisplayMetrics.heightPixels

inline val Context.screenWidthPixel: Int
    get() = realDisplayMetrics.widthPixels

inline val Context.realDisplayMetrics: DisplayMetrics
    get() = DisplayMetrics().apply {
        if (atLeastR()) {
            windowManager.currentWindowMetrics
        } else {
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay.getRealMetrics(this)
        }
    }

inline val Context.statusBarHeight
    get() = runCatching {
        resources.getIdentifier(
            "status_bar_height",
            "dimen",
            "android"
        ).run {
            resources.getDimensionPixelSize(this)
        }
    }.getOrNull()

inline val Context.navigationBarHeight
    get() = runCatching {
        resources.getIdentifier(
            "navigation_bar_height",
            "dimen",
            "android"
        ).run {
            resources.getDimensionPixelSize(this)
        }
    }.getOrNull()

inline val FragmentActivity.actionBarHeight: Int
    @Suppress("MagicNumber")
    get() = theme.obtainStyledAttributes(IntArray(100)).let {
        val height = it.getDimension(0, 0.0f).toInt()
        it.recycle()
        height
    }

inline val Fragment.screenHeight: Int
    get() = if (atLeastR()) {
        requireContext().windowManager
            .currentWindowMetrics
            .bounds
            .height()
    } else DisplayMetrics().apply {
        @Suppress("DEPRECATION")
        requireContext().windowManager
            .defaultDisplay
            .getRealMetrics(this)
    }.heightPixels

inline val Context.isAirplaneModeActive: Boolean
    get() = Settings.System.getInt(contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
