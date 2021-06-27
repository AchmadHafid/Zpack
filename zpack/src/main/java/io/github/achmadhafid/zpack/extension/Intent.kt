package io.github.achmadhafid.zpack.extension

import android.Manifest
import android.annotation.TargetApi
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlin.reflect.KClass

inline fun <reified T : Any> Context.intent(noinline block: Intent.() -> Unit = {}): Intent =
    Intent(this, T::class.java).apply(block)

fun Intent.canBeResolved(context: Context) = resolveActivity(context.packageManager) != null

fun Context.startActivityIfResolved(intent: Intent, onActivityCanNotBeResolved: () -> Unit = {}) {
    if (intent.canBeResolved(this)) startActivity(intent)
    else onActivityCanNotBeResolved()
}

//region Common Screen

fun Context.openAppDetailSettings(onFailure: () -> Unit = {}) {
    startActivityIfResolved(
        Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        ),
        onFailure
    )
}

fun Context.openAdminSettings(onFailure: () -> Unit = {}) {
    startActivityIfResolved(Intent().apply {
        component = ComponentName(
            "com.android.settings",
            "com.android.settings.DeviceAdminSettings"
        )
    }, onFailure)
}

fun Context.openUsageAccessSettings(onFailure: () -> Unit = {}) {
    startActivityIfResolved(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS), onFailure)
}

fun Context.openWirelessSettings(onFailure: () -> Unit = {}) {
    startActivityIfResolved(
        Intent(Settings.ACTION_WIRELESS_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
        onFailure
    )
}

fun Context.openWriteSettings(onFailure: () -> Unit = {}) {
    if (atLeastMarshmallow()) {
        startActivityIfResolved(
            Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                .apply { data = Uri.parse("package:$`package`") }, onFailure
        )
    } else onFailure()
}

//endregion
//region Common Action

fun Context.openHomeLauncher(onFailure: () -> Unit = {}) {
    startActivityIfResolved(
        Intent(Intent.ACTION_MAIN)
            .addCategory(Intent.CATEGORY_HOME)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .setPackage(
                packageManager.queryIntentActivities(
                    Intent(Intent.ACTION_MAIN)
                        .addCategory(Intent.CATEGORY_HOME), PackageManager.MATCH_DEFAULT_ONLY
                )[0].activityInfo.packageName
            ),
        onFailure
    )
}

fun Context.share(text: String, subject: String? = null, onFailure: () -> Unit = {}) {
    val intent = Intent().apply {
        type = "text/plain"
        subject?.let { putExtra(Intent.EXTRA_SUBJECT, it) }
        putExtra(Intent.EXTRA_TEXT, text)
    }
    startActivityIfResolved(Intent.createChooser(intent, null), onFailure)
}

fun Context.openUrl(url: String, newTask: Boolean = false, onFailure: () -> Unit = {}) {
    startActivityIfResolved(Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        if (newTask) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }, onFailure)
}

fun Context.sendEmail(
    email: String,
    subject: String = "",
    text: String = "",
    onFailure: () -> Unit = {}
) {
    startActivityIfResolved(Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:")).apply {
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        if (subject.isNotBlank()) putExtra(Intent.EXTRA_SUBJECT, subject)
        if (text.isNotBlank()) putExtra(Intent.EXTRA_TEXT, text)
    }, onFailure)
}

fun Context.dial(number: String, onFailure: () -> Unit = {}) {
    startActivityIfResolved(
        Intent(Intent.ACTION_CALL, Uri.parse("tel:$number")),
        onFailure
    )
}

fun Context.sendSms(number: String, text: String = "", onFailure: () -> Unit = {}) {
    startActivityIfResolved(
        Intent(Intent.ACTION_VIEW, Uri.parse("smsto:$number"))
            .apply { putExtra("sms_body", text) },
        onFailure
    )
}

fun Context.openGallery(onFailure: () -> Unit = {}) {
    startActivityIfResolved(
        Intent(Intent.ACTION_VIEW).apply {
            type = "image/*"
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        },
        onFailure
    )
}

//endregion
//region Service

inline fun <reified T : Service> Context.stopService() =
    stopService(Intent(this, T::class.java))

inline fun <reified T : Service> Context.startService(noinline block: Intent.() -> Unit = {}): ComponentName? =
    startService(intent<T>(block))

inline fun <reified T : Service> Context.startForegroundServiceCompat(noinline block: Intent.() -> Unit = {}) {
    ContextCompat.startForegroundService(this, intent<T>(block))
}

inline fun <reified T : Service> AppCompatActivity.startForegroundServiceCompat(
    requestCode: Int,
    noinline block: Intent.() -> Unit = {}
) {
    if (atLeastPie()) {
        requestPermissionCompat(arrayOf(Manifest.permission.FOREGROUND_SERVICE), requestCode)
    }
    ActivityCompat.startForegroundService(this, intent<T>(block))
}

fun <S : Service> AppCompatActivity.startForegroundServiceCompat(
    clazz: KClass<S>,
    requestCode: Int,
    block: Intent.() -> Unit = {}
) {
    @TargetApi(Build.VERSION_CODES.P)
    if (atLeastPie()) {
        requestPermissionCompat(arrayOf(Manifest.permission.FOREGROUND_SERVICE), requestCode)
    }
    ActivityCompat.startForegroundService(this, Intent(this, clazz.java).apply(block))
}

//endregion
