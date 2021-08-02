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

fun BottomSheetDialogFragment.setDismissable(isDismissable: Boolean = true) {
        dialog?.setCancelable(isDismissable)
        dialog?.setCanceledOnTouchOutside(isDismissable)
        bottomSheetBehavior?.isHideable = isDismissable
    }
