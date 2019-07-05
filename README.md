Zpack
====================

[![Release](https://jitpack.io/v/AchmadHafid/Zpack.svg)](https://jitpack.io/#AchmadHafid/toolbar-badge-menu-item)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

**Assalamu'alaikum brothers and sisters, peace be upon you!**

In this small library, I try to compile many kotlin snippet code (mostly extension funtions) regularly use in my project.
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
  implementation 'com.github.AchmadHafid:Zpack:0.3.0'
  ...
}
```


Quick Usage
-----------

<details>
  <summary>Top Level (Global) Extensions</summary>
  <br />
  
<details>
  <summary>Logging</summary>
  
```kotlin
class MyClass {

    fun logAll(message: String) {
        d(message) // debug log
        e(message) // error log
        i(message) // info log
        w(message) // warning log
        v(message) // verbose log
    }
    
}
```
  
</details>
<details>
  <summary>SDK Version</summary>
  
```kotlin
class MyClass {

    fun checkVersion() {
        if (belowLollipopMR1()) TODO()
        if (belowMarshmallow()) TODO()
        if (belowNougat()) TODO()
        if (belowNougatMR1()) TODO()
        if (belowOreo()) TODO()
        if (belowOreoMR1()) TODO()
        if (belowPie()) TODO()

        if (atLeastLollipopMR1()) TODO()
        if (atLeastMarshmallow()) TODO()
        if (atLeastNougat()) TODO()
        if (atLeastNougatMR1()) TODO()
        if (atLeastOreo()) TODO()
        if (atLeastOreoMR1()) TODO()
        if (atLeastPie()) TODO()
    }
    
}
```
  
</details>
<details>
  <summary>Theme</summary>

```kotlin
class MyClass {

    // This will restart current foreground activity
    fun applyApplicationLevelTheme() {
        // use AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM on Pie and above
        // else use AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
        defaultTheme()
        
        lightTheme()
        darkTheme()
    }
    
}
```

</details>
  
</details>
<details>
  <summary>Context (e.g. AppCompatActivity & Service) Extensions</summary>
  <br />

<details>
  <summary>System Services</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val allSystemServices = listOf(
            accessibilityManager, accountManager, activityManager, appOpsManager,
            usageStatsManager, notificationManager, powerManager, keyGuardManager,
            telephonyManager, layoutInflater, connectivityManager, wifiManager
            // and many more.....
        )
    }
}
```

</details>
<details>
  <summary>Lazy Resource Binding</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val myString         by stringRes(R.string.my_string)
    private val myNullableString by stringResNullable(R.string.my_string)
    private val myStringArray    by stringArrayRes(R.array.my_string_array)
    private val myStringList     by stringListRes(R.array.my_string_array)
    private val myInt            by intRes(R.integer.my_int)
    private val myIntArray       by intArrayRes(R.array.my_int_array)
    private val myIntList        by intListRes(R.array.my_int_array)
    private val myLong           by longRes(R.integer.my_long)
    private val myLongArray      by longArrayRes(R.array.my_long_array)
    private val myLongList       by longListRes(R.array.my_long_array)
    private val myDimen          by dimenRes(R.dimen.my_dimen)
    private val myColor          by colorRes(R.color.my_color)

}
```

</details>
<details>
  <summary>Toast</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Simpler way to show a toast
         */
        toastShort("Message")
        toastShort(R.string.message)
        toastShort(R.string.format, message1, message2, ..., messageN)  // use String.format()
        toastLong("Message")
        toastLong(R.string.message)
        toastLong(R.string.format, message1, message2, ..., messageN)  // use String.format()
    }
}
```

</details>
<details>
  <summary>Intent</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Build your intent this way
        val intent = intent<Component>()
        
        // or if you want to do some setup on the intent
        val intent = intent<Component> {
            // setup the intent here
        }
        
        // Build your intent for action this way
        val intent = intent(action)
        
        // or if you want to do some setup on the intent
        val intent = intent(action) {
            // setup the intent here
        }
    }
}
```

</details>
<details>
  <summary>Navigation</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Start activity if the intent can be resolved
        val isSuccess = startActivtiyIfResolved(intent)
        
        // Same with default startActivity
        startActivtiy<OtherActivity>()
        // or
        startActivtiy<OtherActivity> {
            // setup the intent here
        }

        // This will open OtherActivity than call finish
        goto<OtherActivity>()
        // or
        goto<OtherActivity> {
            // setup the intent here
        }

        // Navigate to setting screens
        openAdminSettings()
        openAppDetailSettings()
        openUsageAccessSettings()
        openWirelessSettings()
        openWriteSettings()

        // Go to home
        openHomeLauncher()

        // Do something specific
        openUrl("https://github.com/")
        share("Something I want to share with you")
        sendEmail("recepient@google.com", "This is subject", "This is content")
        dial("+6281234567890")
        sendSms("+6281234567890", "This is content")
    }
}
```

