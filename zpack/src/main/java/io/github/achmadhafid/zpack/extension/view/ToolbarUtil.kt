package io.github.achmadhafid.zpack.extension.view

import androidx.annotation.IdRes
import androidx.appcompat.widget.Toolbar

fun Toolbar.getIdIfVisibleOrNull(@IdRes menuId: Int) =
    menu.findItem(menuId)?.let {
        if (it.isVisible) menuId else null
    }
