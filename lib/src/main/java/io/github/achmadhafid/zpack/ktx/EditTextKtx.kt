package io.github.achmadhafid.zpack.ktx

import android.text.InputType
import android.widget.EditText

var EditText.value: String
    get() = text.toString()
    set(value) {
        setText(value)
    }

fun EditText.moveCursorToTheEnd() {
    setSelection(text.length)
}

//region Input Type

fun EditText.showPasswordInputType() {
    inputType = INPUT_TYPE_VISIBLE_PASSWORD
}

fun EditText.hidePasswordInputType() {
    inputType = INPUT_TYPE_HIDDEN_PASSWORD
}

fun EditText.togglePasswordVisibility() {
    when (inputType) {
        INPUT_TYPE_HIDDEN_PASSWORD -> showPasswordInputType()
        INPUT_TYPE_VISIBLE_PASSWORD -> hidePasswordInputType()
    }
}

const val INPUT_TYPE_VISIBLE_PASSWORD =
    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

const val INPUT_TYPE_HIDDEN_PASSWORD =
    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

//endregion

