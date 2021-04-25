package io.github.achmadhafid.zpack.extension

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

fun AppCompatActivity.navControllerWithFragmentContainer(@IdRes id: Int): Lazy<NavController> =
    lazy(LazyThreadSafetyMode.NONE) {
        (supportFragmentManager.findFragmentById(id) as NavHostFragment).navController
    }

inline val Fragment.appCompatActivity get() = activity as? AppCompatActivity

inline val Fragment.isStartDestination
    get() = with(findNavController()) {
        graph.startDestination == currentDestination?.id
    }

fun Fragment.navigateIfNotCurrent(@IdRes destinationId: Int, action: () -> NavDirections) {
    with(findNavController()) {
        if (currentDestination?.id != destinationId) {
            navigate(action())
        }
    }
}

fun Fragment.navigateUp() =
    findNavController().navigateUp()

fun Fragment.navigateUpOrFinishActivity() {
    if (navigateUp().not())
        finishActivity()
}

@Deprecated(
    "Use extension function below as a replacement",
    ReplaceWith("popBackStackOrFinishActivity()")
)
fun Fragment.finish(onNoBackStack: () -> Unit = { finishActivity() }) {
    if (findNavController().popBackStack().not()) {
        onNoBackStack()
    }
}

fun Fragment.popBackStack() =
    findNavController().popBackStack()

fun Fragment.popBackStackOrFinishActivity() {
    if (popBackStack().not()) {
        finishActivity()
    }
}

fun Fragment.finishActivity() {
    activity?.finish()
}
