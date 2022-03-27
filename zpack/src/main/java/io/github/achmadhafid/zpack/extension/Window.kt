package io.github.achmadhafid.zpack.extension

import android.annotation.SuppressLint
import android.view.View
import android.view.WindowInsets
import androidx.core.view.ViewCompat

@SuppressLint("WrongConstant")
fun View.onApplySystemBarWindowInsets(action: (topInset: Int, bottomInset: Int) -> Unit) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { _, windowInsets ->
        val topInset = if (atLeastR()) {
            windowInsets.getInsets(WindowInsets.Type.systemBars()).top
        } else {
            @Suppress("DEPRECATION")
            windowInsets.systemWindowInsetTop
        }

        val bottomInset = if (atLeastR()) {
            windowInsets.getInsets(WindowInsets.Type.systemBars()).bottom
        } else {
            @Suppress("DEPRECATION")
            windowInsets.systemWindowInsetBottom
        }

        action(topInset, bottomInset)

        windowInsets
    }
}
