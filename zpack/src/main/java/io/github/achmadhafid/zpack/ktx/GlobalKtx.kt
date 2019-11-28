@file:Suppress("unused")

package io.github.achmadhafid.zpack.ktx

import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate

//region Logging

inline val <reified T> T.TAG: String
    get() = T::class.java.simpleName

inline fun <reified T> T.d(message: String) {
    Log.d(TAG, message)
}

inline fun <reified T> T.e(message: String) {
    Log.e(TAG, message)
}

inline fun <reified T> T.i(message: String) {
    Log.i(TAG, message)
}

inline fun <reified T> T.w(message: String) {
    Log.w(TAG, message)
}

inline fun <reified T> T.v(message: String) {
    Log.v(TAG, message)
}

//endregion
//region Android Version

fun belowLollipopMR1() = Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1
fun belowMarshmallow() = Build.VERSION.SDK_INT < Build.VERSION_CODES.M
fun belowNougat()      = Build.VERSION.SDK_INT < Build.VERSION_CODES.N
fun belowNougatMR1()   = Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1
fun belowOreo()        = Build.VERSION.SDK_INT < Build.VERSION_CODES.O
fun belowOreoMR1()     = Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1
fun belowPie()         = Build.VERSION.SDK_INT < Build.VERSION_CODES.P
fun belowQ()           = Build.VERSION.SDK_INT < Build.VERSION_CODES.Q

fun atLeastLollipopMR1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1
fun atLeastMarshmallow() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun atLeastNougat()      = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
fun atLeastNougatMR1()   = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
fun atLeastOreo()        = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
fun atLeastOreoMR1()     = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1
fun atLeastPie()         = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
fun atLeastQ()           = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

//endregion
//region Theme

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
            else -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
        }
    )
}

//endregion
//region Permissions

fun arePermissionsGranted(grantResults: IntArray) =
    belowMarshmallow() || grantResults.all { it == PackageManager.PERMISSION_GRANTED }

//endregion
//region Null Check

fun <T : Any> ifAllWereNull(vararg list: T?, function: () -> Unit) {
    if (list.all { it == null }) function()
}

fun <T : Any> ifAllWereNotNull(vararg list: T?, function: List<T>.() -> Unit) {
    if (list.all { it != null }) function(list.map { it!! })
}

fun <T : Any> ifAnyWasNull(vararg list: T?, function: List<T?>.() -> Unit) {
    if (list.any { it == null }) function(list.toList())
}

fun <T : Any> ifAnyWasNotNull(vararg list: T?, function: List<T?>.() -> Unit) {
    if (list.any { it != null }) function(list.toList())
}

//endregion
//region View Util

fun switchViews(vararg list: Triple<View?, View?, () -> Boolean>) {
    list.forEach { (view1, view2, show1) ->
        if (show1()) {
            view2?.gone()
            view1?.show()
        } else {
            view1?.gone()
            view2?.show()
        }
    }
}

//endregion