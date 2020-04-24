@file:Suppress("unused")

package io.github.achmadhafid.zpack.ktx

import android.graphics.Paint
import android.widget.TextView
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.TextViewCompat

fun TextView.setFontRes(@FontRes fontRes: Int) {
    typeface = ResourcesCompat.getFont(context, fontRes)
}

fun TextView.setTextAppearanceRes(@StyleRes styleRes: Int) {
    TextViewCompat.setTextAppearance(this, styleRes)
}

fun TextView.setTextRes(@StringRes textRes: Int) {
    text = resources.getText(textRes)
}

fun TextView.clear() {
    text = ""
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
