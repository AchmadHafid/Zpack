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
  implementation 'com.github.AchmadHafid:Zpack:0.2.0'
  ...
}
```


Quick Usage
-----------

<details>
  <summary>Context Extensions</summary>
  <br />
<details>
  <summary>Resource Binding</summary>
  
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
  <summary>System Services</summary>
  
```kotlin
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val systemServices = listOf(
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
  <summary>Intent & Navigation</summary>
  
```kotlin

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Build your intent this way
        val intent = intent<Component> {
            // setup the intent here
        }

        // Same with startActivity
        open<OtherActivity> {
            // setup the intent here
        }

        // This will open OtherActivity than call finish
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
  <summary> Service </summary>
  
```kotlin

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Start a service
        startService<MyService> {
            // setup your intent here
        }

        // Start a foreground service
        startForegroundServiceCompat<MyForegroundService> {
            // setup your intent here
        }

        // check whether a foreground service is running
        val isMyForegroundServiceRunning = isForegroundServiceRunning<MyForegroundService>()

        // get a running service info
        val serviceInfo = getRunningServiceInfo<MyService>
    }
}

```
  
</details>

</details>

<br />
__That's it! May this library ease your Android development task__
<br />


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
