@file:Suppress("unused")

package io.github.achmadhafid.zpack.ktx

import android.text.TextUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//region Date

fun String.toDate(format: String = "yyyy/MM/dd hh:mm"): Date? = try {
    SimpleDateFormat(format, Locale.getDefault()).parse(this)
} catch (ignored: ParseException) {
    null
}

//endregion
//region Case

val String.toCamelCase: String
    get() {
        if (isEmpty()) return ""

        var camelCaseString = ""
        split(" ").forEach {
            camelCaseString = when {
                it.isEmpty() -> "$camelCaseString "
                camelCaseString.trim().isEmpty() -> camelCaseString + it.toLowerCase(Locale.getDefault()) + " "
                else -> camelCaseString + it.toTitleCase
            }
        }
        return camelCaseString
    }

val String.toTitleCase: String
    get() {
        if (isEmpty()) return ""

        var titleCaseString = ""
        split(" ").forEach {
            titleCaseString = if (it.isEmpty()) {
                "$titleCaseString "
            } else {
                titleCaseString + (
                        it.substring(0, 1).toUpperCase(Locale.getDefault()) +
                                it.substring(1).toLowerCase(Locale.getDefault()) +
                                " "
                        )
            }
        }
        return titleCaseString
    }

//endregion
//region Content

fun String?.blankIfNull() =
    this ?: ""

fun String?.nullIfBlank() =
    if (this == "") null else this

//endregion
//region Null & Empty Check

fun ifAllWereNullOrEmpty(vararg list: String?, function: () -> Unit) {
    if (list.all { TextUtils.isEmpty(it) }) function()
}

fun ifAllWereNotNullOrEmpty(vararg list: String?, function: List<String>.() -> Unit) {
    if (list.all { !TextUtils.isEmpty(it) }) function(list.map { it!! })
}

fun ifAnyWasNullOrEmpty(vararg list: String?, function: List<String?>.() -> Unit) {
    if (list.any { TextUtils.isEmpty(it) }) function(list.toList())
}

fun ifAnyWasNotNullOrEmpty(vararg list: String?, function: List<String?>.() -> Unit) {
    if (list.any { !TextUtils.isEmpty(it) }) function(list.toList())
}

//endregion
