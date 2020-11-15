package io.github.achmadhafid.zpack.extension

val <T> Collection<T?>.areAllNull get() = all { it == null }
val <T> Collection<T?>.areAllNotNull get() = none { it == null }

fun areAllNull(vararg item: Any?) = item.all { it == null }
fun areAllNotNull(vararg item: Any?) = item.none { it == null }

fun <T> MutableCollection<T>.addIfNew(item: T, thenDoSomething: () -> Unit): Boolean =
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

infix fun <T> Collection<T>.multiplyBy(n: Int): Collection<T> {
    val newList = mutableListOf<T>()
    repeat(n) {
        newList.addAll(this)
    }
    return newList
}