</details>
<details>
  <summary>Service</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Same with default service
        startService<MyService>()
        // or
        startService<MyService> {
            // setup your intent here
        }

        // Start a foreground service
        startForegroundServiceCompat<MyForegroundService>()
        // or
        startForegroundServiceCompat<MyForegroundService> {
            // setup your intent here
        }
    }
}
```
  
</details>
<details>
  <summary>Permission Checker</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check permission already granted, more to come
        val permissions = listOf(
            hasWriteSettingPermission,
            hasAppUsagePermission
        )

        // fallback function that can be used to check permissions
        val canReadContacts = isGranted(Manifest.permission.READ_CONTACTS)
    }
}
```

</details>
<details>
  <summary>Internet Connection States</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check some connection states
        val connections = listOf(
            isConnected,
            isMobileDataEnabled,
            isWifiEnabled
        )
    }
}
```

</details>
<details>
  <summary>Device States & Properties</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Device states
        val someDeviceProperties = listOf(
            isScreenOn,
            isDeviceLocked
        )
        
        // Device properties
        val someDeviceProperties = listOf(
            displayWidth,
            displayHeight
        )
    }
}
```

</details>
<details>
  <summary>Application Info</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // current foreground app (usually used in foreground service)
        val packageName = foregroundApp
        
        // list of installed apps in this device
        val packageNameList = installedApps
        
        // list of installed apps in this device that can be launched from home only
        val packageNameList = installedAppsWithLaunchIntent
        
        // application name
        val appName = getAppName(packageName)
        
        // launcher icon
        val iconDrawable = getAppIcon(packageName)
    }
}
```

</details>

</details>
<details>
  <summary>AppCompatActivity Extensions </summary>
  <br />
  
<details>
  <summary>Lazy Resource Binding</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    // bind a view
    private val toolbar: Toolbar by bindView(R.id.toolbar)

}
```

</details>
<details>
  <summary>Navigation</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This will open OtherActivity than call finish
        goto<OtherActivity>()
        // or
        goto<OtherActivity> {
            // setup the intent here
        }
        
        // Finish this activity only if user double click the back button
        val onBackPressedCallback = finishActivityOnDoubleBackPressed(
            message    = "My Exit Message" // or R.string.some_messsage // first backpress message
            handler    = handler,          // Android handler to do postDelayed 
            delayMilis = 1000L             // time to wait for the second back press
        )
    }
}
```

</details>
<details>
  <summary>ViewModel</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    // bind a view model lazily
    private val viewModel: MainActivtiyViewModel by bindViewModel()

}
```

</details>
<details>
  <summary>Theme</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // check whether dark theme currently applied
        val x = isDarkThemeEnabled
        
        /**
         * if currently not dark (default or light), will switch to dark theme
         * if currently dark, switch to light theme
         * Notes:
         * - This is application level theme setting
         * - will restart current activity
         */
        val currentTheme = toggleTheme()
    }
}
```

</details>
<details>
  <summary>View</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // shortcut function, because many times we do nothing with toolbar
        setToolbar(R.id.toolbar)
        // or if you use MaterialToolbar from MaterialComponents
        setMaterialToolbar(R.id.toolbar)
    }
}
```

</details>

</details>
<details>
  <summary>Fragment Extensions </summary>
  <br />

<details>
  <summary>Toast</summary>
  
```kotlin
class MainFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /**
         * Simpler way to show a toast
         */
        toastShort("Message")
        toastShort(R.string.message)
        toastShort(R.string.format, message1, message2, ..., messageN)  // use String.format()
        toastLong("Message")
        toastLong(R.string.message)
        toastLong(R.string.format, message1, message2, ..., messageN)  // use String.format()
    }
}
```

</details>
<details>
  <summary>Permission Checker</summary>
  
```kotlin
class MainFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Check permission already granted, more to come
        val permissions = listOf(
            hasWriteSettingPermission,
            hasAppUsagePermission
        )

        // fallback function that can be used to check permissions
        val canReadContacts = isGranted(Manifest.permission.READ_CONTACTS)
    }
}
```

</details>
<details>
  <summary>Intent</summary>
  
```kotlin
class MainFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Build your intent this way
        val intent = intent<Component>()
        
        // or if you want to do some setup on the intent
        val intent = intent<Component> {
            // setup the intent here
        }
        
        // Build your intent for action this way
        val intent = intent(action)
        
        // or if you want to do some setup on the intent
        val intent = intent(action) {
            // setup the intent here
        }
    }
}
```

</details>
<details>
  <summary>Navigation</summary>
  
```kotlin
class MainFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Same with startActivity on Activity
        startActivtiy<OtherActivity>()
        // or
        startActivtiy<OtherActivity> {
            // setup the intent here
        }

        // This will open OtherActivity than call finish on holder activity (if any)
        goto<OtherActivity>()
        // or
        goto<OtherActivity> {
            // setup the intent here
        }

        // JetPack navigation
        // Check whether this fragment is a start destination
        val x = isStartDestination
        
        // JetPack navigation
        // Finish holder activity only if user double click the back button
        // Can only be used if this fragment is defined as a start destination
        val onBackPressedCallback = finishActivityOnDoubleBackPressed(
            message    = "My Exit Message" // or R.string.some_messsage // first backpress message
            handler    = handler,          // Android handler to do postDelayed 
            delayMilis = 1000L             // time to wait for the second back press
        )
        
        // Finish holder activity when user press the back button
        // Useful when using JetPack navigation, when we want to override its back press default behavior
        val onBackPressedCallback = finishActivityOnBackPressed()
    }
}
```

