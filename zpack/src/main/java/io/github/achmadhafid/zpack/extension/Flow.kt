package io.github.achmadhafid.zpack.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

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

fun <S, R> Flow<S>.combinePair(otherFlow: Flow<R>) =
    combine(otherFlow) { f1, f2 -> f1 to f2 }

fun <S, R, T> Flow<Pair<S, R>>.combineTriple(otherFlow: Flow<T>) =
    combine(otherFlow) { (f1, f2), f3 -> Triple(f1, f2, f3) }

fun <T> Flow<T>.onEachBindTo(stateFlow: MutableStateFlow<T>) =
    onEach { stateFlow.value = it }

suspend fun <T> Flow<T>.collectOn(stateFlow: MutableStateFlow<T>) {
    collect { stateFlow.value = it }
}

inline val StateFlow<List<*>>.isEmpty
    get() = value.isEmpty()
