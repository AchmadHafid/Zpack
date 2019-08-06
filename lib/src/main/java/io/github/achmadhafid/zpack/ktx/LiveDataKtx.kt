@file:Suppress("unused")

package io.github.achmadhafid.zpack.ktx

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.notifyObserver() {
    value = value
}
