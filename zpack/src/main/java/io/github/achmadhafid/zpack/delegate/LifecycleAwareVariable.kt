package io.github.achmadhafid.zpack.delegate

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LifecycleAwareVariable<T>(
    lifecycleOwner: LifecycleOwner,
    defaultValue: T? = null,
    onDestroy: (T?) -> Unit = {}
) : ReadWriteProperty<Any, T?> {

    private var _value: T? = null

    init {
        _value = defaultValue
        lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                lifecycleOwner.lifecycle.removeObserver(this)
                onDestroy(_value)
                _value = null
            }
        })
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T? = _value

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        _value = value
    }

}

class FragmentViewLifecycleAwareVariable<T>(
    fragment: Fragment,
    defaultValue: T? = null,
    onDestroy: (T?) -> Unit = {}
) : ReadWriteProperty<Any, T?> {

    private var _value: T? = null
    private var isObserverAttached = false

    init {
        _value = defaultValue
        fragment.viewLifecycleOwnerLiveData.observe(fragment) {
            if (!isObserverAttached) {
                it?.lifecycle?.let { lifecycle ->
                    isObserverAttached = true
                    lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            it.lifecycle.removeObserver(this)
                            onDestroy(_value)
                            _value = null
                            isObserverAttached = false
                        }
                    })
                }
            }
        }
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T? = _value

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        _value = value
    }

}

//region Consumer Extensions

inline fun <reified T> LifecycleOwner.lifecycleVar(
    defaultValue: T? = null,
    noinline onDestroy: (T?) -> Unit = {}
) = LifecycleAwareVariable(this, defaultValue, onDestroy)

inline fun <reified T> Fragment.viewLifecycleVar(
    defaultValue: T? = null,
    noinline onDestroy: (T?) -> Unit = {}
) = FragmentViewLifecycleAwareVariable(this, defaultValue, onDestroy)

//endregion
