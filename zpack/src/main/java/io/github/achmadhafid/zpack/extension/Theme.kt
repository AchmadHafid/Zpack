package io.github.achmadhafid.zpack.extension

import android.content.Context
import android.content.res.Configuration
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

fun applyTheme(theme: Int): Int {
    AppCompatDelegate.setDefaultNightMode(theme)
    return theme
}

fun lightTheme() = applyTheme(AppCompatDelegate.MODE_NIGHT_NO)

fun darkTheme() = applyTheme(AppCompatDelegate.MODE_NIGHT_YES)

fun defaultTheme() {
    applyTheme(
        when {
            atLeastPie() -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            else -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
        }
    )
}

inline val Context.isDarkThemeEnabled
    get() = resources.configuration.uiMode and
        Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

fun AppCompatActivity.toggleTheme() =
    if (isDarkThemeEnabled) lightTheme()
    else darkTheme()

@Suppress("DEPRECATION")
fun View.setLightNavBar(isLight: Boolean) {
    systemUiVisibility = if (isLight) {
        systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
    } else systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
}
