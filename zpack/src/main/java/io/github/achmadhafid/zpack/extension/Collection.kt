package io.github.achmadhafid.zpack.extension

inline val <T> Collection<T?>.areAllNull
    get() = all { it == null }

inline val <T> Collection<T?>.areAllNotNull
    get() = none { it == null }

fun areAllNull(vararg item: Any?) = item.all { it == null }
fun areAllNotNull(vararg item: Any?) = item.none { it == null }

fun <T> MutableCollection<T>.addIfNew(item: T, thenDoSomething: () -> Unit = {}): Boolean =
    if (!contains(item)) {
        add(item)
        thenDoSomething()
        true
    } else false

@JvmName("mutableMapList")
fun <K, V> Map<K, List<V>>.asMutable(): MutableMap<K, MutableList<V>> =
    entries.associateBy({ it.key }, { it.value.toMutableList() }).toMutableMap()

@JvmName("mutableMapSet")
fun <K, V> Map<K, Set<V>>.asMutable(): MutableMap<K, MutableSet<V>> =
    entries.associateBy({ it.key }, { it.value.toMutableSet() }).toMutableMap()

infix fun <T> Collection<T>.duplicateBy(n: Int): Collection<T> {
    val newList = mutableListOf<T>()
    repeat(n) {
        newList.addAll(this)
    }
    return newList
}

inline fun <reified T> List<T>.duplicateBy(n: Int, builder: (T) -> T = { it }): List<T> {
    val newList = mutableListOf<T>()
    newList.addAll(this)
    repeat(n) {
        newList.addAll(map { builder(it) })
    }
    return newList
}

fun <F, S> Pair<F, S>.invert(): Pair<S, F> =
    second to first

fun <K : Any, V : Any> Map<K, List<V>>.flatten(): List<Any> {
    val temp = mutableListOf<Any>()
    forEach { (key, value) ->
        temp.add(key)
        temp.addAll(value)
    }
    return temp
}