</details>
<details>
  <summary>ViewModel</summary>
  
```kotlin
class MainFragment : Fragment() {

    // bind a view model lazily
    private val viewModel: MainFragmentViewModel by bindViewModel()

}
```

</details>
<details>
  <summary>Theme</summary>
  
```kotlin
class MainFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // check whether dark theme currently applied
        val x = isDarkThemeEnabled
        
        /**
         * if currently not dark (default or light), will switch to dark theme
         * if currently dark, switch to light theme
         * Notes:
         * - This is application level theme setting
         * - will restart current activity
         */
        val currentTheme = toggleTheme()
    }
}
```

</details>

</details>
<details>
  <summary>View Extensions</summary>
  <br />
  
<details>
  <summary>Visibility</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val myView: ViewType by bindView(R.id.my_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        
        val visibilityStates = listOf(
            myView.isVisible,
            myView.isInvisible,
            myView.isGone
        )
        
        // change visibility state
        myView.show() // set visibility = View.VISIBLE
        myView.hide() // set visibility = View.INVISIBLE
        myView.gone() // set visibility = View.GONE
        
        // same as above but takes function as arguments
        myView.showif { true }
        myView.hideIf { false }
        myView.goneIf { true }
        
        // self explanatory
        myView.visibleOrInvisible { true }
        myView.visibleOrGone { false }
    }
}
```
  
</details>
<details>
  <summary>Listener</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val myView: ViewType by bindView(R.id.my_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // guarentee that this view can only be clicked once before fire a callback
        myView.onSingleClick { TODO() }
        
        // If you wish to re-enable click listener manually, specify a flag for it
        myView.onSingleClick(autoReEnable = false) { TODO() }
        // Then you MUST re-enable click listener manually later by using:
        // myView.isClickable = true
    }
}
```

</details>
<details>
  <summary>Snack Bar</summary>

```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val myView: ViewType by bindView(R.id.my_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // basic snackbar
        myView.snackBarShort("message")
        
        // fully customized snack bar with action
        myView.snackBarShort(
            message = "My message", // or: messageRes = R.string.my_message
            anchorView = anAnchorView, // default is null
            actionText = "Undo", // or: actionTextRes = R.string.action_text
            actionTextColorRes = R.color.colorAccent // can also be attribute value, e.g. R.attr.colorOnPrimary
        ) { TODO("Do something when action button is clicked") }
        
        // also available for other lenght types
        myView.snackBarLong()     // Arguments are the same with above
        myView.snackBarForever()  // Arguments are the same with above
    }
}
```

</details>
<details>
  <summary>TextView</summary>

```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val tv: TextView by bindView(R.id.tv)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set TextView's text from string resource
        tv.textRes = R.string.text
        
        // self explanatory
        tv.underline()
        tv.deleteLine()
        tv.bold()
    }
}
```

</details>
<details>
  <summary>EditText</summary>

```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val edt: EditText by bindView(R.id.edt)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // no more edt.text.toString()
        val text = edt.value
    }
}
```

</details>
<details>
  <summary>AppBarLayout</summary>

```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val appBarLayout: AppBarLayout by bindView(R.id.appBarLayout)
    private val scrollView: NestedScrollView by bindView(R.id.scrollView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set appBarLayout selected property to true when scrollView scrolled down
        // and to false when it can not scroll up anymore
        // Commonly used to set/unset elevation on AppBarLayout based on list position inside scrollView
        appBarLayout.setSelectedOnScrollDown(scrollView)
    }
}
```

</details>
<details>
  <summary>Util</summary>
  
  **WIP, please see source code**
  
</details>

</details>
<details>
  <summary>LiveData Extensions</summary>
  <br />
  
```kotlin
class MainFragmentViewModel : ViewModel() {

    private val _myLiveData: MutableLiveData<List<String>> = MutableLiveData()
    private val myLiveData: LiveData<List<String>> = _myLiveData

    private fun notifyOberver() {
        // In quite a rare case, you may to call the observer callback manually without change live data value
        _myLiveData.notifyObserver()
    }
}
```
  
</details>
<details>
  <summary>String Extensions</summary>
  <br />
  
<details>
  <summary>Date</summary>

```kotlin
// get Date instance from a string
// default format is "yyyy/MM/dd hh:mm"
fun getSomeImportantDate() = "2000/01/01 00:00".toDate()

// You can specify a custom format
fun getSomeImportantDate(someDateFormat: String) = "2000/01/01 00:00".toDate(someDateFormat)
```

</details>
<details>
  <summary>Case</summary>

```kotlin
val thisIsTheResult = "ThIs iS tHe ResULt".toCamelCase
val ThisIsTheResult = "ThIs iS tHe ResULt".toTitleCase 
```

</details>
  
</details>

**Stay tuned, There will be more to come!<br/>Anyway may this library ease your Android development task**


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
