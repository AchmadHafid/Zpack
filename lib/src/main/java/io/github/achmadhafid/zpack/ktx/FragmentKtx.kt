@file:Suppress("TooManyFunctions", "unused")

package io.github.achmadhafid.zpack.ktx

import android.content.Intent
import android.content.res.Configuration
import android.os.Handler
import androidx.activity.addCallback
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

//region Toast Helper

fun Fragment.toastShort(message: CharSequence) {
    context?.toastShort(message)
}

fun Fragment.toastShort(@StringRes messageRes: Int) {
    context?.toastShort(messageRes)
}

fun Fragment.toastLong(message: CharSequence) {
    context?.toastLong(message)
}

fun Fragment.toastLong(@StringRes messageRes: Int) {
    context?.toastLong(messageRes)
}

//endregion
//region Navigation Helper

@MainThread
inline fun <reified T: AppCompatActivity> Fragment.open() {
    context?.startActivity<T>()
}

@MainThread
inline fun <reified T: AppCompatActivity> Fragment.open(block: Intent.() -> Unit) =
    context?.startActivity<T>(block)

@MainThread
inline fun <reified T: AppCompatActivity> Fragment.goTo() {
    open<T>()
    activity?.finish()
}

@MainThread
inline fun <reified T: AppCompatActivity> Fragment.goTo(block: Intent.() -> Unit) {
    open<T>(block)
    activity?.finish()
}

fun Fragment.isStartDestination() =
    with(findNavController()) {
        graph.startDestination == currentDestination?.id
    }

fun Fragment.finishActivityOnDoubleBackPressed(message: String, handler: Handler, delayMilis: Long) {
    if (isStartDestination()) {
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner) {
                toastShort(message)
                isEnabled = false
                handler.postDelayed({ isEnabled = true }, delayMilis)
            }
    }
}

fun Fragment.finishActivityOnDoubleBackPressed(@StringRes messageRes: Int, handler: Handler, delayMilis: Long) {
    finishActivityOnDoubleBackPressed(getString(messageRes), handler, delayMilis)
}

fun Fragment.finishActivityOnBackPressed() {
    requireActivity().onBackPressedDispatcher
        .addCallback(viewLifecycleOwner) { activity?.finish() }
}

//endregion
//region ViewModel Helper

@MainThread
inline fun <reified VM : ViewModel> Fragment.bindViewModel() =
    lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this)
            .get(VM::class.java)
    }

//endregion
//region Theme Helper

fun Fragment.isDarkThemeEnable() = resources.configuration.uiMode and
        Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

fun Fragment.toggleTheme() =
    if (isDarkThemeEnable()) lightTheme()
    else darkTheme()

//endregion
