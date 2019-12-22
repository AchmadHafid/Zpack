@file:Suppress("unused")

package io.github.achmadhafid.zpack.ktx

fun <T> MutableList<T>.addIfNotExist(item: T) {
    if (!contains(item)) add(item)
}

fun <T> MutableList<T>.addAndDoIfNotExist(item: T, doSomething: () -> Unit) {
    if (!contains(item)) {
        add(item)
        doSomething()
    }
}

fun <K, V> Map<K, List<V>>.asMutable(): MutableMap<K, MutableList<V>> =
    entries.associateBy({ it.key }, { it.value.toMutableList() }).toMutableMap()
