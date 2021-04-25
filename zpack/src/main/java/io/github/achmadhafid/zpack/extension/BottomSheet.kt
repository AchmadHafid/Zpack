package io.github.achmadhafid.zpack.extension

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun BottomSheetDialogFragment.dismissAfter(delayInSeconds: Int, callback: () -> Unit = {}) {
    viewLifecycleScope.launch {
        delay(delayInSeconds.toLong())
        callback()
        dismiss()
    }
}
