package io.github.achmadhafid.zpack.extension.view

import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

fun ViewPager2.setCurrentItemTo(
    itemIndex: Int,
    withSmoothScroll: Boolean = true,
    forceSmoothScrollNextPage: Boolean = false
) {
    if (currentItem != itemIndex) {
        val isSmoothScroll = withSmoothScroll || (abs(currentItem - itemIndex) == 1 && forceSmoothScrollNextPage)
        setCurrentItem(itemIndex, isSmoothScroll)
    }
}
