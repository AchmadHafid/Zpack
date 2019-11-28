@file:Suppress("unused")

package io.github.achmadhafid.zpack.ktx

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.IntegerRes
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlin.reflect.KClass

//region Resource Binding

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
    requireContext().toastShort(message)
}

fun Fragment.toastShort(@StringRes messageRes: Int) {
    requireContext().toastShort(messageRes)
}

fun Fragment.toastShort(@StringRes messageRes: Int, vararg messages: CharSequence) {
    requireContext().toastShort(messageRes, *messages)
}

fun Fragment.toastLong(message: CharSequence) {
    requireContext().toastLong(message)
}

fun Fragment.toastLong(@StringRes messageRes: Int) {
    requireContext().toastLong(messageRes)
}

fun Fragment.toastLong(@StringRes messageRes: Int, vararg messages: CharSequence) {
    requireContext().toastLong(messageRes, *messages)
}

//endregion
//region Permission

inline val Fragment.hasWriteSettingPermission
    get() = requireContext().hasWriteSettingPermission

inline val Fragment.hasAppUsagePermission
    get() = requireContext().hasAppUsagePermission

fun Fragment.isPermissionGranted(permission: String) = requireContext().isPermissionGranted(permission)

fun Fragment.arePermissionsGranted(permissions: Array<out String>) =
    requireContext().arePermissionsGranted(permissions)

fun Fragment.shouldShowRequestPermissionRationales(permissions: Array<out String>) =
    atLeastMarshmallow() && permissions.any { shouldShowRequestPermissionRationale(it) }

//endregion
//region Intent

inline fun <reified T : Any> Fragment.intent(noinline block: (Intent.() -> Unit)? = null): Intent {
    val intent = Intent(requireContext(), T::class.java)
    if (block != null) intent.apply(block)
    return intent
}

fun Fragment.intent(action: String, block: (Intent.() -> Unit)? = null): Intent =
    if (block == null) Intent(action) else Intent(action).apply(block)

//endregion
//region Service

inline fun <reified T : Service> Fragment.startService(noinline block: (Intent.() -> Unit)? = null): ComponentName? =
    requireContext().startService<T>(block)

inline fun <reified T : Service> Fragment.startForegroundService(
    requestCode: Int,
    noinline block: (Intent.() -> Unit)? = null
) {
    if (atLeastPie()) {
        requestPermissions(arrayOf(Manifest.permission.FOREGROUND_SERVICE), requestCode)
    }
    requireContext().startForegroundServiceCompat<T>(block)
}

fun <S : Service> Fragment.startForegroundService(
    clazz: KClass<S>,
    requestCode: Int,
    block: Intent.() -> Unit = {}
) {
    @TargetApi(Build.VERSION_CODES.P)
    if (atLeastPie()) {
        requestPermissions(arrayOf(Manifest.permission.FOREGROUND_SERVICE), requestCode)
    }
    with(requireContext()) {
        ContextCompat.startForegroundService(this, Intent(this, clazz.java).apply(block))
    }
}

inline fun <reified T : Service> Fragment.stopService() =
    requireContext().stopService<T>()

//endregion
//region Navigation

fun Fragment.finish() = findNavController().popBackStack()

fun Fragment.activityFinish() {
    requireActivity().finish()
}

inline fun <reified T : Activity> Fragment.startActivity(noinline block: (Intent.() -> Unit)? = null) {
    requireContext().startActivity(intent<T>(block))
}

inline fun <reified T: Activity> Fragment.goTo(noinline block: (Intent.() -> Unit)? = null) {
    startActivity<T>(block)
    activity?.finish()
}

inline fun <reified T: Activity> Fragment.startActivityForResult(
    requestCode: Int,
    noinline block: Intent.() -> Unit = {}
) {
    bundleOf()
    startActivityForResult(intent<T>(block), requestCode)
}

inline fun <reified T: Activity> Fragment.startActivityForResult(
    requestCode: Int,
    noinline block: Intent.() -> Unit = {},
    noinline bundle: Bundle.() -> Unit = {}
) {
    startActivityForResult(intent<T>(block), requestCode, bundleOf().apply(bundle))
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

inline fun <reified T: ViewModel> Fragment.getViewModel() =
    ViewModelProvider(this).getViewModel<T>()

inline fun <reified T: ViewModel> Fragment.getViewModel(factory: ViewModelProvider.Factory) =
    ViewModelProvider(this, factory).getViewModel<T>()

inline fun <reified T: ViewModel> Fragment.getViewModelWithActivityScope() =
    ViewModelProvider(requireActivity()).getViewModel<T>()

inline fun <reified T: ViewModel> Fragment.getViewModelWithActivityScope(factory: ViewModelProvider.Factory) =
    ViewModelProvider(requireActivity(), factory).getViewModel<T>()

inline fun <reified T: ViewModel> Fragment.getViewModelWithParentScope() =
    ViewModelProvider(requireParentFragment()).getViewModel<T>()

inline fun <reified T: ViewModel> Fragment.getViewModelWithParentScope(factory: ViewModelProvider.Factory) =
    ViewModelProvider(requireParentFragment(), factory).getViewModel<T>()

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
//region Internet Connection

inline val Fragment.isConnected: Boolean?
    get() = requireContext().isConnected

inline val Fragment.isMobileDataEnabled: Boolean?
    get() = requireContext().isMobileDataEnabled

inline val Fragment.isWifiEnabled
    get() = requireContext().isWifiEnabled

//endregion
//region Uri

fun Fragment.deleteLocalUri(uri: Uri) {
    requireContext().deleteLocalUri(uri)
}

//endregion