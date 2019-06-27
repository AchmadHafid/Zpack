package io.github.achmadhafid.zpack.ktx

import android.graphics.Paint
import android.widget.TextView

fun TextView.textRes(textRes: Int?) {
    textRes?.let { text = resources.getString(it) }
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
