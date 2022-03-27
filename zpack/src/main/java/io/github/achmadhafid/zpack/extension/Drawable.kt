package io.github.achmadhafid.zpack.extension

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat

fun Resources.bitmapFromVector(
    @DrawableRes drawableRes: Int,
    config: Bitmap.Config = Bitmap.Config.ARGB_8888
): Bitmap {
    val drawable = requireNotNull(ResourcesCompat.getDrawable(this, drawableRes, null))
    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, config)
    val canvas = Canvas(bitmap)

    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return bitmap
}
