package io.github.achmadhafid.zpack.extension

import android.text.Spannable
import android.text.SpannableString

fun String.spanSubstring(substring: String, span: Any) =
    SpannableString(this).spanSubstring(substring, span)

fun SpannableString.spanSubstring(substring: String, span: Any) =
    apply {
        val index = indexOf(substring)
        if (index >= 0) {
            setSpan(span, index, index + substring.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
    }
