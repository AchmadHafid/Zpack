package io.github.achmadhafid.zpack.extension.view

import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.IdRes
import androidx.core.view.setPadding
import io.github.achmadhafid.zpack.extension.resolveColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//region Binding

inline fun <reified V : View> View.f(@IdRes id: Int): V = findViewById(id)

//endregion
//region Inflater

inline fun <reified T> ViewGroup.inflate(@IdRes id: Int, attachToRoot: Boolean = false): T =
    LayoutInflater.from(context).inflate(id, this, attachToRoot) as T

//endregion
//region Visibility

inline val View.isVisible get() = visibility == View.VISIBLE

fun View.show() = run {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    this
}

fun List<View>.show() = run {
    forEach { it.show() }
    this
}

inline fun View.showIf(condition: () -> Boolean) = run {
    if (condition()) show()
    this
}

inline fun List<View>.showIf(condition: () -> Boolean) = run {
    val isShow = condition()
    forEach { if (isShow) it.show() }
    this
}

inline val View.isInvisible get() = visibility == View.INVISIBLE

fun View.invisible() = run {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    this
}

fun List<View>.invisible() = run {
    forEach { it.invisible() }
    this
}

inline fun View.invisibleIf(condition: () -> Boolean) = run {
    if (condition()) invisible()
    this
}

inline fun List<View>.invisibleIf(condition: () -> Boolean) = run {
    val isHide = condition()
    forEach { if (isHide) it.invisible() }
    this
}

inline val View.isGone get() = visibility == View.GONE

fun View.gone() = run {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    this
}

fun List<View>.gone() = run {
    forEach { it.invisible() }
    this
}

inline fun View.goneIf(condition: () -> Boolean) = run {
    if (condition()) gone()
    this
}

inline fun List<View>.goneIf(condition: () -> Boolean) = run {
    val isGone = condition()
    forEach { if (isGone) it.gone() }
    this
}

fun View.visibleOrInvisible(visibleIf: () -> Boolean) {
    if (visibleIf()) show() else invisible()
}

fun List<View>.visibleOrInvisible(visibleIf: () -> Boolean) {
    val isVisible = visibleIf()
    forEach { if (isVisible) it.show() else it.invisible() }
}

fun View.visibleOrGone(visibleIf: () -> Boolean) {
    if (visibleIf()) show() else gone()
}

fun List<View>.visibleOrGone(visibleIf: () -> Boolean) {
    val isVisible = visibleIf()
    forEach { if (isVisible) it.show() else it.gone() }
}

//endregion
//region Availability

inline val List<View>.areAllEnabled: Boolean
    get() = all { it.isEnabled }

inline val List<View>.areAllDisabled: Boolean
    get() = all { !it.isEnabled }

fun List<View>.enabled(isEnabled: Boolean) {
    forEach { it.isEnabled = isEnabled }
}

//endregion
//region Padding

fun View.setPaddingRes(@DimenRes paddingRes: Int) {
    setPadding(resources.getDimensionPixelSize(paddingRes))
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
//region Listener

fun View.onSingleClick(autoReEnable: Boolean = true, callback: () -> Unit) = setOnClickListener {
    isClickable = false
    callback()
    isClickable = autoReEnable
}

fun View.onSingleClick(
    coroutineScope: CoroutineScope,
    autoReEnable: Boolean = true,
    callback: suspend () -> Unit
) = setOnClickListener {
    coroutineScope.launch {
        isClickable = false
        callback()
        isClickable = autoReEnable
    }
}

//endregion
