@file:Suppress("unused")

package io.github.achmadhafid.zpack.ktx

import android.accounts.AccountManager
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.AlarmManager
import android.app.AppOpsManager
import android.app.DownloadManager
import android.app.KeyguardManager
import android.app.NotificationManager
import android.app.SearchManager
import android.app.Service
import android.app.UiModeManager
import android.app.WallpaperManager
import android.app.admin.DevicePolicyManager
import android.app.job.JobScheduler
import android.app.usage.NetworkStatsManager
import android.app.usage.StorageStatsManager
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.appwidget.AppWidgetManager
import android.bluetooth.BluetoothManager
import android.companion.CompanionDeviceManager
import android.content.ActivityNotFoundException
import android.content.ClipboardManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.Intent.createChooser
import android.content.RestrictionsManager
import android.content.pm.ApplicationInfo
import android.content.pm.CrossProfileApps
import android.content.pm.LauncherApps
import android.content.pm.PackageManager
import android.content.pm.ShortcutManager
import android.graphics.drawable.Drawable
import android.hardware.ConsumerIrManager
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import android.hardware.display.DisplayManager
import android.hardware.input.InputManager
import android.hardware.usb.UsbManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaRouter
import android.media.midi.MidiManager
import android.media.projection.MediaProjectionManager
import android.media.session.MediaSessionManager
import android.media.tv.TvInputManager
import android.net.ConnectivityManager
import android.net.IpSecManager
import android.net.Uri
import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import android.net.wifi.aware.WifiAwareManager
import android.net.wifi.p2p.WifiP2pManager
import android.net.wifi.rtt.WifiRttManager
import android.nfc.NfcManager
import android.os.DropBoxManager
import android.os.HardwarePropertiesManager
import android.os.PowerManager
import android.os.UserManager
import android.os.Vibrator
import android.os.health.SystemHealthManager
import android.os.storage.StorageManager
import android.print.PrintManager
import android.provider.Settings
import android.telecom.TelecomManager
import android.telephony.CarrierConfigManager
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.CaptioningManager
import android.view.textclassifier.TextClassificationManager
import android.view.textservice.TextServicesManager
import android.widget.Toast
import androidx.annotation.ArrayRes
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.IntegerRes
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat
import androidx.core.os.UserManagerCompat
import java.io.File

//region System Service

inline val Context.accessibilityManager
    get() = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
inline val Context.accountManager
    get() = getSystemService(Context.ACCOUNT_SERVICE) as AccountManager
inline val Context.activityManager
    get() = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
inline val Context.alarmManager
    get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager
inline val Context.appOpsManager
    get() = (getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager).also {
        Log.d("Zpack", "Please use `AppOpsManagerCompat instead`")
    }
inline val Context.appWidgetManager
    get() = getSystemService(Context.APPWIDGET_SERVICE) as AppWidgetManager
inline val Context.audioManager
    get() = getSystemService(Context.AUDIO_SERVICE) as AudioManager
inline val Context.bluetoothManager
    get() = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
inline val Context.cameraManager
    get() = getSystemService(Context.CAMERA_SERVICE) as CameraManager
inline val Context.captioningManager
    get() = getSystemService(Context.CAPTIONING_SERVICE) as CaptioningManager
inline val Context.clipboardManager
    get() = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
inline val Context.connectivityManager: ConnectivityManager
    get() = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).also {
        Log.d("Zpack", "Please use `ConnectivityManagerCompat instead`")
    }
inline val Context.consumerIrManager
    get() = getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager
inline val Context.devicePolicyManager
    get() = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
inline val Context.displayManager
    get() = (getSystemService(Context.DISPLAY_SERVICE) as DisplayManager).also {
        Log.d("Zpack", "Please use `DisplayManagerCompatCompat` instead")
    }
inline val Context.downloadManager
    get() = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
inline val Context.dropBoxManager
    get() = getSystemService(Context.DROPBOX_SERVICE) as DropBoxManager
