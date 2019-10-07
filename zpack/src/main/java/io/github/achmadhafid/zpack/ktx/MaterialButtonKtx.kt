@file:Suppress("unused")

package io.github.achmadhafid.zpack.ktx

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

fun MaterialButton.setIconRes(@DrawableRes iconRes: Int) {
    icon = ContextCompat.getDrawable(context, iconRes)
}
