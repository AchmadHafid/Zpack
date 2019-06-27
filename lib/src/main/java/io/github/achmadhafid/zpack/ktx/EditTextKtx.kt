package io.github.achmadhafid.zpack.ktx

import android.widget.EditText

var EditText.value: String
    get() = text.toString()
    set(value) {
        setText(value)
    }