inline val Context.inputManager
    get() = getSystemService(Context.INPUT_SERVICE) as InputManager
inline val Context.jobScheduler
    get() = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
inline val Context.keyGuardManager
    get() = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
inline val Context.launcherApps
    get() = getSystemService(Context.LAUNCHER_APPS_SERVICE) as LauncherApps
inline val Context.layoutInflate
    get() = (getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).also {
        Log.d("Zpack", "Please use `LayoutInflaterCompat instead`")
    }
inline val Context.locationManager
    get() = getSystemService(Context.LOCATION_SERVICE) as LocationManager
inline val Context.mediaProjectionManager
    get() = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
inline val Context.mediaRouter
    get() = getSystemService(Context.MEDIA_ROUTER_SERVICE) as MediaRouter
inline val Context.mediaSessionManager
    get() = getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager
inline val Context.nfcManager
    get() = getSystemService(Context.NFC_SERVICE) as NfcManager
inline val Context.notificationManager
    get() = (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).also {
        Log.d("Zpack", "Please use `notificationManagerCompat instead`")
    }
inline val Context.notificationManagerCompat
    get() = NotificationManagerCompat.from(this)
inline val Context.nsdManager
    get() = getSystemService(Context.NSD_SERVICE) as NsdManager
inline val Context.powerManager: PowerManager
    get() = getSystemService(Context.POWER_SERVICE) as PowerManager
inline val Context.printManager
    get() = getSystemService(Context.PRINT_SERVICE) as PrintManager
inline val Context.restrictionsManager
    get() = getSystemService(Context.RESTRICTIONS_SERVICE) as RestrictionsManager
inline val Context.searchManager
    get() = getSystemService(Context.SEARCH_SERVICE) as SearchManager
inline val Context.sensorManager
    get() = getSystemService(Context.SENSOR_SERVICE) as SensorManager
inline val Context.storageManager
    get() = getSystemService(Context.STORAGE_SERVICE) as StorageManager
inline val Context.telecomManager
    get() = getSystemService(Context.TELECOM_SERVICE) as TelecomManager
inline val Context.telephonyManager
    get() = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
inline val Context.textServicesManager
    get() = getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE) as TextServicesManager
inline val Context.tvInputManager
    get() = getSystemService(Context.TV_INPUT_SERVICE) as TvInputManager
inline val Context.uiModeManager
    get() = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
inline val Context.usageStatsManager
    @SuppressLint("WrongConstant")
    get() = getSystemService("usagestats") as UsageStatsManager
inline val Context.usbManager
    get() = getSystemService(Context.USB_SERVICE) as UsbManager
inline val Context.userManager
    get() = getSystemService(Context.USER_SERVICE) as UserManager
inline val Context.vibrator
    get() = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
inline val Context.wallpaperManager
    get() = getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager
inline val Context.wifiManager
    get() = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
inline val Context.wifiP2pManager
    get() = getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
inline val Context.windowManager
    get() = getSystemService(Context.WINDOW_SERVICE) as WindowManager

//region Lollipop MR1

inline val Context.subscriptionManager
    get() = if (atLeastLollipopMR1())
        getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager
    else null

//endregion
//region Marshmallow

inline val Context.carrierConfigManager
    get() = if (atLeastMarshmallow())
        getSystemService(Context.CARRIER_CONFIG_SERVICE) as CarrierConfigManager
    else null
inline val Context.midiManager
    get() = if (atLeastMarshmallow())
        getSystemService(Context.MIDI_SERVICE) as MidiManager
    else null
inline val Context.networkStatsManager
    get() = if (atLeastMarshmallow())
        getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager
    else null

//endregion
//region Nougat

inline val Context.hardwarePropertiesManager
    get() = if (atLeastNougat())
        getSystemService(Context.HARDWARE_PROPERTIES_SERVICE) as HardwarePropertiesManager
    else null
