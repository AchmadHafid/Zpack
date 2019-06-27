@file:Suppress("TooManyFunctions", "WildcardImport")

package io.github.achmadhafid.zpack.ktx

import android.annotation.SuppressLint
import android.app.*
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.createChooser
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.PowerManager
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

//region System Service

inline val Context.activityManager
    get() = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
inline val Context.appOpsManager
    get() = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
inline val Context.usageStatsManager
    @SuppressLint("WrongConstant")
    get() = getSystemService("usagestats") as UsageStatsManager
inline val Context.notificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
inline val Context.powerManager: PowerManager
    get() = getSystemService(Context.POWER_SERVICE) as PowerManager
val Context.keyGuardManager
    get() = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
val Context.telephonyManager
    get() = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
val Context.layoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
val Context.connectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
val Context.wifiManager
    get() = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

//endregion
//region Resource Binding Helper

@MainThread
fun Context.dimenRes(@DimenRes id: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getDimensionPixelSize(id)
    }

@MainThread
fun Context.stringRes(@StringRes id: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getString(id)
    }

fun Context.stringArrayRes(@ArrayRes id: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getStringArray(id)
    }

fun Context.stringListRes(@ArrayRes id: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getStringArray(id)
            .toList()
    }

@MainThread
fun Context.intRes(@IntegerRes id: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getInteger(id)
    }

fun Context.intArrayRes(@ArrayRes id: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getIntArray(id)
    }

fun Context.intListRes(@ArrayRes id: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getIntArray(id)
            .toList()
    }

@MainThread
fun Context.longRes(@IntegerRes id: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getInteger(id)
            .toLong()
    }

fun Context.longArrayRes(@ArrayRes id: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getIntArray(id)
            .map { it.toLong() }
            .toLongArray()
    }

fun Context.longListRes(@ArrayRes id: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getIntArray(id)
            .map { it.toLong() }
    }

fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)

fun Context.resolveColor(@ColorRes @AttrRes id: Int) = with(TypedValue()) {
    if (theme.resolveAttribute(id, this, true)) {
        data
    } else {
        ContextCompat.getColor(this@resolveColor, id)
    }
}

private fun Context.applyDimension(unit: Int, number: Number) =
    TypedValue.applyDimension(unit, number.toFloat(), resources.displayMetrics)

fun Context.dpToPx(dp: Number) = applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp)
fun Context.pxToDp(px: Number) = applyDimension(TypedValue.COMPLEX_UNIT_PX, px)
fun Context.spToPx(sp: Int)    = applyDimension(TypedValue.COMPLEX_UNIT_PX, sp)
fun Context.pxToSp(px: Int)    = applyDimension(TypedValue.COMPLEX_UNIT_PX, px)

//endregion
//region Intent Helper

inline fun <reified T : Any> Context.intent() =
    Intent(this, T::class.java)

inline fun <reified T : Any> Context.intent(body: Intent.() -> Unit): Intent =
    Intent(this, T::class.java).apply(body)

inline fun Context.intent(action: String, body: Intent.() -> Unit): Intent =
    Intent(action).apply(body)

fun Context.openAppDetailSettings() {
    startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        .apply { data = Uri.parse(`package`) })
}

fun Context.openAdminSettings() {
    startActivity(
        Intent(
            android.content.Intent()
                .setComponent(
                    android.content.ComponentName(
                        "com.android.settings",
                        "com.android.settings.DeviceAdminSettings"
                    )
                )
        )
    )
}

fun Context.openUsageAccessSettings() {
    startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
}

fun Context.openWirelessSettings() {
    startActivity(
        Intent(Settings.ACTION_WIRELESS_SETTINGS)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    )
}

fun Context.openWriteSettings() {
    if (atLeastMarshmallow()) {
        startActivity(Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
            .apply { data = Uri.parse("package:$`package`") })
    }
}

fun Context.homeLauncher() {
    startActivity(
        Intent(Intent.ACTION_MAIN)
            .addCategory(Intent.CATEGORY_HOME)
            .setPackage(
                packageManager.queryIntentActivities(
                    Intent(Intent.ACTION_MAIN)
                        .addCategory(Intent.CATEGORY_HOME)
                    , PackageManager.MATCH_DEFAULT_ONLY
                )[0].activityInfo.packageName
            )
    )
}

