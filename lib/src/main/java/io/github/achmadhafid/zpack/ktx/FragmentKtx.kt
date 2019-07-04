@file:Suppress("TooManyFunctions", "unused")

package io.github.achmadhafid.zpack.ktx

import android.content.Intent
import android.content.res.Configuration
import android.os.Handler
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

//region Toast

fun Fragment.toastShort(message: CharSequence) {
    context?.toastShort(message)
}

fun Fragment.toastShort(@StringRes messageRes: Int) {
    context?.toastShort(messageRes)
}

fun Fragment.toastShort(@StringRes messageRes: Int, vararg messages: CharSequence) {
    context?.toastShort(messageRes, *messages)
}

fun Fragment.toastLong(message: CharSequence) {
    context?.toastLong(message)
}

fun Fragment.toastLong(@StringRes messageRes: Int) {
    context?.toastLong(messageRes)
}

fun Fragment.toastLong(@StringRes messageRes: Int, vararg messages: CharSequence) {
    context?.toastLong(messageRes, *messages)
}

//endregion
//region Permission

fun Fragment.isGranted(permission: String) = context?.isGranted(permission)

inline val Fragment.hasWriteSettingPermission
    get() = context?.hasWriteSettingPermission

inline val Fragment.hasAppUsagePermission
    get() = context?.hasAppUsagePermission

//endregion
//region Intent

inline fun <reified T : Any> Fragment.intent(noinline block: (Intent.() -> Unit)? = null): Intent? {
    return context?.let {
        val intent = Intent(it, T::class.java)
        if (block != null) intent.apply(block)
        intent
    }
}

fun Fragment.intent(action: String, block: (Intent.() -> Unit)? = null): Intent =
    if (block == null) Intent(action) else Intent(action).apply(block)

//endregion
//region Navigation

inline fun <reified T : AppCompatActivity> Fragment.startActivity(noinline block: (Intent.() -> Unit)? = null) {
    context?.startActivity(intent<T>(block))
}

inline fun <reified T: AppCompatActivity> Fragment.goTo(noinline block: (Intent.() -> Unit)? = null) {
    startActivity<T>(block)
    activity?.finish()
}

inline val Fragment.isStartDestination
    get() = with(findNavController()) {
        graph.startDestination == currentDestination?.id
    }

fun Fragment.finishActivityOnDoubleBackPressed(
    message: String,
    handler: Handler,
    delayMilis: Long
): OnBackPressedCallback? = when {
    isStartDestination -> requireActivity().onBackPressedDispatcher
        .addCallback(viewLifecycleOwner) {
            toastShort(message)
            isEnabled = false
            handler.postDelayed({ isEnabled = true }, delayMilis)
        }
    else -> null
}

fun Fragment.finishActivityOnDoubleBackPressed(
    @StringRes messageRes: Int,
    handler: Handler,
    delayMilis: Long
) = finishActivityOnDoubleBackPressed(getString(messageRes), handler, delayMilis)

fun Fragment.finishActivityOnBackPressed() =
    requireActivity().onBackPressedDispatcher
        .addCallback(viewLifecycleOwner) { activity?.finish() }

//endregion
//region ViewModel

@MainThread
inline fun <reified VM : ViewModel> Fragment.bindViewModel() =
    lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this)
            .get(VM::class.java)
    }

//endregion
//region Theme

fun Fragment.isDarkThemeEnabled() = resources.configuration.uiMode and
        Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

fun Fragment.toggleTheme() =
    if (isDarkThemeEnabled()) lightTheme()
    else darkTheme()

//endregion
