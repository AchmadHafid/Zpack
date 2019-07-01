@file:Suppress("TooManyFunctions", "unused")

package io.github.achmadhafid.zpack.ktx

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate

//region Logging Helper

inline val <reified T> T.TAG: String
    get() = T::class.java.simpleName

inline fun <reified T> T.d(message: String) {
    Log.d(TAG, message)
}

//endregion
//region Android Version Helper

@SuppressLint("ObsoleteSdkInt")
fun belowLollipop()    = Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP
fun belowLollipopMR1() = Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1
fun belowMarshmallow() = Build.VERSION.SDK_INT < Build.VERSION_CODES.M
fun belowNougat()      = Build.VERSION.SDK_INT < Build.VERSION_CODES.N
fun belowNougatMR1()   = Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1
fun belowOreo()        = Build.VERSION.SDK_INT < Build.VERSION_CODES.O
fun belowOreoMR1()     = Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1
fun belowPie()         = Build.VERSION.SDK_INT < Build.VERSION_CODES.P

fun atLeastLollipop()    = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
fun atLeastLollipopMR1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1
fun atLeastMarshmallow() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun atLeastNougat()      = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
fun atLeastNougatMR1()   = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
fun atLeastOreo()        = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
fun atLeastOreoMR1()     = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1
fun atLeastPie()         = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

//endregion
//region Theme Helper

fun applyTheme(theme: Int): Int {
    AppCompatDelegate.setDefaultNightMode(theme)
    return theme
}

fun lightTheme() = applyTheme(AppCompatDelegate.MODE_NIGHT_NO)
fun darkTheme()  = applyTheme(AppCompatDelegate.MODE_NIGHT_YES)

fun defaultTheme() {
    applyTheme(
        when {
            atLeastPie() -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            atLeastLollipop() -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            else -> AppCompatDelegate.MODE_NIGHT_NO
        }
    )
}

//endregion
