@file:Suppress("unused")

package io.github.achmadhafid.zpack.delegate

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.github.achmadhafid.zpack.ktx.f
import io.github.achmadhafid.zpack.ktx.viewLifecycleState
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FragmentViewsBinding<T: View>(@IdRes val ids: Array<out Int>) :
    ReadWriteProperty<Fragment, MutableList<T?>> {

    private var _value: MutableList<T?> = mutableListOf()

    override fun getValue(thisRef: Fragment, property: KProperty<*>): MutableList<T?> {
        if (_value.isEmpty() && thisRef.viewLifecycleState != Lifecycle.State.DESTROYED) {
            ids.forEach {
                @Suppress("UNCHECKED_CAST")
                _value.add(thisRef.view!!.f<View>(it) as T?)
            }
            val lifecycle = thisRef.viewLifecycleOwner.lifecycle
            lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroy() {
                    _value.clear()
                    lifecycle.removeObserver(this)
                }
            })
        }
        return _value
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: MutableList<T?>) {
        _value = value
    }
}

inline fun <reified T: View> Fragment.fragmentViews(@IdRes vararg ids: Int) =
    FragmentViewsBinding<T>(ids.toTypedArray())
