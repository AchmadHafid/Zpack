package io.github.achmadhafid.zpack.extension

import android.accounts.AccountManager
import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.AlarmManager
import android.app.AppOpsManager
import android.app.DownloadManager
import android.app.KeyguardManager
import android.app.NotificationManager
import android.app.SearchManager
import android.app.UiModeManager
import android.app.WallpaperManager
import android.app.admin.DevicePolicyManager
import android.app.job.JobScheduler
import android.app.usage.NetworkStatsManager
import android.app.usage.StorageStatsManager
import android.app.usage.UsageStatsManager
import android.appwidget.AppWidgetManager
import android.bluetooth.BluetoothManager
import android.companion.CompanionDeviceManager
import android.content.ClipboardManager
import android.content.Context
import android.content.RestrictionsManager
import android.content.pm.CrossProfileApps
import android.content.pm.LauncherApps
import android.content.pm.ShortcutManager
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
import android.os.health.SystemHealthManager
import android.os.storage.StorageManager
import android.print.PrintManager
import android.telecom.TelecomManager
import android.telephony.CarrierConfigManager
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.CaptioningManager
import android.view.inputmethod.InputMethodManager
import android.view.textclassifier.TextClassificationManager
import android.view.textservice.TextServicesManager
import androidx.core.app.NotificationManagerCompat

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
        w("Please use `AppOpsManagerCompat` instead")
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
        w("Please use `ConnectivityManagerCompat` instead")
    }
inline val Context.consumerIrManager
    get() = getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager
inline val Context.devicePolicyManager
    get() = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
inline val Context.displayManager
    get() = (getSystemService(Context.DISPLAY_SERVICE) as DisplayManager).also {
        w("Please use `DisplayManagerCompatCompat` instead")
    }
inline val Context.downloadManager
    get() = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
inline val Context.dropBoxManager
    get() = getSystemService(Context.DROPBOX_SERVICE) as DropBoxManager
inline val Context.inputManager
    get() = getSystemService(Context.INPUT_SERVICE) as InputManager
inline val Context.inputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
inline val Context.jobScheduler
    get() = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
inline val Context.keyGuardManager
    get() = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
inline val Context.launcherApps
    get() = getSystemService(Context.LAUNCHER_APPS_SERVICE) as LauncherApps
inline val Context.layoutInflate
    get() = (getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).also {
        w("Please use `LayoutInflaterCompat` instead")
    }
inline val Context.locationManager
    get() = (getSystemService(Context.LOCATION_SERVICE) as LocationManager).also {
        w("Please use `locationManagerCompat` instead")
    }
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
        w("Please use `notificationManagerCompat` instead")
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
    get() = (getSystemService(Context.USER_SERVICE) as UserManager).also {
        w("Please use `UserManagerCompat` instead")
    }
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
    get() = getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager

//endregion
//region Marshmallow

inline val Context.carrierConfigManager
    get() = getSystemService(Context.CARRIER_CONFIG_SERVICE) as CarrierConfigManager
inline val Context.midiManager
    get() = getSystemService(Context.MIDI_SERVICE) as MidiManager
inline val Context.networkStatsManager
    get() = getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager

//endregion
//region Nougat

inline val Context.hardwarePropertiesManager
    get() = getSystemService(Context.HARDWARE_PROPERTIES_SERVICE) as HardwarePropertiesManager
inline val Context.systemHealthManager
    get() = getSystemService(Context.SYSTEM_HEALTH_SERVICE) as SystemHealthManager

//endregion
//region Nougat MR1

inline val Context.shortcutManager
    get() = (getSystemService(Context.SHORTCUT_SERVICE) as ShortcutManager).also {
        d("Please use `ShortcutManagerCompat instead`")
    }

//endregion
//region Oreo

inline val Context.companionDeviceManager
    get() = getSystemService(Context.COMPANION_DEVICE_SERVICE) as CompanionDeviceManager
inline val Context.storageStatsManager
    get() = getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
inline val Context.textClassificationManager
    get() = getSystemService(Context.TEXT_CLASSIFICATION_SERVICE) as TextClassificationManager
inline val Context.wifiAwareManager
    get() = getSystemService(Context.WIFI_AWARE_SERVICE) as WifiAwareManager

//endregion
//region Pie

inline val Context.crossProfileApps
    @SuppressLint("ServiceCast")
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

//endregion
