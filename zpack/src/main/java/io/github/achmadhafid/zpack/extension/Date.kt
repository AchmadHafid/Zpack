package io.github.achmadhafid.zpack.extension

import android.text.format.DateUtils
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

inline val Date.isToday
    get() = DateUtils.isToday(time)

inline val Date.isYesterday
    get() = DateUtils.isToday(time + DateUtils.DAY_IN_MILLIS)

inline val Date.isTomorrow
    get() = DateUtils.isToday(time - DateUtils.DAY_IN_MILLIS)

inline val Date.formatCompact: String
    get() = DateFormat
        .getDateInstance(DateFormat.SHORT)
        .format(this)
        .replace("/", "")

inline val Date.formatShort: String
    get() = DateFormat
        .getDateInstance(DateFormat.SHORT)
        .format(this)

inline val Date.formatMedium: String
    get() = DateFormat
        .getDateInstance(DateFormat.MEDIUM)
        .format(this)

inline val Date.formatLong: String
    get() = DateFormat
        .getDateInstance(DateFormat.LONG)
        .format(this)

fun String.toDate(format: String, locale: Locale = Locale.getDefault()): Date? = runCatching {
    SimpleDateFormat(format, locale).parse(this)
}.getOrNull()
