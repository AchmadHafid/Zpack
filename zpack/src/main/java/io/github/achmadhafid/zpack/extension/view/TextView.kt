package io.github.achmadhafid.zpack.extension.view

import android.graphics.Paint
import android.text.Selection
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.TextViewCompat

infix fun TextView.withFontRes(@FontRes fontRes: Int) {
    typeface = ResourcesCompat.getFont(context, fontRes)
}

infix fun TextView.withTextAppearanceRes(@StyleRes styleRes: Int) {
    TextViewCompat.setTextAppearance(this, styleRes)
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

fun TextView.makeLinks(vararg links: Pair<String, () -> Unit>) {
    val spannableString = SpannableString(text)
    var startIndexOfLink = -1

    for (link in links) {
        val clickableSpan = object : ClickableSpan() {
            override fun updateDrawState(textPaint: TextPaint) {
                // use this to change the link color
                textPaint.color = textPaint.linkColor
                // toggle below value to enable/disable
                // the underline shown below the clickable text
                textPaint.isUnderlineText = true
            }

            override fun onClick(view: View) {
                Selection.setSelection((view as TextView).text as Spannable, 0)
                view.invalidate()
                link.second()
            }
        }

        startIndexOfLink = text.toString().indexOf(link.first, startIndexOfLink + 1)
//      if(startIndexOfLink == -1) continue // if you want to verify your texts contains links text
        spannableString.setSpan(
            clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    movementMethod = LinkMovementMethod.getInstance() // without LinkMovementMethod, link can not click
    setText(spannableString, TextView.BufferType.SPANNABLE)
}
