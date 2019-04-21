package com.umarfarisi.savefieldtest

import android.app.Application
import com.umarfarisi.savefield.storage.FieldStorageSharedPreference
import com.umarfarisi.savefield.storage.FieldStorageUtils

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        // set default FieldStorage to be FieldStorageSharedPreference
        FieldStorageUtils.setDefaultFS(FieldStorageSharedPreference(this.applicationContext))
    }

}