package io.github.achmadhafid.zpack.extension

import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Window.adjustKeyboard(isVisible: Boolean = false) {
    val state =
        if (isVisible) WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        else WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN

    @Suppress("DEPRECATION")
    setSoftInputMode(state or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
}

fun Fragment.dismissKeyboard() =
    context?.inputMethodManager?.hideSoftInputFromWindow(view?.rootView?.windowToken, 0)

fun AppCompatActivity.dismissKeyboard() =
    inputMethodManager.hideSoftInputFromWindow(window?.decorView?.windowToken, 0)
