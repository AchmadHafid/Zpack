@file:Suppress("TooManyFunctions", "unused")

package io.github.achmadhafid.zpack.ktx

import android.app.Service

inline val Service.isForeground
    get() = isForegroundServiceRunning(this::class.java.name)
