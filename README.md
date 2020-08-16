Zpack
====================

[![Release](https://jitpack.io/v/AchmadHafid/Zpack.svg)](https://jitpack.io/#AchmadHafid/toolbar-badge-menu-item)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

**Assalamu'alaikum brothers and sisters, peace be upon you!**

In this small library, I try to compile many kotlin code snippet (mostly extension functions) regularly use in my project.
You can directly look into its source code to find some code that you may need or just add this lib into your build.


Compatibility
-------------

This library is compatible from API 21 (Android 5.0 Lollipop) & AndroidX.


Download
--------

Add jitpack repository into your root build.gradle

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
    ...
  }
}
```

Add the dependency

```groovy
dependencies {
  ...
  implementation 'com.github.AchmadHafid:Zpack:1.2.2'
  ...
}
```


Quick Usage
-----------


<details>
  <summary>Android Version</summary>

```kotlin
//Version checking

fun belowLollipopMR1()
fun belowMarshmallow()
fun belowNougat()
fun belowNougatMR1()
fun belowOreo()
fun belowOreoMR1()
fun belowPie()
fun belowQ()

fun atLeastLollipopMR1()
fun atLeastMarshmallow()
fun atLeastNougat()
fun atLeastNougatMR1()
fun atLeastOreo()
fun atLeastOreoMR1()
fun atLeastPie()
fun atLeastQ()
```

</details>
<details>
  <summary>App</summary>

```kotlin
// Inquiry apps info that available on the device

val Context.appName
val Context.appIcon
val Context.foregroundApp
val Context.installedApps
val Context.installedAppsWithLaunchIntent
val Context.installedAppsWithLaunchActivity
val Context.installedLauncherApp

fun Context.getAppName()
fun Context.getAppIcon()
```

</details>
<details>
  <summary>Clipboard</summary>

```kotlin
fun Context.copyPlainTextToClipboard()
fun Context.copyHtmlTextToClipboard()
fun Context.copyRawUriToClipboard()
```

</details>
<details>
  <summary>Collection</summary>

```kotlin
// Null checking

val Collection.areAllNull
val Collection.areAllNotNull

fun areAllNull()
fun areAllNotNull()

// Collection operation

fun MutableList.addIfNotExist()

// Transformer

fun Map<K, List<V>>.asMutable(): MutableMap<K, MutableList<V>>
fun Map<K, Set<V>>.asMutable(): MutableMap<K, MutableSet<V>>
```

</details>
<details>
  <summary>Connection</summary>

```kotlin
val Context.isConnected
val Context.isMobileDataEnabled
val Context.isWifiEnabled
```

</details>
<details>
  <summary>Date</summary>

```kotlin
val Date.isToday
val Date.isYesterday
val Date.formatCompact
val Date.formatShort
val Date.formatMedium
val Date.formatLong

fun String.toDate()
```

</details>
<details>
  <summary>Device</summary>

```kotlin
val Context.hasSoftNavigationKeys
val Context.isScreenOn
val Context.isDeviceLocked
val Context.displayWidth
val Context.displayHeight
val Context.statusBarHeight
val Context.navigationBarHeight
val FragmentActivity.actionBarHeight
```

</details>
<details>
  <summary>Intent</summary>

```kotlin
fun Context.intent()
fun Intent.canBeResolved()

// Common Screen

fun Context.startActivityIfResolved()
fun Context.openAppDetailSettings()
fun Context.openAdminSettings()
fun Context.openUsageAccessSettings()
fun Context.openWirelessSettings()
fun Context.openWriteSettings()

// Common Action

fun Context.openHomeLauncher()
fun Context.share()
fun Context.openUrl()
fun Context.sendEmail()
fun Context.dial()
fun Context.sendSms()

// Service

fun Context.stopService()
fun Context.startService()
fun Context.startForegroundServiceCompat()
fun AppCompatActivity.startForegroundServiceCompat()
```

</details>
<details>
  <summary>Keyboard</summary>

```kotlin
fun Window.adjustKeyboard()
```

</details>
<details>
  <summary>Lifecycle</summary>

```kotlin
val FragmentActivity.lifecycleState
val Fragment.lifecycleState
val Fragment.viewLifecycle
val Fragment.viewLifecycleState
val Fragment.viewLifecycleScope
```

</details>
<details>
  <summary>LiveData</summary>

```kotlin
fun MutableLiveData.setValueIfNew()
fun MutableLiveData.notifyObserver()
```

</details>
<details>
  <summary>Log</summary>

```kotlin
fun d()
fun e()
fun i()
fun v()
fun w()
```

</details>
<details>
  <summary>Metadata</summary>

```kotlin
val Context.metaData
```

</details>
<details>
  <summary>Navigation</summary>

```kotlin
val Fragment.appCompatActivity
val Fragment.isStartDestination
fun Fragment.finish() // popup fragment from navigation stack
```

</details>
<details>
  <summary>Permission</summary>

```kotlin
val IntArray.arePermissionsGranted

fun Context.arePermissionsGranted()
fun Context.isPermissionGranted()

val Context.hasWriteSettingPermission
val Context.hasAppUsagePermission

fun AppCompatActivity.requestPermissionCompat()
fun AppCompatActivity.shouldShowRequestPermissionRationales()
fun Fragment.shouldShowRequestPermissionRationales()
```

</details>
<details>
  <summary>Resource</summary>

```kotlin
fun Context.stringRes()
fun Context.stringArrayRes()
fun Context.stringListRes()
fun Context.intRes()
fun Context.intArrayRes()
fun Context.intListRes()
fun Context.dimenRes()
fun Context.colorRes()

fun Fragment.stringRes()
fun Fragment.stringArrayRes()
fun Fragment.stringListRes()
fun Fragment.intRes()
fun Fragment.intArrayRes()
fun Fragment.intListRes()
fun Fragment.dimenRes()
fun Fragment.colorRes()

