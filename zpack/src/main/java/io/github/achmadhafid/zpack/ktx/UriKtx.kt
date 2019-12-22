@file:Suppress("unused")

package io.github.achmadhafid.zpack.ktx

import android.net.Uri

val Uri.isContent
    get() = scheme?.contains("content") ?: false
val Uri.isFile
    get() = scheme?.contains("file") ?: false
val Uri.isContentOrFile
    get() = isContent || isFile
val Uri.isHttp
    get() = scheme?.contains("http") ?: false
val Uri.isHttps
    get() = scheme?.contains("https") ?: false
val Uri.isUrl
    get() = isHttp || isHttps
