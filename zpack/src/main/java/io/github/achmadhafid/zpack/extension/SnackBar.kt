package io.github.achmadhafid.zpack.extension

import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import io.github.achmadhafid.zpack.R

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
    resources.getText(messageRes), anchorView,
    actionTextRes?.let { resources.getText(it) },
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
    resources.getText(messageRes), anchorView,
    actionTextRes?.let { resources.getText(it) },
    actionTextColorRes, onClick
)

fun View.snackBarForever(
    message: CharSequence,
    anchorView: View? = null,
    actionText: CharSequence? = null,
    @ColorRes @AttrRes actionTextColorRes: Int = R.attr.colorPrimary,
    onClick: (() -> Unit)? = null
) = snackBar(
    Snackbar.LENGTH_INDEFINITE,
    message,
    anchorView,
    actionText,
    actionTextColorRes,
    onClick
)

fun View.snackBarForever(
    @StringRes messageRes: Int,
    anchorView: View? = null,
    @StringRes actionTextRes: Int? = null,
    @ColorRes @AttrRes actionTextColorRes: Int = R.attr.colorPrimary,
    onClick: (() -> Unit)? = null
) = snackBarForever(
    resources.getText(messageRes), anchorView,
    actionTextRes?.let { resources.getText(it) },
    actionTextColorRes, onClick
)

@Suppress("LongParameterList")
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