inline val Context.systemHealthManager
    get() = if (atLeastNougat())
        getSystemService(Context.SYSTEM_HEALTH_SERVICE) as SystemHealthManager
    else null

//endregion
//region Nougat MR1

inline val Context.shortcutManager
    get() = (if (atLeastNougatMR1())
        getSystemService(Context.SHORTCUT_SERVICE) as ShortcutManager
    else null).also {
        Log.d("Zpack", "Please use `ShortcutManagerCompat instead`")
    }

//endregion
//region Oreo

inline val Context.companionDeviceManager
    get() = if (atLeastOreo())
        getSystemService(Context.COMPANION_DEVICE_SERVICE) as CompanionDeviceManager
    else null
inline val Context.storageStatsManager
    get() = if (atLeastOreo())
        getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
    else null
inline val Context.textClassificationManager
    get() = if (atLeastOreo())
        getSystemService(Context.TEXT_CLASSIFICATION_SERVICE) as TextClassificationManager
    else null
inline val Context.wifiAwareManager
    get() = if (atLeastOreo())
        getSystemService(Context.WIFI_AWARE_SERVICE) as WifiAwareManager
    else null

//endregion
//region Pie

inline val Context.crossProfileApps
    get() = if (atLeastPie())
        getSystemService(Context.CROSS_PROFILE_APPS_SERVICE) as CrossProfileApps
    else null
inline val Context.ipSecManager
    get() = if (atLeastPie())
        getSystemService(Context.IPSEC_SERVICE) as IpSecManager
    else null
inline val Context.wifiRttManager
    get() = if (atLeastPie())
        getSystemService(Context.WIFI_RTT_RANGING_SERVICE) as WifiRttManager
    else null

//endregion

fun LocationManager.isLocationEnabledCompat() =
    LocationManagerCompat.isLocationEnabled(this)
fun UserManager.isUserUnlockedCompat(context: Context) =
    UserManagerCompat.isUserUnlocked(context)

//endregion
//region Resource Binding

@MainThread
fun Context.stringRes(@StringRes stringRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getString(stringRes)
    }

@MainThread
fun Context.stringArrayRes(@ArrayRes arrayRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getStringArray(arrayRes)
    }

@MainThread
fun Context.stringListRes(@ArrayRes arrayRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getStringArray(arrayRes)
            .toList()
    }

@MainThread
fun Context.intRes(@IntegerRes intRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getInteger(intRes)
    }

@MainThread
fun Context.intArrayRes(@ArrayRes arrayRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getIntArray(arrayRes)
    }

@MainThread
fun Context.intListRes(@ArrayRes arrayRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getIntArray(arrayRes)
            .toList()
    }

@MainThread
fun Context.longRes(@IntegerRes intRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getInteger(intRes)
            .toLong()
    }

@MainThread
fun Context.longArrayRes(@ArrayRes arrayRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getIntArray(arrayRes)
            .map { it.toLong() }
            .toLongArray()
    }

@MainThread
fun Context.longListRes(@ArrayRes arrayRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getIntArray(arrayRes)
            .map { it.toLong() }
    }

@MainThread
fun Context.dimenRes(@DimenRes dimenRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        resources.getDimensionPixelSize(dimenRes)
    }

@MainThread
fun Context.colorRes(@ColorRes colorRes: Int) =
    lazy(LazyThreadSafetyMode.NONE) {
        getColorCompat(colorRes)
    }

fun Context.getColorCompat(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

fun Context.resolveColor(@ColorRes @AttrRes id: Int) = with(TypedValue()) {
    when {
        theme.resolveAttribute(id, this, true) -> data
        atLeastMarshmallow() -> getColor(id)
        else -> getColorCompat(id)
    }
}

private fun Context.applyDimension(unit: Int, number: Number) =
    TypedValue.applyDimension(unit, number.toFloat(), resources.displayMetrics)

fun Context.dpToPx(dp: Number) = applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp)
fun Context.pxToDp(px: Number) = applyDimension(TypedValue.COMPLEX_UNIT_PX, px)
fun Context.spToPx(sp: Int)    = applyDimension(TypedValue.COMPLEX_UNIT_PX, sp)
fun Context.pxToSp(px: Int)    = applyDimension(TypedValue.COMPLEX_UNIT_PX, px)

