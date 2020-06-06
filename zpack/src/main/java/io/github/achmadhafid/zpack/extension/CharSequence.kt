package io.github.achmadhafid.zpack.extension

import java.util.Locale

//region Case

inline val CharSequence.toCamelCase: CharSequence
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

inline val CharSequence.toTitleCase: CharSequence
    get() = split(" ").joinToString(" ") {
        if (it.isEmpty()) " " else it.substring(0, 1).toUpperCase(Locale.getDefault()) +
                it.substring(1).toLowerCase(Locale.getDefault())
    }.replace("  ", " ")

//endregion
//region Content

infix fun CharSequence?.whenNullOrEmpty(placeholder: CharSequence) =
    if (this?.isNotEmpty() == true) this else placeholder

infix fun CharSequence?.whenNullOrBlank(placeholder: CharSequence) =
    if (this?.isNotBlank() == true) this else placeholder

//endregion
