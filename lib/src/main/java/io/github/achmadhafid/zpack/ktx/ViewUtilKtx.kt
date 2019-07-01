@file:Suppress("TooManyFunctions", "unused")

package io.github.achmadhafid.zpack.ktx

import android.widget.CompoundButton
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup

fun List<Pair<CompoundButton, Boolean>>.atLeastOneIsChecked(
    onCheckedChange: ((Int, Boolean) -> Unit)? = null
) {
    for ((index, button) in withIndex()) {
        button.first.isChecked = button.second
        button.first.setOnCheckedChangeListener { buttonView, isChecked ->
            onCheckedChange?.invoke(buttonView.id, isChecked)
            forEach {
                if (it.first.isChecked) return@setOnCheckedChangeListener
            }
            if (index != size - 1) this[index + 1].first.isChecked = true
            else this[0].first.isChecked = true

        }
    }
}

fun MaterialButtonToggleGroup.exactlyOneMustBeChecked(
    buttons: List<MaterialButton>,
    defaultChecked: MaterialButton,
    onCheckedChange: ((Int, Boolean) -> Unit)? = null
) {
    isSingleSelection = true
    buttons.forEach { button ->
        button.addOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                button.isCheckable = false
                buttons.forEach {
                    if (it !== button) it.isCheckable = true
                }
            }
        }
    }
    defaultChecked.isChecked = true
    addOnButtonCheckedListener { _, checkedId, isChecked ->
        onCheckedChange?.invoke(checkedId, isChecked)
    }
}

@Suppress("LongParameterList")
fun List<MaterialButton>.exactlyOneMustBeCheckedOrNone(
    buttonGroup: MaterialButtonToggleGroup,
    buttonGroupDefaultChecked: MaterialButton,
    buttonGroupListener: (id: Int, isChecked: Boolean) -> Unit,
    switchButton: CompoundButton,
    switchButtonDefaultIsChecked: Boolean,
    switchButtonListener: (isChecked: Boolean) -> Unit
) {
    val listener = MaterialButton.OnCheckedChangeListener { button, isChecked ->
        if (isChecked) {
            button.isCheckable = false
            forEach {
                if (it !== button) it.isCheckable = true
            }
        }
    }
    val groupListener = MaterialButtonToggleGroup.OnButtonCheckedListener { _, id, isChecked ->
        buttonGroupListener(id, isChecked)
    }
    switchButton.setOnCheckedChangeListener { _, isChecked ->
        if (isChecked) {
            forEach {
                it.isEnabled = true
                it.isCheckable = true
                it.addOnCheckedChangeListener(listener)
            }
            buttonGroupDefaultChecked.isChecked = true
            buttonGroup.addOnButtonCheckedListener(groupListener)
        } else {
            buttonGroup.removeOnButtonCheckedListener(groupListener)
            forEach {
                it.removeOnCheckedChangeListener(listener)
                it.isCheckable = true
                it.isChecked = false
                it.isEnabled = false
            }
        }
        switchButtonListener(isChecked)
    }
    switchButton.isChecked = switchButtonDefaultIsChecked
}
