package io.github.achmadhafid.zpack.extension

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

fun belowOreoMR1() = Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1
fun belowPie() = Build.VERSION.SDK_INT < Build.VERSION_CODES.P
fun belowQ() = Build.VERSION.SDK_INT < Build.VERSION_CODES.Q
fun belowR() = Build.VERSION.SDK_INT < Build.VERSION_CODES.R
fun belowS() = Build.VERSION.SDK_INT < Build.VERSION_CODES.S
fun belowS2() = Build.VERSION.SDK_INT < Build.VERSION_CODES.S_V2
fun belowTiramisu() = Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O_MR1)
fun atLeastOreoMR1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
fun atLeastPie() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
fun atLeastQ() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
fun atLeastR() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun atLeastS() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S_V2)
fun atLeastS2() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
fun atLeastTiramisu() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
