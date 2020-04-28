package io.github.achmadhafid.zpack.extension

import android.os.Build

fun belowLollipopMR1()   = Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1
fun belowMarshmallow()   = Build.VERSION.SDK_INT < Build.VERSION_CODES.M
fun belowNougat()        = Build.VERSION.SDK_INT < Build.VERSION_CODES.N
fun belowNougatMR1()     = Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1
fun belowOreo()          = Build.VERSION.SDK_INT < Build.VERSION_CODES.O
fun belowOreoMR1()       = Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1
fun belowPie()           = Build.VERSION.SDK_INT < Build.VERSION_CODES.P
fun belowQ()             = Build.VERSION.SDK_INT < Build.VERSION_CODES.Q

fun atLeastLollipopMR1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1
fun atLeastMarshmallow() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun atLeastNougat()      = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
fun atLeastNougatMR1()   = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
fun atLeastOreo()        = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
fun atLeastOreoMR1()     = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1
fun atLeastPie()         = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
fun atLeastQ()           = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
