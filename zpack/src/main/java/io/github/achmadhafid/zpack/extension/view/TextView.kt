package io.github.achmadhafid.zpack.extension.view

import android.graphics.Paint
import android.widget.TextView
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.TextViewCompat

@Deprecated(
    "Will be removed in the next release", ReplaceWith(
        "withFontRes",
        "io.github.achmadhafid.zpack.extension.view"
    )
)
fun TextView.setFontRes(@FontRes fontRes: Int) {
    typeface = ResourcesCompat.getFont(context, fontRes)
}

infix fun TextView.withFontRes(@FontRes fontRes: Int) {
    typeface = ResourcesCompat.getFont(context, fontRes)
}

@Deprecated(
    "Will be removed in the next release", ReplaceWith(
        "withTextAppearanceRes",
        "io.github.achmadhafid.zpack.extension.view"
    )
)
fun TextView.setTextAppearanceRes(@StyleRes styleRes: Int) {
    TextViewCompat.setTextAppearance(this, styleRes)
}

infix fun TextView.withTextAppearanceRes(@StyleRes styleRes: Int) {
    TextViewCompat.setTextAppearance(this, styleRes)
}

@Deprecated(
    "Will be removed in the next release", ReplaceWith(
        "withTextRes",
        "io.github.achmadhafid.zpack.extension.view"
    )
)
fun TextView.setTextRes(@StringRes textRes: Int) {
    text = resources.getText(textRes)
}

infix fun TextView.withTextRes(@StringRes textRes: Int) {
    text = resources.getText(textRes)
}

infix fun TextView.withNewText(newText: CharSequence) {
    if (text != newText) text = newText
}

infix fun TextView.withNewText(newText: String) {
    if (text != newText) text = newText
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
