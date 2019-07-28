@file:Suppress("TooManyFunctions", "unused")

package io.github.achmadhafid.zpack.ktx

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.setPadding
import com.google.android.material.snackbar.Snackbar
import io.github.achmadhafid.zpack.R

//region Visibility

inline val View.isVisible
    get() = visibility == View.VISIBLE

inline val View.isInvisible
    get() = visibility == View.INVISIBLE

inline val View.isGone
    get() = visibility == View.GONE

fun View.show() : View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

inline fun View.showIf(condition: () -> Boolean) : View {
    if (visibility != View.VISIBLE && condition()) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.hide() : View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}

inline fun View.hideIf(condition: () -> Boolean) : View {
    if (visibility != View.INVISIBLE && condition()) {
        visibility = View.INVISIBLE
    }
    return this
}

fun View.gone() : View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

inline fun View.goneIf(condition: () -> Boolean) : View {
    if (visibility != View.GONE && condition()) {
        visibility = View.GONE
    }
    return this
}

fun View.visibleOrInvisible(visibleIf: () -> Boolean) {
    if (visibleIf()) show() else hide()
}

fun View.visibleOrGone(visibleIf: () -> Boolean) {
    if (visibleIf()) show() else gone()
}

inline fun <reified T> List<Pair<View, T>>.visibleOrInvisible(visibleIf: (T) -> Boolean) {
    for ((view, any) in this) {
        if (visibleIf(any)) view.show() else view.hide()
    }
}

inline fun <reified T> List<Pair<View, T>>.visibleOrGone(visibleIf: (T) -> Boolean) {
    for ((view, any) in this) {
        if (visibleIf(any)) view.show() else view.gone()
    }
}

//endregion
//region Listener

fun View.onSingleClick(autoReEnable: Boolean = false, callback: () -> Unit) = setOnClickListener {
    isClickable = false
    callback()
    if (autoReEnable) isClickable = true
}

//endregion
//region Snack Bar

private fun View.snackBar(
    length: Int,
    message: CharSequence,
    anchorView: View? = null,
    actionText: CharSequence? = null,
    @ColorRes @AttrRes actionTextColorRes: Int = R.attr.colorPrimary,
    onClick: (() -> Unit)? = null
) {
    val snackbar = Snackbar.make(this, message, length)
        .apply { anchorView?.let { setAnchorView(it) } }

    onClick?.let {
        snackbar.setAction(actionText) { it() }
            .setActionTextColor(context.resolveColor(actionTextColorRes))
    }

    snackbar.show()
}

fun View.snackBarShort(
    message: CharSequence,
    anchorView: View? = null,
    actionText: CharSequence? = null,
    @ColorRes @AttrRes actionTextColorRes: Int = R.attr.colorPrimary,
    onClick: (() -> Unit)? = null
) = snackBar(Snackbar.LENGTH_SHORT, message, anchorView, actionText, actionTextColorRes, onClick)

fun View.snackBarShort(
    @StringRes messageRes: Int,
    anchorView: View? = null,
    @StringRes actionTextRes: Int? = null,
    @ColorRes @AttrRes actionTextColorRes: Int = R.attr.colorPrimary,
    onClick: (() -> Unit)? = null
) = snackBarShort(
    context.getString(messageRes), anchorView,
    actionTextRes?.let { context.getString(it) },
    actionTextColorRes, onClick
)

fun View.snackBarLong(
    message: CharSequence,
    anchorView: View? = null,
    actionText: CharSequence? = null,
    @ColorRes @AttrRes actionTextColorRes: Int = R.attr.colorPrimary,
    onClick: (() -> Unit)? = null
) = snackBar(Snackbar.LENGTH_LONG, message, anchorView, actionText, actionTextColorRes, onClick)

fun View.snackBarLong(
    @StringRes messageRes: Int,
    anchorView: View? = null,
    @StringRes actionTextRes: Int? = null,
    @ColorRes @AttrRes actionTextColorRes: Int = R.attr.colorPrimary,
    onClick: (() -> Unit)? = null
) = snackBarLong(
    context.getString(messageRes), anchorView,
    actionTextRes?.let { context.getString(it) },
    actionTextColorRes, onClick
)

fun View.snackBarForever(
    message: CharSequence,
    anchorView: View? = null,
    actionText: CharSequence? = null,
    @ColorRes @AttrRes actionTextColorRes: Int = R.attr.colorPrimary,
    onClick: (() -> Unit)? = null
) = snackBar(Snackbar.LENGTH_INDEFINITE, message, anchorView, actionText, actionTextColorRes, onClick)

fun View.snackBarForever(
    @StringRes messageRes: Int,
    anchorView: View? = null,
    @StringRes actionTextRes: Int? = null,
    @ColorRes @AttrRes actionTextColorRes: Int = R.attr.colorPrimary,
    onClick: (() -> Unit)? = null
) = snackBarForever(
    context.getString(messageRes), anchorView,
    actionTextRes?.let { context.getString(it) },
    actionTextColorRes, onClick
)

//endregion
//region Constraint Layout

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

//endregion
//region Padding

fun View.setPaddingRes(@DimenRes paddingRes: Int) {
    setPadding(resources.getDimensionPixelSize(paddingRes))
}

fun View.addNavigationBarPadding() {
    setPadding(
        paddingLeft,
        paddingTop,
        paddingRight,
        paddingBottom + resources.navBarHeightX
    )
}

//endregion
//region Background

fun View.setBackgroundColorRes(@ColorRes @AttrRes colorRes: Int) {
    setBackgroundColor(context.resolveColor(colorRes))
}

//endregion
//region Shape

fun View.makeRoundedCornerOnTop(@DimenRes radiusRes: Int) {
    val radius = resources.getDimensionPixelSize(radiusRes)
    outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            outline.setRoundRect(
                0, 0,
                view.width, view.height + radius,
                radius.toFloat()
            )
        }
    }
    clipToOutline = true
}

//endregion
