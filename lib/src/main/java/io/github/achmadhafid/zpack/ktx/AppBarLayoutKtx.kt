package io.github.achmadhafid.zpack.ktx

import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout

fun AppBarLayout.setSelectedOnScrollDown(scrollView: NestedScrollView) {
    scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, _, _, _ ->
        isSelected = scrollView.canScrollVertically(-1)
    })
}

fun AppBarLayout.setSelectedOnScrollDown(recyclerView: RecyclerView) {
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            isSelected = recyclerView.canScrollVertically(-1)
        }
    })
}
