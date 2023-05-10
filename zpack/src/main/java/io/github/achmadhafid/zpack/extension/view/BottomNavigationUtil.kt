package io.github.achmadhafid.zpack.extension.view

import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

infix fun BottomNavigationView.setCurrentItemTo(itemId: Int) {
    if (selectedItemId != itemId) {
        selectedItemId = itemId
    }
}

inline val BottomSheetDialogFragment.bottomSheetBehavior: BottomSheetBehavior<FrameLayout>?
    get() = dialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)?.let {
        BottomSheetBehavior.from(it)
    }

fun BottomSheetDialogFragment.setDismissible(isDismissible: Boolean = true) {
        dialog?.setCancelable(isDismissible)
        dialog?.setCanceledOnTouchOutside(isDismissible)
        bottomSheetBehavior?.isHideable = isDismissible
    }
