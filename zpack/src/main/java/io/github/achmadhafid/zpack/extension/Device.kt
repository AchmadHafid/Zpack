package io.github.achmadhafid.zpack.extension

import android.content.Context
import android.util.DisplayMetrics
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
