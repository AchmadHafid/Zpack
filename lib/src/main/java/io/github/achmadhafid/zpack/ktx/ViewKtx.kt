@file:Suppress("TooManyFunctions", "unused")

package io.github.achmadhafid.zpack.ktx

import android.view.View
import android.widget.CompoundButton
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import io.github.achmadhafid.zpack.R

//region Visibility Helper

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

fun View.visibleOrInvisible(visible: Boolean) {
    if (visible) show() else hide()
}

fun View.visibleOrGone(visible: Boolean) {
    if (visible) show() else gone()
}

inline fun <reified T> visibleOrInvisible(vararg targets: Pair<View, T>, visibleIf: (T) -> Boolean) {
    for ((view, any) in targets) {
        view.visibleOrInvisible(visibleIf(any))
    }
}

inline fun <reified T> visibleOrGone(vararg targets: Pair<View, T>, visibleIf: (T) -> Boolean) {
    for ((view, any) in targets) {
        view.visibleOrGone(visibleIf(any))
    }
}

//endregion
//region Listener Helper

inline fun View.onClick(crossinline callback: () -> Unit) = setOnClickListener {
    isClickable = false
    callback()
}

inline fun View.onLongClick(crossinline callback: () -> Boolean) = setOnLongClickListener {
    isLongClickable = false
    callback()
}

//endregion
//region Snack bar Helper

fun View.snackBarShort(message: CharSequence, anchorView: View? = null) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
        .apply { anchorView?.let { setAnchorView(it) } }
        .show()
}

fun View.snackBarShort(
    message: CharSequence,
    anchorView: View? = null,
    actionText: CharSequence,
    @ColorRes @AttrRes actionTextColorRes: Int = R.attr.colorAccent,
    onClick: () -> Unit
) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
        .apply { anchorView?.let { setAnchorView(it) } }
        .setAction(actionText) { onClick() }
        .setActionTextColor(context.resolveColor(actionTextColorRes))
        .show()
}

fun View.snackBarShort(@StringRes messageRes: Int, anchorView: View? = null) {
    Snackbar.make(this, messageRes, Snackbar.LENGTH_SHORT)
        .apply { anchorView?.let { setAnchorView(it) } }
        .show()
}

fun View.snackBarShort(
    @StringRes messageRes: Int,
    anchorView: View? = null,
    actionText: CharSequence,
    @ColorRes @AttrRes actionTextColorRes: Int = R.attr.colorAccent,
    onClick: () -> Unit
) {
    Snackbar.make(this, messageRes, Snackbar.LENGTH_SHORT)
        .apply { anchorView?.let { setAnchorView(it) } }
        .setAction(actionText) { onClick() }
        .setActionTextColor(context.resolveColor(actionTextColorRes))
        .show()
}

fun View.snackBarLong(message: CharSequence, anchorView: View? = null) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .apply { anchorView?.let { setAnchorView(it) } }
        .show()
}

fun View.snackBarLong(
    message: CharSequence,
    anchorView: View? = null,
    actionText: CharSequence,
    @ColorRes @AttrRes actionTextColorRes: Int = R.attr.colorAccent,
    onClick: () -> Unit
) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .apply { anchorView?.let { setAnchorView(it) } }
        .setAction(actionText) { onClick() }
        .setActionTextColor(context.resolveColor(actionTextColorRes))
        .show()
}

fun View.snackBarLong(@StringRes messageRes: Int, anchorView: View? = null) {
    Snackbar.make(this, messageRes, Snackbar.LENGTH_LONG)
        .apply { anchorView?.let { setAnchorView(it) } }
        .show()
}

fun View.snackBarLong(
    @StringRes messageRes: Int,
    anchorView: View? = null,
    actionText: CharSequence,
    @ColorRes @AttrRes actionTextColorRes: Int = R.attr.colorAccent,
    onClick: () -> Unit
) {
    Snackbar.make(this, messageRes, Snackbar.LENGTH_LONG)
        .apply { anchorView?.let { setAnchorView(it) } }
        .setAction(actionText) { onClick() }
        .setActionTextColor(context.resolveColor(actionTextColorRes))
        .show()
}

fun View.snackBarForever(message: CharSequence, anchorView: View? = null) {
    Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
        .apply { anchorView?.let { setAnchorView(it) } }
        .show()
}

fun View.snackBarForever(
    message: CharSequence,
    anchorView: View? = null,
    actionText: CharSequence,
    @ColorRes @AttrRes actionTextColorRes: Int = R.attr.colorAccent,
    onClick: () -> Unit
) {
    Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
        .apply { anchorView?.let { setAnchorView(it) } }
        .setAction(actionText) { onClick() }
        .setActionTextColor(context.resolveColor(actionTextColorRes))
        .show()
}

fun View.snackBarForever(@StringRes messageRes: Int, anchorView: View? = null) {
    Snackbar.make(this, messageRes, Snackbar.LENGTH_INDEFINITE)
        .apply { anchorView?.let { setAnchorView(it) } }
        .show()
}

fun View.snackBarForever(
    @StringRes messageRes: Int,
    anchorView: View? = null,
    actionText: CharSequence,
    @ColorRes @AttrRes actionTextColorRes: Int = R.attr.colorAccent,
    onClick: () -> Unit
) {
    Snackbar.make(this, messageRes, Snackbar.LENGTH_INDEFINITE)
        .apply { anchorView?.let { setAnchorView(it) } }
        .setAction(actionText) { onClick() }
        .setActionTextColor(context.resolveColor(actionTextColorRes))
        .show()
}

//endregion
//region Toggle Group Helper

fun atLeastOneIsChecked(vararg buttons: MaterialButton) {
    buttons.forEach { button ->
        button.addOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                button.isCheckable = false
                buttons.forEach {
                    if (it !== button) it.isCheckable = true
                }
            }
        }
    }
}

fun atLeastOneIsCheckedV2(vararg buttons: CompoundButton) {
    for((index, button) in buttons.withIndex()) {
        button.setOnCheckedChangeListener { _, _ ->
            buttons.forEach {
                if (it.isChecked) return@setOnCheckedChangeListener
            }
            if (index != buttons.size - 1) buttons[index + 1].isChecked = true
            else buttons[0].isChecked = true
        }
    }
}

fun CompoundButton.atLeastOneIsChecked(vararg buttons: MaterialButton) {
    val listener = MaterialButton.OnCheckedChangeListener { button, isChecked ->
        if (isChecked) {
            button.isCheckable = false
            buttons.forEach {
                if (it !== button) it.isCheckable = true
            }
        }
    }
    setOnCheckedChangeListener { _, isChecked ->
        if (isChecked) {
            buttons.forEach {
                it.isEnabled   = true
                it.isCheckable = true
                it.addOnCheckedChangeListener(listener)
            }
            buttons[0].isChecked = true
        } else {
            buttons.forEach {
                it.removeOnCheckedChangeListener(listener)
                it.isCheckable = true
                it.isChecked   = false
                it.isEnabled   = false
            }
        }
    }
}

//endregion
