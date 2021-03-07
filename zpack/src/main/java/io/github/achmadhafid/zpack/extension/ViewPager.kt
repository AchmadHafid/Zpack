package io.github.achmadhafid.zpack.extension

import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.showFirstPage() {
    currentItem = 0
}

fun ViewPager2.showPrevious(onUnavailable: () -> Unit = {}) {
    if (currentItem == 0) onUnavailable()
    else currentItem -= 1
}

fun ViewPager2.showNext(onUnavailable: () -> Unit = {}) {
    adapter?.itemCount?.let {
        if (currentItem < it - 1) currentItem += 1
        else onUnavailable()
    } ?: onUnavailable()
}
