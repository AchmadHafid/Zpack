package io.github.achmadhafid.zpack.extension

import java.util.UUID

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
