@file:Suppress("TooManyFunctions", "unused")

package io.github.achmadhafid.zpack.ktx

import android.content.Intent
import android.content.res.Configuration
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.appbar.MaterialToolbar

//region Navigation Helper

@MainThread
inline fun <reified T: AppCompatActivity> AppCompatActivity.open() =
    startActivity<T>()

@MainThread
inline fun <reified T: AppCompatActivity> AppCompatActivity.open(block: Intent.() -> Unit) =
    startActivity<T>(block)

@MainThread
inline fun <reified T: AppCompatActivity> AppCompatActivity.goTo() {
    open<T>()
    finish()
}

@MainThread
inline fun <reified T: AppCompatActivity> AppCompatActivity.goTo(block: Intent.() -> Unit) {
    open<T>(block)
    finish()
}

//endregion
//region Resource Binding Helper

@MainThread
inline fun <reified V : View> AppCompatActivity.bindView(@IdRes id: Int) =
    lazy(LazyThreadSafetyMode.NONE) { findViewById<V>(id) }

@MainThread
inline fun <reified V : View> AppCompatActivity.bindView(@IdRes id: Int, crossinline init: V.() -> Unit) =
    lazy(LazyThreadSafetyMode.NONE) {
        findViewById<V>(id).also {
            it.init()
        }
    }

//endregion
//region ViewModel Helper

@MainThread
inline fun <reified VM : ViewModel> AppCompatActivity.bindViewModel() =
    lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this)
            .get(VM::class.java)
    }

//endregion
//region Theme Helper

fun AppCompatActivity.isDarkThemeEnable() = resources.configuration.uiMode and
        Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

fun AppCompatActivity.toggleTheme() =
    if (isDarkThemeEnable()) lightTheme()
    else darkTheme()

//endregion
//region View Helper

fun AppCompatActivity.setToolbar(@IdRes id: Int) {
    setSupportActionBar(findViewById<Toolbar>(id))
}

fun AppCompatActivity.setMaterialToolbar(@IdRes id: Int) {
    setSupportActionBar(findViewById<MaterialToolbar>(id))
}

//endregion