@Suppress("TooGenericExceptionCaught")
fun Context.browse(url: String, newTask: Boolean = false): Boolean {
    return try {
        val intent = intent(Intent.ACTION_VIEW) {
            data = Uri.parse(url)
            if (newTask) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
        true
    } catch (e: Exception) {
        false
    }
}

fun Context.share(text: String, subject: String? = null): Boolean {
    val intent = Intent().apply {
        type = "text/plain"
        subject?.let { putExtra(Intent.EXTRA_SUBJECT, it) }
        putExtra(Intent.EXTRA_TEXT, text)
    }
    return try {
        startActivity(createChooser(intent, null))
        true
    } catch (e: ActivityNotFoundException) {
        false
    }
}

fun Context.email(email: String, subject: String = "", text: String = ""): Boolean {
    val intent = intent(Intent.ACTION_SENDTO) {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        if (subject.isNotBlank()) putExtra(Intent.EXTRA_SUBJECT, subject)
        if (text.isNotBlank()) putExtra(Intent.EXTRA_TEXT, text)
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
        return true
    }
    return false
}

@Suppress("TooGenericExceptionCaught")
fun Context.makeCall(number: String): Boolean {
    return try {
        startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$number")))
        true
    } catch (e: Exception) {
        false
    }
}

fun Context.dial(tel: String?) = startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$tel")))

@Suppress("TooGenericExceptionCaught")
fun Context.sendSms(number: String, text: String = ""): Boolean {
    return try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("smsto:$number")).apply {
            putExtra("sms_body", text)
        }
        startActivity(intent)
        true
    } catch (e: Exception) {
        false
    }
}

//endregion
//region Navigation

@MainThread
inline fun <reified T : AppCompatActivity> Context.startActivity() =
    startActivity(Intent(this, T::class.java))

@MainThread
inline fun <reified T : AppCompatActivity> Context.startActivity(block: Intent.() -> Unit) =
    startActivity(Intent(this, T::class.java).apply(block))

//endregion
//region Service Helper

inline fun <reified T : Service> Context.startService() =
    startService(Intent(this, T::class.java))

@Suppress("DEPRECATION")
fun Context.getRunningServiceInfo(serviceClassName: String): ActivityManager.RunningServiceInfo? {
    for (serviceInfo in activityManager.getRunningServices(Integer.MAX_VALUE)) {
        if (serviceClassName == serviceInfo.service.className) {
            return serviceInfo
        }
    }
    return null
}

fun Context.isForegroundServiceRunning(serviceClassName: String) =
    getRunningServiceInfo(serviceClassName)
        ?.foreground
        ?: false

fun Context.startForegroundServiceCompat(intent: Intent) =
    ContextCompat.startForegroundService(this, intent)

//endregion
//region Toast Helper

fun Context.toastShort(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toastShort(@StringRes messageRes: Int) {
    Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()
}

fun Context.toastLong(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.toastLong(@StringRes messageRes: Int) {
    Toast.makeText(this, messageRes, Toast.LENGTH_LONG).show()
}

//endregion
//region Permission Helper

fun Context.isGranted(permission: String) =
    when {
        belowMarshmallow() -> true
        else -> {
            ContextCompat.checkSelfPermission(this, permission) ==
                    PackageManager.PERMISSION_GRANTED
        }
    }

val Context.hasWriteSettingPermission
    get() = when{
        atLeastMarshmallow() -> Settings.System.canWrite(this)
        else -> null
    }

val Context.hasAppUsagePermission: Boolean
    get() {
        val mode = appOpsManager.checkOpNoThrow(
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

//endregion
//region Internet Connection Helper

inline val Context.isConnected
    get() = connectivityManager.activeNetworkInfo.isConnected

inline val Context.isMobileDataEnabled: Boolean?
    get() = when {
        atLeastOreo() -> telephonyManager.isDataEnabled
        else -> null
    }

inline val Context.isWifiEnabled
    get() = wifiManager.isWifiEnabled

//endregion
//region Device Helper

val Context.isScreenOn
    get() = powerManager.isInteractive

val Context.isDeviceLocked
    get() = if (atLeastLollipopMR1()) keyGuardManager.isDeviceLocked
    else keyGuardManager.isKeyguardLocked

inline val Context.displayWidth: Int
    get() = resources.displayMetrics.widthPixels
inline val Context.displayHeight: Int
    get() = resources.displayMetrics.heightPixels

//endregion
//region App Helper

val Context.foregroundApp: String?
    get() {
        if (!hasAppUsagePermission) {
            return null
        }

        val usageEvents = System.currentTimeMillis().let {
            @Suppress("MagicNumber")
            // query for the last one minute
            usageStatsManager.queryEvents(it - 600000L, it)
        }
        val event = UsageEvents.Event()
        var foregroundApp = ""
        while (usageEvents.hasNextEvent()) {
            usageEvents.getNextEvent(event)
            if (event.eventType == UsageEvents.Event.MOVE_TO_FOREGROUND) {
                foregroundApp = event.packageName
            }
        }

        return foregroundApp
    }

val Context.installedApps: List<ApplicationInfo>
    get() = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

val Context.installedAppsWithLaunchIntent: List<ApplicationInfo>
    get() = installedApps
        .filter { it.packageName?.isNotEmpty() ?: false }
        .filter { it.name?.isNotEmpty() ?: false }
        .filter { packageManager.getLaunchIntentForPackage(it.packageName) != null }

fun Context.getAppName(packageName: String): String? {
    return try {
        packageManager.getApplicationLabel(
            packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        ).toString()
    } catch (ignored: PackageManager.NameNotFoundException) {
        null
    }
}

fun Context.getAppIcon(packageName: String): Drawable? {
    return try {
        packageManager.getApplicationIcon(packageName)
    } catch (e: PackageManager.NameNotFoundException) {
        null
    }
}

//endregion
