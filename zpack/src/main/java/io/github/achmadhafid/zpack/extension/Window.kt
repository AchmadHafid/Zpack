@file:Suppress("TooManyFunctions")

package io.github.achmadhafid.zpack.extension

import android.annotation.SuppressLint
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment

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

fun Fragment.resetSystemBarIconsAndButtonsColor() {
    activity?.window?.setLightSystemBar(requireContext().isDarkThemeEnabled.not())
}

fun AppCompatActivity.resetSystemBarIconsAndButtonsColor() {
    window?.setLightSystemBar(isDarkThemeEnabled.not())
}

fun Fragment.setLightSystemBarIconsAndButtons(isLight: Boolean) {
    activity?.window?.setLightSystemBar(isLight.not())
}

fun AppCompatActivity.setLightSystemBarIconsAndButtons(isLight: Boolean) {
    window?.setLightSystemBar(isLight.not())
}

fun Fragment.resetStatusBarIconsColor() {
    setLightStatusBarIcons(requireContext().isDarkThemeEnabled)
}

fun AppCompatActivity.resetStatusBarIconsColor() {
    setLightStatusBarIcons(isDarkThemeEnabled)
}

fun Fragment.resetNavBarButtonsColor() {
    setLightNavBarButtons(requireContext().isDarkThemeEnabled)
}

fun AppCompatActivity.resetNavBarButtonsColor() {
    setLightNavBarButtons(isDarkThemeEnabled)
}

@Suppress("DEPRECATION", "NestedBlockDepth")
fun Fragment.setLightStatusBarIcons(isLight: Boolean) {
    appCompatActivity?.setLightStatusBarIcons(isLight)
}

@Suppress("DEPRECATION", "NestedBlockDepth")
fun AppCompatActivity.setLightStatusBarIcons(isLight: Boolean) {
    window?.apply {
        if (atLeastR()) {
            val lightStatusBar = WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            insetsController?.apply {
                setSystemBarsAppearance(if (isLight) 0 else lightStatusBar, lightStatusBar)
            }
        } else with(decorView) {
            val flags = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            systemUiVisibility = if (isLight) {
                systemUiVisibility and flags.inv()
            } else systemUiVisibility or flags
        }
    }
}

@Suppress("DEPRECATION", "NestedBlockDepth")
fun Fragment.setLightNavBarButtons(isLight: Boolean) {
    appCompatActivity?.setLightNavBarButtons(isLight)
}

@Suppress("DEPRECATION", "NestedBlockDepth")
fun AppCompatActivity.setLightNavBarButtons(isLight: Boolean) {
    window?.apply {
        if (atLeastR()) {
            val lightNavBar = WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
            insetsController?.apply {
                setSystemBarsAppearance(if (isLight) 0 else lightNavBar, lightNavBar)
            }
        } else with(decorView) {
            val flags = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            systemUiVisibility = if (isLight) {
                systemUiVisibility and flags.inv()
            } else systemUiVisibility or flags
        }
    }
}

@Suppress("DEPRECATION")
private fun Window.setLightSystemBar(isLight: Boolean) {
    if (atLeastR()) {
        val lightStatusBar = WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        val lightNavBar = WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS

        insetsController?.apply {
            setSystemBarsAppearance(if (isLight) lightStatusBar else 0, lightStatusBar)
            setSystemBarsAppearance(if (isLight) lightNavBar else 0, lightNavBar)
        }
    } else with(decorView) {
        val flags = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR

        systemUiVisibility = if (isLight) {
            systemUiVisibility or flags
        } else systemUiVisibility and flags.inv()
    }
}
