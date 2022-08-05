package io.github.achmadhafid.zpack.extension

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import java.util.AbstractList

inline infix fun <reified T> SavedStateHandle.withKey(key: String): Lazy<T?> =
    lazy(LazyThreadSafetyMode.NONE) { get<T>(key) }

infix fun SavedStateHandle.asStringWithKey(key: String): Lazy<String> =
    lazy(LazyThreadSafetyMode.NONE) { get<String>(key).orEmpty() }

infix fun SavedStateHandle.asBooleanWithKey(key: String): Lazy<Boolean> =
    lazy(LazyThreadSafetyMode.NONE) { get<Boolean>(key) ?: false }

infix fun SavedStateHandle.asDoubleWithKey(key: String): Lazy<Double> =
    lazy(LazyThreadSafetyMode.NONE) { get<Double>(key) ?: 0.0 }

infix fun SavedStateHandle.asLongWithKey(key: String): Lazy<Long> =
    lazy(LazyThreadSafetyMode.NONE) { get<Long>(key) ?: 0 }

infix fun SavedStateHandle.asIntWithKey(key: String): Lazy<Int> =
    lazy(LazyThreadSafetyMode.NONE) { get<Int>(key) ?: 0 }

infix fun SavedStateHandle.asListOfStringWithKey(key: String): Lazy<List<String>> =
    lazy(LazyThreadSafetyMode.NONE) { get<List<String>>(key) ?: emptyList() }

inline infix fun <reified T : Parcelable> SavedStateHandle.asParcelableArrayWithKey(key: String): Lazy<Array<T>> =
    lazy(LazyThreadSafetyMode.NONE) { get<Array<T>>(key) ?: emptyArray() }

inline infix fun <reified T : Parcelable> SavedStateHandle.asParcelableListWithKey(key: String): Lazy<List<T>> =
    lazy(LazyThreadSafetyMode.NONE) { get<AbstractList<T>>(key)?.toList() ?: emptyList() }
