@file:Suppress("unused")

package io.github.achmadhafid.zpack.ktx

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Service
import android.content.Intent
import android.content.res.Configuration
import android.os.Handler
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.appbar.MaterialToolbar

//region Permissions

@SuppressLint("NewApi")
fun Activity.shouldShowRequestPermissionRationales(permissions: Array<out String>) =
    atLeastMarshmallow() && permissions.any { shouldShowRequestPermissionRationale(it) }

fun Activity.requestPermissionCompat(permission: Array<out String>, requestCode: Int) =
    ActivityCompat.requestPermissions(this, permission, requestCode)

//endregion
//region Resource Binding

@MainThread
inline fun <reified V : View> Activity.bindView(@IdRes id: Int) =
    lazy(LazyThreadSafetyMode.NONE) { findViewById<V>(id) }

//endregion
//region Service

inline fun <reified T : Service> Activity.startForegroundServiceCompat(
    requestCode: Int,
    noinline block: (Intent.() -> Unit)? = null
) {
    if (atLeastPie()) {
        requestPermissionCompat(arrayOf(Manifest.permission.FOREGROUND_SERVICE), requestCode)
    }
    ActivityCompat.startForegroundService(this, intent<T>(block))
}

//endregion
//region Navigation

inline fun <reified T: Activity> Activity.goTo(noinline block: (Intent.() -> Unit)? = null) {
    startActivity<T>(block)
    finish()
}

fun FragmentActivity.addBackPressedListener(callback: OnBackPressedCallback.() -> Unit) =
    onBackPressedDispatcher.addCallback(this, true, callback)

fun FragmentActivity.finishActivityOnDoubleBackPressed(
    message: String,
    handler: Handler,
    delayMilis: Long
): OnBackPressedCallback = onBackPressedDispatcher.addCallback(this) {
    toastShort(message)
    isEnabled = false
    handler.postDelayed({ isEnabled = true }, delayMilis)
}

@MainThread
fun Activity.bindNavController(@IdRes id: Int) =
    lazy(LazyThreadSafetyMode.NONE) { findNavController(id) }

//endregion
//region ViewModel

@MainThread
inline fun <reified VM : ViewModel> FragmentActivity.bindViewModel() =
    lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this)
            .get(VM::class.java)
    }

inline fun <reified T: ViewModel> FragmentActivity.getViewModel() =
    ViewModelProvider(this).getViewModel<T>()

inline fun <reified T: ViewModel> FragmentActivity.getViewModel(factory: ViewModelProvider.Factory) =
    ViewModelProvider(this, factory).getViewModel<T>()

//endregion
//region Theme

inline val Activity.isDarkThemeEnabled
    get() = resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

fun Activity.toggleTheme() =
    if (isDarkThemeEnabled) lightTheme()
    else darkTheme()

//endregion
//region View

fun AppCompatActivity.setToolbar(@IdRes id: Int) {
    setSupportActionBar(findViewById(id))
}

fun AppCompatActivity.setMaterialToolbar(@IdRes id: Int) {
    setSupportActionBar(findViewById<MaterialToolbar>(id))
}

//endregion
