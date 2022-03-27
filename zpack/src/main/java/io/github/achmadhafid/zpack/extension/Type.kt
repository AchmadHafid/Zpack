package io.github.achmadhafid.zpack.extension

typealias PairOf<T> = Pair<T, T>

fun <T> T.pair() = this to this
