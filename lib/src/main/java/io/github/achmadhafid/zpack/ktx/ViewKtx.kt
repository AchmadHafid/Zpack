@file:Suppress("TooManyFunctions", "unused")

package io.github.achmadhafid.zpack.ktx

import android.graphics.Paint
import android.view.View
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
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
//region TextView

var TextView.textRes: Int
    get () = TODO("Only use this extension function to set text from a string resource")
    set(value) {
        text = resources.getString(value)
    }

fun TextView.underLine() {
    paint.flags = paint.flags or Paint.UNDERLINE_TEXT_FLAG
    paint.isAntiAlias = true
}

fun TextView.deleteLine() {
    paint.flags = paint.flags or Paint.STRIKE_THRU_TEXT_FLAG
    paint.isAntiAlias = true
}

fun TextView.bold() {
    paint.isFakeBoldText = true
    paint.isAntiAlias = true
}

//endregion
//region EditText

var EditText.value: String
    get() = text.toString()
    set(value) {
        setText(value)
    }

//endregion
//region AppBarLayout

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

//endregion
//region Util

fun List<Pair<CompoundButton, Boolean>>.atLeastOneIsChecked(
    onCheckedChange: ((Int, Boolean) -> Unit)? = null
) {
    for ((index, button) in withIndex()) {
        button.first.isChecked = button.second
        button.first.setOnCheckedChangeListener { buttonView, isChecked ->
            onCheckedChange?.invoke(buttonView.id, isChecked)
            forEach {
                if (it.first.isChecked) return@setOnCheckedChangeListener
            }
            if (index != size - 1) this[index + 1].first.isChecked = true
            else this[0].first.isChecked = true

        }
    }
}

fun MaterialButtonToggleGroup.exactlyOneMustBeChecked(
    buttons: List<MaterialButton>,
    defaultChecked: MaterialButton,
    onCheckedChange: ((Int, Boolean) -> Unit)? = null
) {
    isSingleSelection = true
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
    defaultChecked.isChecked = true
    addOnButtonCheckedListener { _, checkedId, isChecked ->
        onCheckedChange?.invoke(checkedId, isChecked)
    }
}

@Suppress("LongParameterList")
fun List<MaterialButton>.exactlyOneMustBeCheckedOrNone(
    buttonGroup: MaterialButtonToggleGroup,
    buttonGroupDefaultChecked: MaterialButton,
    buttonGroupListener: (id: Int, isChecked: Boolean) -> Unit,
    switchButton: CompoundButton,
    switchButtonDefaultIsChecked: Boolean,
    switchButtonListener: (isChecked: Boolean) -> Unit
) {
    val listener = MaterialButton.OnCheckedChangeListener { button, isChecked ->
        if (isChecked) {
            button.isCheckable = false
            forEach {
                if (it !== button) it.isCheckable = true
            }
        }
    }
    val groupListener = MaterialButtonToggleGroup.OnButtonCheckedListener { _, id, isChecked ->
        buttonGroupListener(id, isChecked)
    }
    switchButton.setOnCheckedChangeListener { _, isChecked ->
        if (isChecked) {
            forEach {
                it.isEnabled = true
                it.isCheckable = true
                it.addOnCheckedChangeListener(listener)
            }
            buttonGroupDefaultChecked.isChecked = true
            buttonGroup.addOnButtonCheckedListener(groupListener)
        } else {
            buttonGroup.removeOnButtonCheckedListener(groupListener)
            forEach {
                it.removeOnCheckedChangeListener(listener)
                it.isCheckable = true
                it.isChecked = false
                it.isEnabled = false
            }
        }
        switchButtonListener(isChecked)
    }
    switchButton.isChecked = switchButtonDefaultIsChecked
}


//endregion
