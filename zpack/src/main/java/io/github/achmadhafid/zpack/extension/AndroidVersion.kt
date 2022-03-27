package io.github.achmadhafid.zpack.extension

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

fun belowLollipopMR1() = Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1
fun belowMarshmallow() = Build.VERSION.SDK_INT < Build.VERSION_CODES.M
fun belowNougat() = Build.VERSION.SDK_INT < Build.VERSION_CODES.N
fun belowNougatMR1() = Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1
fun belowOreo() = Build.VERSION.SDK_INT < Build.VERSION_CODES.O
fun belowOreoMR1() = Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1
fun belowPie() = Build.VERSION.SDK_INT < Build.VERSION_CODES.P
fun belowQ() = Build.VERSION.SDK_INT < Build.VERSION_CODES.Q
fun belowR() = Build.VERSION.SDK_INT < Build.VERSION_CODES.R

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.LOLLIPOP_MR1)
fun atLeastLollipopMR1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.M)
fun atLeastMarshmallow() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N)
fun atLeastNougat() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N_MR1)
fun atLeastNougatMR1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
fun atLeastOreo() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

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
