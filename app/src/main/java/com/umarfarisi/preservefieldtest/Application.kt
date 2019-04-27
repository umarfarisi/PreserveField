package com.umarfarisi.preservefieldtest

import android.app.Application
import com.umarfarisi.preservefield.storage.FieldStorageSharedPreference
import com.umarfarisi.preservefield.storage.FieldStorageUtils

class Application : Application() {

    companion object {
        const val FIELD_STORAGE_SHARED_PREFERENCE_STORAGE_NAME = "FIELD_STORAGE_SHARED_PREFERENCE_STORAGE_NAME"
    }

    override fun onCreate() {
        super.onCreate()
        // set default FieldStorage to be FieldStorageSharedPreference
        FieldStorageUtils.setDefaultFS(
            FieldStorageSharedPreference(
                this.applicationContext,
                FIELD_STORAGE_SHARED_PREFERENCE_STORAGE_NAME
            )
        )
    }

}