fun Context.getColorCompat()
fun Context.resolveColor()

fun Context.dpToPx()
fun Context.pxToDp()
fun Context.spToPx()
fun Context.pxToSp()
```

</details>
<details>
  <summary>Snackbar</summary>

```kotlin
fun View.snackBarShort()
fun View.snackBarLong()
fun View.snackBarForever()
```

</details>
<details>
  <summary>String</summary>

```kotlin
val String.toCamelCase
val String.toTitleCase

val String?.blankIfNull
val String?.nullIfBlank
fun String?.orEmpty()
```

</details>
<details>
  <summary>System Service</summary>

```kotlin
// All System Service

val Context.accessibilityManager
...
val Context.windowManager
```

</details>
<details>
  <summary>Theme</summary>

```kotlin
fun applyTheme()
fun lightTheme()
fun darkTheme()
fun defaultTheme()
fun AppCompatActivity.toggleTheme()

val Context.isDarkThemeEnabled
```

</details>
<details>
  <summary>Toast</summary>

```kotlin
fun Context.toastShort
fun Context.toastLong
fun Fragment.toastShort
fun Fragment.toastLong
```

</details>
<details>
  <summary>Uri</summary>

```kotlin
val Uri.isContent
val Uri.isFile
val Uri.isContentOrFile
val Uri.isHttp
val Uri.isHttps
val Uri.isUrl

fun Context.deleteLocalUri()
fun Context.deleteLocalUris()
```

</details>
<details>
  <summary>View Model</summary>

```kotlin
fun ViewModelProvider.getViewModel()
fun FragmentActivity.getViewModel()
fun Fragment.getViewModel()
fun Fragment.getViewModelWithActivityScope()
fun Fragment.getViewModelWithParentScope()
```

</details>
<details>
  <summary>View</summary>
    <br/>
    <details>
  	<summary>Bottom Sheet Dialog</summary>

  ```kotlin
  fun BottomSheetDialog.setExpanded()
  ```

  </details>
  <details>
  	<summary>Constraint Layout</summary>

  ```kotlin
  fun View.clearConstraint()
  var View.constraintMarginStart
  var View.constraintMarginEnd
  ```

  </details>
  <details>
  	<summary>Edit Text</summary>

  ```kotlin
  var EditText.value
  fun EditText.setText()
  fun EditText.onInput()
  fun EditText.showPasswordInputType()
  fun EditText.hidePasswordInputType()
  fun EditText.togglePasswordVisibility()

  const val INPUT_TYPE_VISIBLE_PASSWORD
  const val INPUT_TYPE_HIDDEN_PASSWORD
  ```

  </details>
  <details>
  	<summary>Image View</summary>

  ```kotlin
  fun ImageView.setImageTintList()
  ```

  </details>
  <details>
	<summary>Text View</summary>

  ```kotlin
  fun TextView.setFontRes()
  fun TextView.setTextAppearanceRes()
  fun TextView.setTextRes()
  fun TextView.clear()
  fun TextView.underLine()
  fun TextView.deleteLine()
  fun TextView.bold()
  ```

  </details>
  <details>
  	<summary>View</summary>

  ```kotlin
  fun View.f(id) // shortcut for findViewById(id)
  fun ViewGroup.inflate()

  // Visibility

  val View.isVisible
  fun View.show()
  fun List<View>.show()
  fun View.showIf()
  fun List<View>.showIf()

  val View.isInvisible
  fun View.invisible()
  fun List<View>.invisible()
  fun View.invisibleIf()
  fun List<View>.invisibleIf

  val View.isGone
  fun View.gone()
  fun List<View>.gone()
  fun View.goneIf()
  fun List<View>.goneIf()

  fun View.visibleOrInvisible()
  fun List<View>.visibleOrInvisible()
  fun View.visibleOrGone()
  fun List<View>.visibleOrGone()

  // Availability

  val List<View>.areAllEnabled
  val List<View>.areAllDisabled
  fun List<View>.enabled()

  // Resource

  fun View.setPaddingRes()
  fun View.setBackgroundColorRes()

  // Shape

  fun View.makeRoundedCornerOnTop()

  // Listener

  fun View.onSingleClick()
  ```

  </details>
</details>

Bonus!
-----------
<details>
  <summary>Lifecycle Value</summary>
Forgot to reset a value to null at 'onDestroy' ? Use this handy delegate!

```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) { // can also be used inside Fragment or LifecycleService

    private var myObj: SomeHeavyObject? by lifecycleVar { // or viewLifecycleVar for Fragment's view lifecycle binding
    	d("MyInt is destroyed")
        /**
         * this callback will be called at 'onDestroy'
         * after this callback returned, 'myInt' will be set to null
         */
    }

    fun onCreate(savedInstanceState: Bundle?) {
    	super.onCreate(savedInstanceState)
        myObj = SomeHeavyObject() // use value as usual
    }

}
```

</details>
<details>
	<summary>Ignorable Observer</summary>
Usually we attach a livedata observer at 'OnCreate' lifecycle event. This is a standard way to make sure that we only attach an observer once. But what if you want to attach a livedata observer dynamically? maybe after some event like 'onClick'? Use this handy extension!

```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    fun thisFunctionMaybeCalledMultipleTimes() {
        /**
         * below observe function will be ignored if already called before
         * by doing this, we prevent a multiple observer with the same lifecycle owner to be attached to the same livedata
         */
        myViewModel.myLiveData.observeOrIgnore() {
            // callback
        }
    }

}
```

</details>

<br/>**Stay tuned, There will be more to come!**<br/>


License
-------

    Copyright 2019 Achmad Hafid

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
