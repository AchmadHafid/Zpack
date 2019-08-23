@file:Suppress("unused")

package io.github.achmadhafid.zpack.ktx

import android.content.Intent
import android.content.res.Configuration
import android.os.Handler
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.IntegerRes
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

//region Binding

@MainThread
fun Fragment.stringRes(@StringRes stringRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getString(stringRes)
    }

@MainThread
fun Fragment.stringArrayRes(@ArrayRes arrayRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getStringArray(arrayRes)
    }

@MainThread
fun Fragment.stringListRes(@ArrayRes arrayRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getStringArray(arrayRes)
            .toList()
    }

@MainThread
fun Fragment.intRes(@IntegerRes intRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getInteger(intRes)
    }

@MainThread
fun Fragment.intArrayRes(@ArrayRes arrayRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getIntArray(arrayRes)
    }

@MainThread
fun Fragment.intListRes(@ArrayRes arrayRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getIntArray(arrayRes)
            .toList()
    }

@MainThread
fun Fragment.longRes(@IntegerRes intRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getInteger(intRes)
            .toLong()
    }

@MainThread
fun Fragment.longArrayRes(@ArrayRes arrayRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getIntArray(arrayRes)
            .map { it.toLong() }
            .toLongArray()
    }

@MainThread
fun Fragment.longListRes(@ArrayRes arrayRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getIntArray(arrayRes)
            .map { it.toLong() }
    }

@MainThread
fun Fragment.dimenRes(@DimenRes dimenRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getDimensionPixelSize(dimenRes)
    }

@MainThread
fun Fragment.colorRes(@ColorRes colorRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        requireContext().getColorCompat(colorRes)
    }


//endregion
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

inline val Fragment.hasWriteSettingPermission
    get() = context?.hasWriteSettingPermission

inline val Fragment.hasAppUsagePermission
    get() = context?.hasAppUsagePermission

fun Fragment.isPermissionGranted(permission: String) = context?.isPermissionGranted(permission)

fun Fragment.arePermissionsGranted(permissions: Array<out String>) =
    requireContext().arePermissionsGranted(permissions)

fun Fragment.shouldShowRequestPermissionRationales(permissions: Array<out String>) =
    atLeastMarshmallow() && permissions.any { shouldShowRequestPermissionRationale(it) }

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

inline fun <reified T : FragmentActivity> Fragment.startActivity(noinline block: (Intent.() -> Unit)? = null) {
    context?.startActivity(intent<T>(block))
}

inline fun <reified T: FragmentActivity> Fragment.goTo(noinline block: (Intent.() -> Unit)? = null) {
    startActivity<T>(block)
    activity?.finish()
}

inline val Fragment.isStartDestination
    get() = with(findNavController()) {
        graph.startDestination == currentDestination?.id
    }

fun Fragment.addBackPressedListener(callback: OnBackPressedCallback.() -> Unit) =
    requireActivity().onBackPressedDispatcher
        .addCallback(viewLifecycleOwner, true, callback)

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
        ViewModelProvider(this)
            .get(VM::class.java)
    }

inline fun <reified T: ViewModel> Fragment.getViewModelWithActivityScope() =
    ViewModelProvider(requireActivity()).getViewModel<T>()

inline fun <reified T: ViewModel> Fragment.getViewModel() =
    ViewModelProvider(this).getViewModel<T>()

inline fun <reified T: ViewModel> Fragment.getViewModelWithActivityScope(factory: ViewModelProvider.Factory) =
    ViewModelProvider(requireActivity(), factory).getViewModel<T>()

inline fun <reified T: ViewModel> Fragment.getViewModel(factory: ViewModelProvider.Factory) =
    ViewModelProvider(this, factory).getViewModel<T>()

//endregion
//region Theme

fun Fragment.isDarkThemeEnabled() = resources.configuration.uiMode and
        Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

fun Fragment.toggleTheme() =
    if (isDarkThemeEnabled()) lightTheme()
    else darkTheme()

//endregion
//region Lifecycle

inline val Fragment.lifecycleState
    get() = lifecycle.currentState

inline val Fragment.viewLifecycleState
    get() = viewLifecycleOwner.lifecycle.currentState

//endregion
