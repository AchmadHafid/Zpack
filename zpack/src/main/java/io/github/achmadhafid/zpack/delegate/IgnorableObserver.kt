package io.github.achmadhafid.zpack.delegate

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

@PublishedApi
internal object IgnorableObserver {

    private val holder = HashMap<LifecycleOwner, MutableList<LiveData<*>>>()

    fun contains(owner: LifecycleOwner, data: LiveData<*>) = holder[owner]?.contains(data) ?: false

    fun add(owner: LifecycleOwner, data: LiveData<*>) {
        holder[owner]?.add(data) ?: run {
            holder[owner] = mutableListOf(data)
        }
    }

    fun remove(owner: LifecycleOwner, data: LiveData<*>) {
        holder[owner]?.remove(data)
    }

}

@MainThread
inline fun <T> LiveData<T>.observeOrIgnore(
    owner: LifecycleOwner,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    crossinline onChanged: (T) -> Unit
) {
    if (!IgnorableObserver.contains(owner, this)) {
        IgnorableObserver.add(owner, this)

//        owner.lifecycle.addObserver(object : LifecycleObserver {
//            private fun onRemoved() {
//                Log.d("IgnorableObserver", "observer removed: $this")
//                IgnorableObserver.remove(owner, this@observeOrIgnore)
//            }
//
//            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//            fun onStop() {
//                if (event == Lifecycle.Event.ON_STOP) onRemoved()
//            }
//
//            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
//            fun onDestroy() {
//                if (event == Lifecycle.Event.ON_DESTROY) onRemoved()
//            }
//        }.also {
//            Log.d("IgnorableObserver", "new observer attached: $it")
//        })

        owner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStop(owner: LifecycleOwner) {
                if (event == Lifecycle.Event.ON_STOP) onRemoved()
            }

            override fun onDestroy(owner: LifecycleOwner) {
                if (event == Lifecycle.Event.ON_DESTROY) onRemoved()
            }

            private fun onRemoved() {
                Log.d("IgnorableObserver", "observer removed: $this")
                IgnorableObserver.remove(owner, this@observeOrIgnore)
            }
        }).also {
            Log.d("IgnorableObserver", "new observer attached: $it")
        }

        observe(owner) { onChanged(it) }
    }
}
