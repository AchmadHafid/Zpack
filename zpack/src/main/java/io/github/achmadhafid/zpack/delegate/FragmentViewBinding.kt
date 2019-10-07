@file:Suppress("unused")

package io.github.achmadhafid.zpack.delegate

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.github.achmadhafid.zpack.ktx.f
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FragmentViewBinding<T: View>(@IdRes val id: Int) : ReadWriteProperty<Fragment, T?> {

    private var _value: T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T? {
        if (_value == null) {
            thisRef.view?.let { view ->
                @Suppress("UNCHECKED_CAST")
                _value = view.f<View>(id) as T?
                val lifecycle = thisRef.viewLifecycleOwner.lifecycle
                lifecycle.addObserver(object : LifecycleObserver {
                    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                    fun onDestroy() {
                        _value = null
                        lifecycle.removeObserver(this)
                    }
                })
            }
        }
        return _value
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T?) {
        _value = value
    }
}

inline fun <reified T: View> Fragment.fragmentView(@IdRes id: Int) =
    FragmentViewBinding<T>(id)
