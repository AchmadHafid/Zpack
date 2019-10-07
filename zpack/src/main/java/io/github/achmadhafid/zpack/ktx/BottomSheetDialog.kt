@file:Suppress("unused")

package io.github.achmadhafid.zpack.ktx

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

fun BottomSheetDialog.setExpanded() {
    findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)?.let {
        BottomSheetBehavior.from(it).state = BottomSheetBehavior.STATE_EXPANDED
    }
}
