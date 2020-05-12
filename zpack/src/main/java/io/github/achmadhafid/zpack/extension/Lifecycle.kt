package io.github.achmadhafid.zpack.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope

inline val FragmentActivity.lifecycleState
    get() = lifecycle.currentState

inline val Fragment.lifecycleState
    get() = lifecycle.currentState

inline val Fragment.viewLifecycle
    get() = viewLifecycleOwner.lifecycle

inline val Fragment.viewLifecycleScope
    get() = viewLifecycleOwner.lifecycleScope

inline val Fragment.viewLifecycleState
    get() = viewLifecycleOwner.lifecycle.currentState


