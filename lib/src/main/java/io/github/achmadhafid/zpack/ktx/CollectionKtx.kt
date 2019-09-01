package io.github.achmadhafid.zpack.ktx

fun <T> MutableList<T>.addIfNotExist(item: T) {
    if (!contains(item)) add(item)
}
