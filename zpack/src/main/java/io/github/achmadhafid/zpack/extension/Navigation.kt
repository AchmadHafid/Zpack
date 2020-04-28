package io.github.achmadhafid.zpack.extension

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

inline val Fragment.isStartDestination
    get() = with(findNavController()) {
        graph.startDestination == currentDestination?.id
    }

fun Fragment.finish() {
    runCatching {
        findNavController().popBackStack()
    }
}
