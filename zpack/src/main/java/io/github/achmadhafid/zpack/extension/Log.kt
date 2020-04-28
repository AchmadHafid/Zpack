package io.github.achmadhafid.zpack.extension

import android.util.Log

inline val <reified T> T.TAG: String get() = T::class.java.simpleName

inline fun <reified T> T.d(message: String) = Log.d(TAG, message)
inline fun <reified T> T.e(message: String) = Log.e(TAG, message)
inline fun <reified T> T.i(message: String) = Log.i(TAG, message)
inline fun <reified T> T.v(message: String) = Log.v(TAG, message)
inline fun <reified T> T.w(message: String) = Log.w(TAG, message)
