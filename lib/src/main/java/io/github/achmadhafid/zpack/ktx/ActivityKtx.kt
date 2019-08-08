@file:Suppress("unused")

package io.github.achmadhafid.zpack.ktx

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Handler
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.google.android.material.appbar.MaterialToolbar

//region Permissions

@SuppressLint("NewApi")
fun AppCompatActivity.shouldShowRequestPermissionRationales(permissions: Array<out String>) =
    atLeastMarshmallow() && permissions.any { shouldShowRequestPermissionRationale(it) }

//endregion
//region Resource Binding

@MainThread
inline fun <reified V : View> AppCompatActivity.bindView(@IdRes id: Int) =
    lazy(LazyThreadSafetyMode.NONE) { findViewById<V>(id) }

//endregion
//region Navigation

inline fun <reified T: AppCompatActivity> AppCompatActivity.goTo(noinline block: (Intent.() -> Unit)? = null) {
    startActivity<T>(block)
    finish()
}

fun AppCompatActivity.finishActivityOnDoubleBackPressed(
    message: String,
    handler: Handler,
    delayMilis: Long
): OnBackPressedCallback = onBackPressedDispatcher.addCallback(this) {
    toastShort(message)
    isEnabled = false
    handler.postDelayed({ isEnabled = true }, delayMilis)
}

@MainThread
fun AppCompatActivity.bindNavController(@IdRes id: Int) =
    lazy(LazyThreadSafetyMode.NONE) { findNavController(id) }

//endregion
//region ViewModel

@MainThread
inline fun <reified VM : ViewModel> AppCompatActivity.bindViewModel() =
    lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this)
            .get(VM::class.java)
    }

//endregion
//region Theme

inline val AppCompatActivity.isDarkThemeEnabled
    get() = resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

fun AppCompatActivity.toggleTheme() =
    if (isDarkThemeEnabled) lightTheme()
    else darkTheme()

//endregion
//region View

fun AppCompatActivity.setToolbar(@IdRes id: Int) {
    setSupportActionBar(findViewById<Toolbar>(id))
}

fun AppCompatActivity.setMaterialToolbar(@IdRes id: Int) {
    setSupportActionBar(findViewById<MaterialToolbar>(id))
}

//endregion
