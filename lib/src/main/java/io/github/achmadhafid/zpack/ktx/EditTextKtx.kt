@file:Suppress("unused")

package io.github.achmadhafid.zpack.ktx

import android.text.InputType
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged

var EditText.value: String
    get() = text.toString()
    set(value) {
        setText(value)
    }

fun EditText.setTextAndMoveCursor(text: CharSequence) {
    setText(text)
    setSelection(text.length)
}

fun EditText.onUserInput(
    char: Char,
    shouldTrim: Boolean = true,
    function: (String) -> Unit
) {
    doAfterTextChanged {
        it?.toString()?.let { input ->
            if (input.isNotEmpty() && input.last() == char) {
                function(if (shouldTrim) input.trim() else input)
            }
        }
    }
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

