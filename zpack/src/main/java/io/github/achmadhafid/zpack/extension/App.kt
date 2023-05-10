package io.github.achmadhafid.zpack.extension

import android.annotation.SuppressLint
import android.app.usage.UsageEvents
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable

inline val Context.appName get() = getAppName(packageName)

inline val Context.appIcon get() = getAppIcon(packageName)

@Suppress("DEPRECATION")
fun Context.getAppName(packageName: String): String? = runCatching {
    packageManager.getApplicationLabel(
        packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
    ).toString()
}.getOrNull()

fun Context.getAppIcon(packageName: String): Drawable? = runCatching {
    packageManager.getApplicationIcon(packageName)
}.getOrNull()

inline val Context.foregroundApp: String?
    get() {
        if (!hasAppUsagePermission) {
            w("App usage permission is required")
            return null
        }

        val usageEvents = System.currentTimeMillis().let {
            @Suppress("MagicNumber")
            // query for the last one minute
            usageStatsManager.queryEvents(it - 60 * 1000L, it)
        }
        val event = UsageEvents.Event()
        var foregroundApp = ""
        while (usageEvents.hasNextEvent()) {
            usageEvents.getNextEvent(event)

            @Suppress("DEPRECATION")
            val eventType = if (atLeastQ()) UsageEvents.Event.ACTIVITY_RESUMED
            else UsageEvents.Event.MOVE_TO_FOREGROUND

            if (event.eventType == eventType) {
                foregroundApp = event.packageName
            }
        }

        return foregroundApp
    }

@Suppress("DEPRECATION")
inline val Context.installedApps: List<ApplicationInfo>
    @SuppressLint("QueryPermissionsNeeded")
    get() = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

inline val Context.installedAppsWithLaunchIntent: List<ApplicationInfo>
    get() = installedApps
        .filter { it.packageName?.isNotEmpty() ?: false }
        .filter { packageManager.getLaunchIntentForPackage(it.packageName) != null }

inline val Context.installedAppsWithLaunchActivity: List<ApplicationInfo>
    @SuppressLint("QueryPermissionsNeeded")
    get() {
        val intent = Intent(Intent.ACTION_MAIN, null)
            .apply { addCategory(Intent.CATEGORY_LAUNCHER) }

        @Suppress("DEPRECATION")
        val packageNames = packageManager.queryIntentActivities(intent, 0)
            .map { it.activityInfo.packageName }

        return installedApps
            .filter { it.packageName?.isNotEmpty() ?: false }
            .filter { packageNames.contains(it.packageName) }
    }

inline val Context.installedLauncherApp: List<ApplicationInfo>
    @SuppressLint("QueryPermissionsNeeded")
    get() {
        val intent = Intent(Intent.ACTION_MAIN, null)
            .apply { addCategory(Intent.CATEGORY_LAUNCHER) }

        @Suppress("DEPRECATION")
        val packageNames = packageManager.queryIntentActivities(intent, 0)
            .map { it.activityInfo.packageName }

        return installedApps
            .filter { it.packageName?.isNotEmpty() ?: false }
            .filter {
                packageManager.getLaunchIntentForPackage(it.packageName) != null ||
                    packageNames.contains(it.packageName)
            }
    }
