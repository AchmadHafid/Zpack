@file:Suppress("TooManyFunctions", "unused")

package io.github.achmadhafid.zpack.ktx

import androidx.core.widget.NestedScrollView
import com.google.android.material.appbar.AppBarLayout

fun AppBarLayout.setSelectedOnScrollDown(scrollView: NestedScrollView) {
    scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, _, _, _ ->
        isSelected = scrollView.canScrollVertically(-1)
    })
}
