package io.github.achmadhafid.zpack.ktx

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes

inline fun <reified T> ViewGroup.inflate(@IdRes id: Int, attachToRoot: Boolean = false): T =
    LayoutInflater.from(context).inflate(id, this, attachToRoot) as T
