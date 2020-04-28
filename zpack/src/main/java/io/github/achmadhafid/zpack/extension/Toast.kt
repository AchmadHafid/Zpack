package io.github.achmadhafid.zpack.extension

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Context.toastShort(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toastShort(@StringRes messageRes: Int) {
    Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()
}

fun Context.toastShort(@StringRes messageRes: Int, vararg messages: CharSequence) {
    toastShort(String.format(getString(messageRes), messages))
}

fun Context.toastLong(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.toastLong(@StringRes messageRes: Int) {
    Toast.makeText(this, messageRes, Toast.LENGTH_LONG).show()
}

fun Context.toastLong(@StringRes messageRes: Int, vararg messages: CharSequence) {
    toastLong(String.format(getString(messageRes), messages))
}

fun Fragment.toastShort(message: CharSequence) {
    context?.toastShort(message)
}

fun Fragment.toastShort(@StringRes messageRes: Int) {
    context?.toastShort(messageRes)
}

@Suppress("SpreadOperator")
fun Fragment.toastShort(@StringRes messageRes: Int, vararg messages: CharSequence) {
    context?.toastShort(messageRes, *messages)
}

fun Fragment.toastLong(message: CharSequence) {
    context?.toastLong(message)
}

fun Fragment.toastLong(@StringRes messageRes: Int) {
    context?.toastLong(messageRes)
}

@Suppress("SpreadOperator")
fun Fragment.toastLong(@StringRes messageRes: Int, vararg messages: CharSequence) {
    context?.toastLong(messageRes, *messages)
}
