package io.github.achmadhafid.zpack.extension

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

fun <T : View> Fragment.inflateOrNull(@LayoutRes layoutRes: Int, root: ViewGroup? = null): T? =
    runCatching {
        @Suppress("UNCHECKED_CAST")
        layoutInflater.inflate(layoutRes, root) as T
    }.getOrNull()

fun Fragment.showChildFragment(@IdRes id: Int, transaction: FragmentTransaction.() -> FragmentTransaction = { this }) {
    with(childFragmentManager) {
        findFragmentById(id)?.let { fragment ->
            beginTransaction()
                .transaction()
                .show(fragment)
                .commit()
        }
    }
}

fun Fragment.hideChildFragment(@IdRes id: Int, transaction: FragmentTransaction.() -> FragmentTransaction = { this }) {
    with(childFragmentManager) {
        findFragmentById(id)?.let { fragment ->
            beginTransaction()
                .transaction()
                .hide(fragment)
                .commit()
        }
    }
}

fun Fragment.removeChildFragment(@IdRes id: Int) {
    with(childFragmentManager) {
        findFragmentById(id)?.let { fragment ->
            beginTransaction()
                .remove(fragment)
                .commit()
        }
    }
}
