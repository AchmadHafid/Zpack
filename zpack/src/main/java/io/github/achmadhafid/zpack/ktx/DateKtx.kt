package io.github.achmadhafid.zpack.ktx

import android.text.format.DateUtils
import java.text.DateFormat
import java.util.Date

inline val Date.isToday
    get() = DateUtils.isToday(time)

inline val Date.isYesterday
    get() = DateUtils.isToday(time + DateUtils.DAY_IN_MILLIS)

inline val Date.isTommorrow
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
