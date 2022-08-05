package io.github.achmadhafid.zpack.extension.view

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.Px
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

inline fun View.clearConstraint(function: ConstraintSet.() -> Unit) {
    with(ConstraintSet()) {
        clone(this@clearConstraint as ConstraintLayout)
        function()
        applyTo(this@clearConstraint)
    }
}

@Deprecated("use extension functions below")
inline var View.constraintMarginStart: Int?
    get() = with(layoutParams as ConstraintLayout.LayoutParams) {
        marginStart
    }
    set(value) {
        with(layoutParams as ConstraintLayout.LayoutParams) {
            marginStart = value ?: 0
            layoutParams = this
        }
    }

@Deprecated("use extension functions below")
inline var View.constraintMarginEnd: Int?
    get() = with(layoutParams as ConstraintLayout.LayoutParams) {
        marginEnd
    }
    set(value) {
        with(layoutParams as ConstraintLayout.LayoutParams) {
            marginEnd = value ?: 0
            layoutParams = this
        }
    }

fun ConstraintLayout.setTopMargin(@IdRes targetView: Int, @Px marginInPx: Int) {
    setMargin(targetView, ConstraintSet.TOP, marginInPx)
}

fun ConstraintLayout.setBottomMargin(@IdRes targetView: Int, @Px marginInPx: Int) {
    setMargin(targetView, ConstraintSet.BOTTOM, marginInPx)
}

fun ConstraintLayout.setStartMargin(@IdRes targetView: Int, @Px marginInPx: Int) {
    setMargin(targetView, ConstraintSet.START, marginInPx)
}

fun ConstraintLayout.setEndMargin(@IdRes targetView: Int, @Px marginInPx: Int) {
    setMargin(targetView, ConstraintSet.END, marginInPx)
}

infix fun ConstraintLayout.matchHorizontally(@IdRes viewId: Int) {
    ConstraintSet().let {
        it.clone(this)
        it.connect(id, ConstraintSet.START, viewId, ConstraintSet.START)
        it.connect(id, ConstraintSet.END, viewId, ConstraintSet.END)
        it.applyTo(this)
    }
}

private fun ConstraintLayout.setMargin(@IdRes targetView: Int, anchor: Int, @Px marginInPx: Int) {
    ConstraintSet().run {
        clone(this@setMargin)
        setMargin(targetView, anchor, marginInPx)
        applyTo(this@setMargin)
    }
}
