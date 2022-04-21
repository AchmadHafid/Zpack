package io.github.achmadhafid.zpack.extension

import java.util.UUID

const val EMPTY_STRING = ""

//region Case

inline val String.toCamelCase: String get() = (this as CharSequence).toCamelCase.toString()
inline val String.toTitleCase: String get() = (this as CharSequence).toTitleCase.toString()

//endregion
//region Content

infix fun String?.whenNullOrEmpty(placeholder: String) =
    if (this?.isNotEmpty() == true) this else placeholder

infix fun String?.whenNullOrBlank(placeholder: String) =
    if (this?.isNotBlank() == true) this else placeholder

//endregion

inline val newId: String
    get() = UUID.randomUUID().toString()

infix fun String.orEmptyWhen(condition: Boolean) =
    if (condition) "" else this

fun String.removeSpaces() =
    replace(" ", "")

fun mergeRange(start: String, end: String): String {
    val startTokens = start.trim().split(" ")
    val endTokens = end.trim().split(" ")

    if (startTokens.size != endTokens.size) {
        return "$start - $end"
    }

    fun List<String>.merge(maxIndex: Int) =
        subList(0, maxIndex + 1).joinToString(" ")

    var result = ""
    for (index in (startTokens.size - 1) downTo 0) {
        if (startTokens[index] == endTokens[index]) {
            result = "${startTokens[index]} $result"
        } else {
            result = "${startTokens.merge(index)} - ${endTokens.merge(index)} $result"
            break
        }
    }

    return result
}

fun String.camelCaseAWord() =
    when {
        contains(" ") || isBlank() -> this
        length == 1 -> first().lowercase()
        else -> first().lowercase() + substring(1)
    }

fun String.firstWordOrEmpty() =
    split(" ").firstOrNull().orEmpty()
