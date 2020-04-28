package io.github.achmadhafid.zpack.extension

import android.content.Context
import android.net.Uri
import java.io.File

inline val Uri.isContent get() = scheme?.contains("content") ?: false
inline val Uri.isFile get() = scheme?.contains("file") ?: false
inline val Uri.isContentOrFile get() = isContent || isFile
inline val Uri.isHttp get() = scheme?.contains("http") ?: false
inline val Uri.isHttps get() = scheme?.contains("https") ?: false
inline val Uri.isUrl get() = isHttp || isHttps

fun Context.deleteLocalUris(vararg uris: Uri) {
    uris.forEach(::deleteLocalUri)
}

fun Context.deleteLocalUris(uris: List<Uri>) {
    uris.forEach(::deleteLocalUri)
}

@Suppress("NestedBlockDepth")
fun Context.deleteLocalUri(uri: Uri) {
    when {
        uri.isContent -> contentResolver.delete(uri, null, null)
        uri.isFile -> uri.path?.let { with(File(it)) { if (exists()) delete() } }
        else -> return
    }
}
