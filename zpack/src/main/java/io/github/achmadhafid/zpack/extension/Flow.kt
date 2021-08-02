package io.github.achmadhafid.zpack.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
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

fun <T : Any?> Flow<T>.collectWhenCreated(
    fragment: Fragment,
    action: suspend (value: T) -> Unit
) {
    fragment.viewLifecycleOwner.lifecycleScope.launch {
        collectLatest(action)
    }
}

fun <T : Any?> Flow<T>.collectWhenStarted(
    fragment: Fragment,
    action: suspend (value: T) -> Unit
) {
    fragment.viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        collectLatest(action)
    }
}

fun <T : Any?> Flow<T>.collectWhenResumed(
    fragment: Fragment,
    action: suspend (value: T) -> Unit
) {
    fragment.viewLifecycleOwner.lifecycleScope.launchWhenResumed {
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

fun <T : Any?> Flow<T>.collectWhenCreated(
    activity: AppCompatActivity,
    action: suspend (value: T) -> Unit
) {
    activity.lifecycleScope.launch {
        collectLatest(action)
    }
}

fun <T : Any?> Flow<T>.collectWhenStarted(
    activity: AppCompatActivity,
    action: suspend (value: T) -> Unit
) {
    activity.lifecycleScope.launchWhenStarted {
        collectLatest(action)
    }
}

fun <T : Any?> Flow<T>.collectWhenResumed(
    activity: AppCompatActivity,
    action: suspend (value: T) -> Unit
) {
    activity.lifecycleScope.launchWhenResumed {
        collectLatest(action)
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
    onEach { stateFlow.value = it }

suspend fun <T> Flow<T>.collectOn(stateFlow: MutableStateFlow<T>) {
    collect { stateFlow.value = it }
}

inline val StateFlow<List<*>>.isEmpty
    get() = value.isEmpty()

fun <T> Flow<T>.asLazyState(scope: CoroutineScope, initialValue: T) =
    stateIn(scope, SharingStarted.Lazily, initialValue)

//endregion
