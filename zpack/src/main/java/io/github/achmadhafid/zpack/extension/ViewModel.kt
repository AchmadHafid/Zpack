package io.github.achmadhafid.zpack.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T: ViewModel> ViewModelProvider.getViewModel() =
    get(T::class.java)

inline fun <reified T: ViewModel> FragmentActivity.getViewModel() =
    ViewModelProvider(this).getViewModel<T>()

inline fun <reified T: ViewModel> FragmentActivity.getViewModel(factory: ViewModelProvider.Factory) =
    ViewModelProvider(this, factory).getViewModel<T>()

inline fun <reified T : ViewModel> Fragment.getViewModel() =
    ViewModelProvider(this).getViewModel<T>()

inline fun <reified T : ViewModel> Fragment.getViewModel(factory: ViewModelProvider.Factory) =
    ViewModelProvider(this, factory).getViewModel<T>()

inline fun <reified T : ViewModel> Fragment.getViewModelWithActivityScope() =
    ViewModelProvider(requireActivity()).getViewModel<T>()

inline fun <reified T : ViewModel> Fragment.getViewModelWithActivityScope(factory: ViewModelProvider.Factory) =
    ViewModelProvider(requireActivity(), factory).getViewModel<T>()

inline fun <reified T : ViewModel> Fragment.getViewModelWithParentScope() =
    ViewModelProvider(requireParentFragment()).getViewModel<T>()

inline fun <reified T : ViewModel> Fragment.getViewModelWithParentScope(factory: ViewModelProvider.Factory) =
    ViewModelProvider(requireParentFragment(), factory).getViewModel<T>()
