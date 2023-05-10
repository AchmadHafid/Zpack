package io.github.achmadhafid.zpack.extension

import android.app.AppOpsManager
import android.content.Context
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

inline val IntArray.arePermissionsGranted
    get() = all { it == PackageManager.PERMISSION_GRANTED }

fun Context.arePermissionsGranted(permissions: Array<out String>) =
    permissions.all { isPermissionGranted(it) }

fun Context.isPermissionGranted(permission: String) =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

inline val Context.hasWriteSettingPermission
    get() = Settings.System.canWrite(this)

inline val Context.hasAppUsagePermission: Boolean
    get() {
        @Suppress("DEPRECATION")
        val mode = if (atLeastQ()) appOpsManager.unsafeCheckOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(),
            packageName
        ) else appOpsManager.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(),
            packageName
        )

        return if (mode == AppOpsManager.MODE_DEFAULT) {
            checkCallingOrSelfPermission(
                "android.permission.PACKAGE_USAGE_STATS"
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            mode == AppOpsManager.MODE_ALLOWED
        }
    }

fun AppCompatActivity.requestPermissionCompat(permission: Array<out String>, requestCode: Int) =
    ActivityCompat.requestPermissions(this, permission, requestCode)

fun AppCompatActivity.shouldShowRequestPermissionRationales(permissions: Array<out String>) =
    permissions.any { shouldShowRequestPermissionRationale(it) }

fun Fragment.shouldShowRequestPermissionRationales(permissions: Array<out String>) =
    permissions.any { shouldShowRequestPermissionRationale(it) }
