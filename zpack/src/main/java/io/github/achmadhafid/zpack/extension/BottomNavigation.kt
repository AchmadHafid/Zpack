package io.github.achmadhafid.zpack.extension

import com.google.android.material.bottomnavigation.BottomNavigationView

infix fun BottomNavigationView.setCurrentItemTo(itemId: Int) {
    if (selectedItemId != itemId) {
        selectedItemId = itemId
    }
}
