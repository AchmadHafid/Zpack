package io.github.achmadhafid.zpack.extension

import android.content.Context
import android.util.TypedValue
import androidx.annotation.ArrayRes
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

//region Context
//region CharSequence

@MainThread
fun Context.charSequenceRes(@StringRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getText(resourceId)
    }

@MainThread
fun Context.charSequenceArrayRes(@ArrayRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getTextArray(resourceId)
    }

@MainThread
fun Context.charSequenceListRes(@ArrayRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getTextArray(resourceId)
            .toList()
    }

//endregion
//region String

@MainThread
fun Context.stringRes(@StringRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getString(resourceId)
    }

@MainThread
fun Context.stringRes(@StringRes resourceId: Int, vararg formatArgs: Any) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getString(resourceId, formatArgs)
    }

@MainThread
fun Context.stringArrayRes(@ArrayRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getStringArray(resourceId)
    }

@MainThread
fun Context.stringListRes(@ArrayRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getStringArray(resourceId)
            .toList()
    }

//endregion
//region Int

@MainThread
fun Context.intRes(@IntegerRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getInteger(resourceId)
    }

@MainThread
fun Context.intArrayRes(@ArrayRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getIntArray(resourceId)
    }

@MainThread
fun Context.intListRes(@ArrayRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getIntArray(resourceId)
            .toList()
    }

//endregion
//region Dimension

@MainThread
fun Context.dimenRes(@DimenRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getDimensionPixelSize(resourceId)
    }

//endregion
//region Color

@MainThread
fun Context.colorRes(@ColorRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resolveColor(resourceId)
    }

//endregion
//region region

@MainThread
fun Context.drawableRes(@DrawableRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        ContextCompat.getDrawable(this@drawableRes, resourceId)
    }

//endregion
//endregion
//region Fragment
//region CharSequence

@MainThread
fun Fragment.charSequenceRes(@StringRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getText(resourceId)
    }

@MainThread
fun Fragment.charSequenceArrayRes(@ArrayRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getTextArray(resourceId)
    }

@MainThread
fun Fragment.charSequenceListRes(@ArrayRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getTextArray(resourceId)
            .toList()
    }

//endregion
//region String

@MainThread
fun Fragment.stringRes(@StringRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getString(resourceId)
    }

@MainThread
fun Fragment.stringRes(@StringRes resourceId: Int, vararg formatArgs: Any) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getString(resourceId, formatArgs)
    }

@MainThread
fun Fragment.stringArrayRes(@ArrayRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getStringArray(resourceId)
    }

@MainThread
fun Fragment.stringListRes(@ArrayRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getStringArray(resourceId)
            .toList()
    }

//endregion
//region Int

@MainThread
fun Fragment.intRes(@IntegerRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getInteger(resourceId)
    }

@MainThread
fun Fragment.intArrayRes(@ArrayRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getIntArray(resourceId)
    }

@MainThread
fun Fragment.intListRes(@ArrayRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getIntArray(resourceId)
            .toList()
    }

//endregion
//region Dimension

@MainThread
fun Fragment.dimenRes(@DimenRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getDimensionPixelSize(resourceId)
    }

//endregion
//region Color

@MainThread
fun Fragment.colorRes(@ColorRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        requireContext().resolveColor(resourceId)
    }

//endregion
//region region

@MainThread
fun Fragment.drawableRes(@DrawableRes resourceId: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        ContextCompat.getDrawable(requireContext(), resourceId)
    }

//endregion
//endregion

fun Context.getColorCompat(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

fun Context.resolveColor(@ColorRes @AttrRes id: Int) = with(TypedValue()) {
    when {
        theme.resolveAttribute(id, this, true) -> data
        atLeastMarshmallow() -> getColor(id)
        else -> getColorCompat(id)
    }
}

private fun Context.applyDimension(unit: Int, number: Number) =
    TypedValue.applyDimension(unit, number.toFloat(), resources.displayMetrics)

fun Context.dpToPx(dp: Number) = applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp)
fun Context.pxToDp(px: Number) = applyDimension(TypedValue.COMPLEX_UNIT_PX, px)
fun Context.spToPx(sp: Int) = applyDimension(TypedValue.COMPLEX_UNIT_PX, sp)
fun Context.pxToSp(px: Int) = applyDimension(TypedValue.COMPLEX_UNIT_PX, px)
