@file:Suppress("unused")

package io.github.achmadhafid.zpack.ktx

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity

inline val Resources.statusBarHeight: Int
    get() = getIdentifier(
        "status_bar_height",
        "dimen",
        "android"
    ).runCatching {
        getDimensionPixelSize(this)
    }.getOrDefault(0)

inline val Resources.navigationBarHeight: Int
    get() = getIdentifier(
        "navigation_bar_height",
        "dimen",
        "android"
    ).runCatching {
        getDimensionPixelSize(this)
    }.getOrDefault(0)

inline val AppCompatActivity.actionBarHeight: Int
    @Suppress("MagicNumber")
    get() = theme.obtainStyledAttributes(IntArray(100)).let {
        val height = it.getDimension(0, 0.0f).toInt()
        it.recycle()
        height
    }
