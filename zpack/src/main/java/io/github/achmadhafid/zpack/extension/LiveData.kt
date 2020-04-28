package io.github.achmadhafid.zpack.extension

import androidx.lifecycle.MutableLiveData

@Suppress("NestedBlockDepth")
fun <T> MutableLiveData<T?>.setValueIfNew(newValue: T?, comparator: ((T, T) -> Boolean)? = null) {
    value?.let { nonNullValue ->
        newValue?.let {
            comparator?.let {
                if (!comparator(nonNullValue, newValue)) value = newValue
            } ?: run {
                if (nonNullValue != newValue) value = newValue
            }
        } ?: run { value = newValue }
    } ?: run { if (newValue != null) value = newValue }
}

fun <T> MutableLiveData<T?>.notifyObserver() {
    value = value
}
