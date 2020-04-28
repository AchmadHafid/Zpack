package io.github.achmadhafid.zpack.extension.view

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

inline fun View.clearConstraint(function: ConstraintSet.() -> Unit) {
    with(ConstraintSet()) {
        clone(this@clearConstraint as ConstraintLayout)
        function()
        applyTo(this@clearConstraint)
    }
}

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
