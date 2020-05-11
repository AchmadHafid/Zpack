package io.github.achmadhafid.zpack.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

inline val Fragment.appCompatActivity get() = activity as? AppCompatActivity

inline val Fragment.isStartDestination
    get() = with(findNavController()) {
        graph.startDestination == currentDestination?.id
    }

fun Fragment.finish() {
    runCatching {
        findNavController().popBackStack()
    }
}
