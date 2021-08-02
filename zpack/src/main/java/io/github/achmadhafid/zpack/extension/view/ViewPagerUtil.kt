package io.github.achmadhafid.zpack.extension.view

import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

infix fun ViewPager2.setCurrentItemTo(itemIndex: Int) {
    if (currentItem != itemIndex) {
        val isSmoothScroll = abs(currentItem - itemIndex) == 1
        setCurrentItem(itemIndex, isSmoothScroll)
    }
}
