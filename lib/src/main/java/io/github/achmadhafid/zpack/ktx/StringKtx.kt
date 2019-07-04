@file:Suppress("TooManyFunctions", "unused")

package io.github.achmadhafid.zpack.ktx

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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
        if (length == 0)
            return this
        val parts = split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var camelCaseString = ""
        for (part in parts) camelCaseString = camelCaseString + part.toTitleCase + " "
        return camelCaseString
    }

val String.toTitleCase: String
    @SuppressLint("DefaultLocale")
    get() = substring(0, 1).toUpperCase() + substring(1).toLowerCase()

//endregion
