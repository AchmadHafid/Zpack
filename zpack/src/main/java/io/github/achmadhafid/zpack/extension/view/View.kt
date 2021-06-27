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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//region Binding

inline infix fun <reified V : View> View.f(@IdRes id: Int): V = findViewById(id)

//endregion
//region Inflater

inline fun <reified T> ViewGroup.inflate(@IdRes id: Int, attachToRoot: Boolean = false): T =
    LayoutInflater.from(context).inflate(id, this, attachToRoot) as T

//endregion
//region Visibility

inline val View.isVisible get() = visibility == View.VISIBLE
inline val View.isInvisible get() = visibility == View.INVISIBLE
inline val View.isGone get() = visibility == View.GONE

fun View.visible() = apply {
    if (!isVisible) visibility = View.VISIBLE
}

fun View.invisible() = apply {
    if (!isInvisible) visibility = View.INVISIBLE
}

fun View.gone() = apply {
    if (!isGone) visibility = View.GONE
}

fun Collection<View>.visible() = apply {
    forEach { it.visible() }
}

fun Collection<View>.invisible() = apply {
    forEach { it.invisible() }
}

fun Collection<View>.gone() = apply {
    forEach { it.gone() }
}

inline fun View.visibleIf(condition: () -> Boolean) = apply {
    if (condition()) visible()
}

inline fun View.invisibleIf(condition: () -> Boolean) = apply {
    if (condition()) invisible()
}

inline fun View.goneIf(condition: () -> Boolean) = apply {
    if (condition()) gone()
}

inline fun Collection<View>.visibleIf(condition: () -> Boolean) = apply {
    if (condition()) visible()
}

inline fun Collection<View>.invisibleIf(condition: () -> Boolean) = apply {
    if (condition()) invisible()
}

inline fun Collection<View>.goneIf(condition: () -> Boolean) = apply {
    if (condition()) gone()
}

fun View.visibleOrInvisible(visibleIf: () -> Boolean) = apply {
    if (visibleIf()) visible() else invisible()
}

fun View.visibleOrGone(visibleIf: () -> Boolean) = apply {
    if (visibleIf()) visible() else gone()
}

fun Collection<View>.visibleOrInvisible(visibleIf: () -> Boolean) = apply {
    if (visibleIf()) visible() else invisible()
}

fun Collection<View>.visibleOrGone(visibleIf: () -> Boolean) = apply {
    if (visibleIf()) visible() else gone()
}

//endregion
//region Availability

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun Collection<View>.enable() {
    forEach { it.isEnabled = true }
}

fun Collection<View>.disable() {
    forEach { it.isEnabled = false }
}

inline val Collection<View>.areAnyEnabled: Boolean
    get() = any { it.isEnabled }

inline val Collection<View>.areAnyDisabled: Boolean
    get() = any { !it.isEnabled }

inline val Collection<View>.areAllEnabled: Boolean
    get() = all { it.isEnabled }

inline val Collection<View>.areAllDisabled: Boolean
    get() = all { !it.isEnabled }

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

fun View.onSingleClick(callback: () -> Unit) = setOnClickListener {
    isClickable = false
    callback()
}

fun View.onSingleClick(
    coroutineScope: CoroutineScope,
    debounce: Long,
    callback: suspend () -> Unit
) = setOnClickListener {
    coroutineScope.launch {
        callback()
        delay(debounce)
        isClickable = true
    }
}

//endregion

@Suppress("DEPRECATION")
fun View.makeFullscreen() {
    systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
}
