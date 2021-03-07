package io.github.achmadhafid.zpack.extension.view

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.core.widget.ImageViewCompat
import io.github.achmadhafid.zpack.extension.resolveColor

infix fun ImageView.withTintRes(@ColorRes @AttrRes tintRes: Int) {
    val tintList = ColorStateList.valueOf(context.resolveColor(tintRes))
    ImageViewCompat.setImageTintList(this, tintList)
}
