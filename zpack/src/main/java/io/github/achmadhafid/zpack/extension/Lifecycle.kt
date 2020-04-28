package io.github.achmadhafid.zpack.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

inline val FragmentActivity.lifecycleState
    get() = lifecycle.currentState

inline val Fragment.lifecycleState
    get() = lifecycle.currentState

inline val Fragment.viewLifecycleState
    get() = viewLifecycleOwner.lifecycle.currentState
