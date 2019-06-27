package io.github.achmadhafid.zpack.util

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.reflect.KProperty

class LifecycleHandler(private val lifecycle: Lifecycle) : LifecycleObserver {

    private val handler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        lifecycle.removeObserver(this)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Handler = handler

}
