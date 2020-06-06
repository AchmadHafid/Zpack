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

inline infix fun <reified V : View> View.f(@IdRes id: Int): V = findViewById(id)

//endregion
//region Inflater

inline fun <reified T> ViewGroup.inflate(@IdRes id: Int, attachToRoot: Boolean = false): T =
    LayoutInflater.from(context).inflate(id, this, attachToRoot) as T

//endregion
//region Visibility

inline val View.isVisible get()   = visibility == View.VISIBLE
inline val View.isInvisible get() = visibility == View.INVISIBLE
inline val View.isGone get()      = visibility == View.GONE

fun View.visible()   = apply {
    if (!isVisible) visibility = View.VISIBLE
}
fun View.invisible() = apply {
    if (!isInvisible) visibility = View.INVISIBLE
}
fun View.gone()      = apply {
    if (!isGone) visibility = View.GONE
}

fun List<View>.visible()   = apply {
    forEach { it.visible() }
}
fun List<View>.invisible() = apply {
    forEach { it.invisible() }
}
fun List<View>.gone()      = apply {
    forEach { it.gone() }
}

inline fun View.visibleIf(condition: () -> Boolean)   = apply {
    if (condition()) visible()
}
inline fun View.invisibleIf(condition: () -> Boolean) = apply {
    if (condition()) invisible()
}
inline fun View.goneIf(condition: () -> Boolean)      = apply {
    if (condition()) gone()
}

inline fun List<View>.visibleIf(condition: () -> Boolean)   = apply {
    if (condition()) visible()
}
inline fun List<View>.invisibleIf(condition: () -> Boolean) = apply {
    if (condition()) invisible()
}
inline fun List<View>.goneIf(condition: () -> Boolean)      = apply {
    if (condition()) gone()
}

fun View.visibleOrInvisible(visibleIf: () -> Boolean) = apply {
    if (visibleIf()) visible() else invisible()
}
fun View.visibleOrGone(visibleIf: () -> Boolean)      = apply {
    if (visibleIf()) visible() else gone()
}

fun List<View>.visibleOrInvisible(visibleIf: () -> Boolean) = apply {
    if (visibleIf()) visible() else invisible()
}
fun List<View>.visibleOrGone(visibleIf: () -> Boolean)      = apply {
    if (visibleIf()) visible() else gone()
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
