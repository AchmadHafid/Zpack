package io.github.achmadhafid.zpack.ktx

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T: ViewModel> ViewModelProvider.getViewModel() =
    get(T::class.java)
