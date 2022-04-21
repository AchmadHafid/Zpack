package io.github.achmadhafid.zpack.extension

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit

fun <T : View> Fragment.inflateOrNull(@LayoutRes layoutRes: Int, root: ViewGroup? = null): T? =
    runCatching {
        @Suppress("UNCHECKED_CAST")
        layoutInflater.inflate(layoutRes, root) as T
    }.getOrNull()

fun Fragment.showChildFragment(
    @IdRes id: Int,
    transaction: FragmentTransaction.() -> FragmentTransaction = { this }
) {
    with(childFragmentManager) {
        findFragmentById(id)?.let { fragment ->
            commit {
                transaction()
                show(fragment)
            }
        }
    }
}

fun <T : Fragment> Fragment.addOrShowChildFragment(
    @IdRes id: Int,
    transaction: FragmentTransaction.() -> FragmentTransaction = { this },
    fragmentBuilder: () -> T
) {
    with(childFragmentManager) {
        commit {
            transaction()
            findFragmentById(id)
                ?.let { show(it) }
                ?: replace(id, fragmentBuilder())
        }
    }
}

fun Fragment.hideChildFragment(
    @IdRes id: Int,
    transaction: FragmentTransaction.() -> FragmentTransaction = { this }
) {
    with(childFragmentManager) {
        findFragmentById(id)?.let { fragment ->
            if (fragment.isHidden.not()) {
                commit {
                    transaction()
                    hide(fragment)
                }
            }
        }
    }
}

fun Fragment.removeChildFragment(
    @IdRes id: Int,
    transaction: FragmentTransaction.() -> FragmentTransaction = { this }
) {
    with(childFragmentManager) {
        findFragmentById(id)?.let { fragment ->
            commit {
                transaction()
                remove(fragment)
            }
        }
    }
}
