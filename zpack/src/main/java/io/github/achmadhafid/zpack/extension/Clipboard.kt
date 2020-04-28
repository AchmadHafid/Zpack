package io.github.achmadhafid.zpack.extension

import android.content.ClipData
import android.content.Context
import android.net.Uri

fun Context.copyPlainTextToClipboard(label: CharSequence, text: CharSequence) {
    clipboardManager.setPrimaryClip(ClipData.newPlainText(label, text))
}

fun Context.copyHtmlTextToClipboard(label: CharSequence, text: CharSequence, htmlText: String) {
    clipboardManager.setPrimaryClip(ClipData.newHtmlText(label, text, htmlText))
}

fun Context.copyRawUriToClipboard(label: CharSequence, uri: Uri) {
    clipboardManager.setPrimaryClip(ClipData.newRawUri(label, uri))
}
