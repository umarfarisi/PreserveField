# PreserveField
Library for making easy and safe to preserve the state of Activity / Fragment from their lifecycle 

Introduction
-------
We want to preserve our properties (fields) in our Activity / Fragment when they are still valid, but those properties can gone in some cases such as configuration change or killed by system when memory device is low. That problem can be solved by saving those fields in `onSaveInstanceState()` function of the Activity or the Fragment. Those fields will be saved with `Bundle` object from `onSaveInstanceState()` function param, and then that bundle object will be saved by the system. However, there is a limitation of the data size that can be saved in one transaction. If it exceeds that limitation, it can throw [TransactionTooLargeException](https://developer.android.com/reference/android/os/TransactionTooLargeException). This library provides easy mechanism to solve that problem.

Gradle Setup
-------
Add in your build.gradle of your project:
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Add dependency in build.gradle of your app module:
```gradle
dependencies {
    implementation 'com.github.umarfarisi:PreserveField:$preserve_field_version'
}
```

How to use it
-------
1. Setup in `onCreate()` function of your `Application` class
    - First setup for setting default FieldStorage that will be used in the Activity / Fragment
      ```kotlin
      FieldStorageUtils.setDefaultFS(
            FieldStorageSharedPreference(
                this.applicationContext,
                FIELD_STORAGE_SHARED_PREFERENCE_STORAGE_NAME
            )
      )
      ```
    - Next setup for removing all previous data if any
      ```kotlin
      FieldStorageUtils.getDefaultFS().clear()
      ```
2. Implement in Activity / Fragment
    - Define constant with value class name
      ```kotlin
         private val fullClassPath = this::class.java.name
      ```   
    - Make a global objects for `PreserveField` in Activity or Fragment
      ```kotlin
      private val preserveField: PreserveField = PreserveField()
      ```
    - Get the saved field, and clear it from storage, do it in `onCreate()` function in Activity or `onCreateView()` function in Fragment
      ```kotlin
      override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         ...
         val getter: PreserveField.Getter = preserveField.clearDataAndGetFields(savedInstanceState != null)
         val field1: String? = getter.get(fullClassPath + "field1", null)
         val filed2: String? = getter.get(fullClassPath + "filed2", null)
         val filed3: String? = getter.get(fullClassPath + "filed3", null)

         // example applying each field to one textView
         field1?.let(textView1::setText)
         field2?.let(textView2::setText)
         field3?.let(textView3::setText)
         ...
      }
      ```
    - Save the fields, do it in `onSaveInstanceState()` function
      ```kotlin
      override fun onSaveInstanceState(outState: Bundle?) {
         super.onSaveInstanceState(outState)
         preserveField
                .putField(fullClassPath + "field1", textView1.text.toString())
                .putField(fullClassPath + "field2", textView2.text.toString())
                .putField(fullClassPath + "field3", textView3.text.toString())
                .save()
      }
      ```

Contribution
-------
Feel free to create issues and pull requests.

License
-------

    Copyright 2019 Muhammad Umar Farisi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
