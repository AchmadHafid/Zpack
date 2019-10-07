@file:Suppress("unused")

package io.github.achmadhafid.zpack.ktx

import android.view.Window
import android.view.WindowManager

fun Window.adjustKeyboard(isVisible: Boolean = false) {
    val state =
        if (isVisible) WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        else WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN

    setSoftInputMode(state or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
}

fun Window.fullScreen() {
    setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
}