fun Context.getIntRes(@IntegerRes intRes: Int) =
    resources.getInteger(intRes)

fun Context.getLongRes(@IntegerRes longRes: Int) =
    getIntRes(longRes).toLong()

//endregion
//region Toast

fun Context.toastShort(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toastShort(@StringRes messageRes: Int) {
    Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()
}

fun Context.toastShort(@StringRes messageRes: Int, vararg messages: CharSequence) {
    toastShort(String.format(getString(messageRes), *messages))
}

fun Context.toastLong(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.toastLong(@StringRes messageRes: Int) {
    Toast.makeText(this, messageRes, Toast.LENGTH_LONG).show()
}

fun Context.toastLong(@StringRes messageRes: Int, vararg messages: CharSequence) {
    toastLong(String.format(getString(messageRes), *messages))
}

//endregion
//region Intent

inline fun <reified T : Any> Context.intent(noinline block: (Intent.() -> Unit)? = null): Intent {
    val intent = Intent(this, T::class.java)
    if (block != null) intent.apply(block)
    return intent
}

fun Context.intent(action: String, block: (Intent.() -> Unit)? = null): Intent =
    if (block == null) Intent(action) else Intent(action).apply(block)

fun Context.canBeResolved(intent: Intent) = intent.resolveActivity(packageManager) != null

//endregion
//region Navigation

fun Context.startActivityIfResolved(intent: Intent): Boolean {
    return if (canBeResolved(intent)) {
        startActivity(intent)
        true
    } else false
}

inline fun <reified T : Activity> Context.startActivity(noinline block: (Intent.() -> Unit)? = null) {
    startActivity(intent<T>(block))
}

fun Context.openAppDetailSettings() {
    startActivity(Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ))
}

fun Context.openAdminSettings() {
    startActivity(Intent().apply {
        component = ComponentName(
            "com.android.settings",
            "com.android.settings.DeviceAdminSettings"
        )
    })
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

fun Context.openHomeLauncher() {
    startActivity(
        Intent(Intent.ACTION_MAIN)
            .addCategory(Intent.CATEGORY_HOME)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .setPackage(
                packageManager.queryIntentActivities(
                    Intent(Intent.ACTION_MAIN)
                        .addCategory(Intent.CATEGORY_HOME)
                    , PackageManager.MATCH_DEFAULT_ONLY
                )[0].activityInfo.packageName
            )
    )
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

fun Context.openUrl(url: String, newTask: Boolean = false): Boolean =
    startActivityIfResolved(intent(Intent.ACTION_VIEW) {
        data = Uri.parse(url)
        if (newTask) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    })

fun Context.sendEmail(email: String, subject: String = "", text: String = ""): Boolean =
    startActivityIfResolved(intent(Intent.ACTION_SENDTO) {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        if (subject.isNotBlank()) putExtra(Intent.EXTRA_SUBJECT, subject)
        if (text.isNotBlank()) putExtra(Intent.EXTRA_TEXT, text)
    })

fun Context.dial(number: String): Boolean =
    startActivityIfResolved(Intent(
        Intent.ACTION_CALL, Uri.parse("tel:$number")
    ))

fun Context.sendSms(number: String, text: String = ""): Boolean =
    startActivityIfResolved(Intent(Intent.ACTION_VIEW, Uri.parse("smsto:$number"))
        .apply { putExtra("sms_body", text) })

//endregion
//region Service

inline fun <reified T : Service> Context.startService(noinline block: (Intent.() -> Unit)? = null): ComponentName? =
    startService(intent<T>(block))

inline fun <reified T : Service> Context.startForegroundServiceCompat(noinline block: (Intent.() -> Unit)? = null) {
    ContextCompat.startForegroundService(this, intent<T>(block))
}

inline fun <reified T : Service> Context.stopService() =
    stopService(Intent(this, T::class.java))

//endregion
//region Permission

inline val Context.hasWriteSettingPermission
    get() = when{
        atLeastMarshmallow() -> Settings.System.canWrite(this)
        else -> null
    }

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

fun Context.isPermissionGranted(permission: String) = belowMarshmallow() ||
        ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Context.arePermissionsGranted(permissions: Array<out String>) =
    belowMarshmallow() || permissions.all { isPermissionGranted(it) }

//endregion
//region Internet Connection

inline val Context.isConnected: Boolean?
    get() {
        if (atLeastQ()) d(
                "This function is deprecated in Q, please use NetworkCallback" +
                "\nSee: https://developer.android.com/reference/android/net/NetworkInfo.html"
        )
        @Suppress("DEPRECATION")
        return connectivityManager.activeNetworkInfo?.isConnectedOrConnecting
    }

inline val Context.isMobileDataEnabled: Boolean?
    get() = when {
        atLeastOreo() -> telephonyManager.isDataEnabled
        else -> null
    }

inline val Context.isWifiEnabled
    get() = wifiManager.isWifiEnabled

//endregion
//region Device

inline val Context.isScreenOn
    get() = powerManager.isInteractive

inline val Context.isDeviceLocked
    get() = if (atLeastLollipopMR1()) keyGuardManager.isDeviceLocked
    else keyGuardManager.isKeyguardLocked

inline val Context.displayWidth: Int
    get() = resources.displayMetrics.widthPixels
inline val Context.displayHeight: Int
    get() = resources.displayMetrics.heightPixels

//endregion
//region App

inline val Context.foregroundApp: String?
    get() {
        if (!hasAppUsagePermission) {
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

inline val Context.installedApps: List<ApplicationInfo>
    get() = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

inline val Context.installedAppsWithLaunchIntent: List<ApplicationInfo>
    get() = installedApps
        .filter { it.packageName?.isNotEmpty() ?: false }
        .filter { packageManager.getLaunchIntentForPackage(it.packageName) != null }

inline val Context.installedAppsWithLaunchActivity: List<ApplicationInfo>
    get() {
        val intent = Intent(Intent.ACTION_MAIN, null)
            .apply { addCategory(Intent.CATEGORY_LAUNCHER) }

        val packageNames = packageManager.queryIntentActivities(intent, 0)
            .map { it.activityInfo.packageName }

        return installedApps
            .filter { it.packageName?.isNotEmpty() ?: false }
            .filter { packageNames.contains(it.packageName) }
    }

inline val Context.installedLauncherApp: List<ApplicationInfo>
    get() {
        val intent = Intent(Intent.ACTION_MAIN, null)
            .apply { addCategory(Intent.CATEGORY_LAUNCHER) }

        val packageNames = packageManager.queryIntentActivities(intent, 0)
            .map { it.activityInfo.packageName }

        return installedApps
            .filter { it.packageName?.isNotEmpty() ?: false }
            .filter {
                packageManager.getLaunchIntentForPackage(it.packageName) != null ||
                        packageNames.contains(it.packageName)
            }
    }

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
//region Uri

fun Context.deleteLocalUris(vararg uris: Uri) {
    uris.forEach(::deleteLocalUri)
}

fun Context.deleteLocalUris(uris: List<Uri>) {
    uris.forEach(::deleteLocalUri)
}

@Suppress("NestedBlockDepth")
fun Context.deleteLocalUri(uri: Uri) {
    when {
        uri.isContent -> contentResolver.delete(uri, null, null)
        uri.isFile    -> uri.path?.let { with(File(it)) { if (exists()) delete() } }
        else          -> return
    }
}

//endregion
