package io.github.achmadhafid.zpack.extension

import java.util.Locale

//region Case

inline val String.toCamelCase: String
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

inline val String.toTitleCase: String
    get() = split(" ").joinToString(" ") {
        if (it.isEmpty()) " " else it.substring(0, 1).toUpperCase(Locale.getDefault()) +
                it.substring(1).toLowerCase(Locale.getDefault())
    }.replace("  ", " ")

//endregion
//region Content

inline val String?.blankIfNull get() = this ?: ""
inline val String?.nullIfBlank get() = if (this == "") null else this

fun String?.orEmpty(placeholder: String) = if (this?.isNotEmpty() == true) this else placeholder

//endregion
