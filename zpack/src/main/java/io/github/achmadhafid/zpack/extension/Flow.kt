package io.github.achmadhafid.zpack.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//region Activity & Fragment Collector Extensions

fun <T : Any?> Flow<T>.collect(
    fragment: Fragment,
    action: suspend (value: T) -> Unit
) {
    fragment.viewLifecycleOwner.lifecycleScope.launch {
        collectLatest(action)
    }
}

fun <T : Any?> Flow<T>.collect(
    activity: AppCompatActivity,
    action: suspend (value: T) -> Unit
) {
    activity.lifecycleScope.launch {
        collectLatest(action)
    }
}

/**
 * Should be called in onCreate state
 */
fun <T> Flow<T>.collectOnLifecycle(
    fragment: Fragment,
    shouldBeRepeated: Boolean = true,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (value: T) -> Unit
) {
    fragment.viewLifecycleOwner.lifecycleScope.launch {
        if (shouldBeRepeated) {
            fragment.viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
                collectLatest(action)
            }
        } else collectLatest(action)
    }
}

/**
 * Should be called in onCreate state
 */
fun <T> Flow<T>.collectOnLifecycle(
    activity: AppCompatActivity,
    shouldBeRepeated: Boolean = true,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (value: T) -> Unit
) {
    activity.lifecycleScope.launch {
        if (shouldBeRepeated) {
            activity.repeatOnLifecycle(lifecycleState) {
                collectLatest(action)
            }
        } else collectLatest(action)
    }
}

//endregion
//region Operator Extension

fun <S, R> Flow<S>.combinePair(otherFlow: Flow<R>) =
    combine(otherFlow) { f1, f2 -> f1 to f2 }

fun <S, R, T> Flow<Pair<S, R>>.combineTriple(otherFlow: Flow<T>) =
    combine(otherFlow) { (f1, f2), f3 -> Triple(f1, f2, f3) }

fun <S, R, T, U> Flow<Triple<S, R, T>>.combineQuad(otherFlow: Flow<U>) =
    combine(otherFlow) { (s, r, t), u -> (s to r) to (t to u) }

fun <S, R, T, U> Flow<Pair<Pair<S, R>, Pair<T, U>>>.onEachQuad(action: suspend (S, R, T, U) -> Unit) =
    onEach { (f1, f2) ->
        val (s, r) = f1
        val (t, u) = f2
        action(s, r, t, u)
    }

fun <S, R, T, U, V> Flow<Pair<Pair<S, R>, Pair<T, U>>>.mapQuad(action: suspend (S, R, T, U) -> V) =
    map { (f1, f2) ->
        val (s, r) = f1
        val (t, u) = f2
        action(s, r, t, u)
    }

fun <T> Flow<T?>.throwOnNull(throwable: Throwable): Flow<T> =
    map { it ?: throw throwable }

fun <T> Flow<T>.onEachBindTo(stateFlow: MutableStateFlow<T>) =
    onEach { value -> stateFlow.update { value } }

suspend fun <T> Flow<T>.collectOn(stateFlow: MutableStateFlow<T>) {
    collect { value -> stateFlow.update { value } }
}

suspend fun <T> Flow<T>.collectLatestOn(stateFlow: MutableStateFlow<T>) {
    collectLatest { value -> stateFlow.update { value } }
}

fun <T> Flow<T>.waitFor(flow: Flow<Boolean>): Flow<T> =
    combine(flow.filter { it }) { s, _ -> s }

fun <T> Flow<T>.asLazyState(scope: CoroutineScope, initialValue: T): StateFlow<T> =
    stateIn(scope, SharingStarted.Lazily, initialValue)

inline val StateFlow<List<*>>.isEmpty
    get() = value.isEmpty()

//endregion
