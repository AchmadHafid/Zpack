@file:Suppress("unused")

package io.github.achmadhafid.zpack.delegate

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LifecycleAwareValue<T>(lifecycle: Lifecycle, defaultValue: T? = null) : ReadWriteProperty<Any, T?> {

    private var _value: T? = null

    init {
        _value = defaultValue
        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                _value = null
                lifecycle.removeObserver(this)
            }
        })
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T? = _value

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        _value = value
    }

}

inline fun <reified T> LifecycleOwner.lifecycleValue(defaultValue: T? = null) =
    LifecycleAwareValue(lifecycle, defaultValue)

inline fun <reified T> LifecycleOwner.lifecycleValue(init: () -> T?) =
    LifecycleAwareValue(lifecycle, init())
