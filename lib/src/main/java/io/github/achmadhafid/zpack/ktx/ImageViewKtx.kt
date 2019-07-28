package io.github.achmadhafid.zpack.ktx

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.core.widget.ImageViewCompat

fun ImageView.setImageTintListRes(@ColorRes @AttrRes tintRes: Int) {
    val tintList = ColorStateList.valueOf(context.resolveColor(tintRes))
    ImageViewCompat.setImageTintList(this, tintList)
}
