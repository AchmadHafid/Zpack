package io.github.achmadhafid.zpack.ktx

import android.content.res.Resources

inline val Resources.navBarHeightX: Int
    get() = with(getIdentifier("navigation_bar_height", "dimen", "android")) {
        if (this > 0) getDimensionPixelSize(this) else 0
    }
