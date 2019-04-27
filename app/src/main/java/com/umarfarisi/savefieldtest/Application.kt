package com.umarfarisi.savefieldtest

import android.app.Application
import com.umarfarisi.savefield.storage.FieldStorageSharedPreference
import com.umarfarisi.savefield.storage.FieldStorageUtils

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        // set default FieldStorage to be FieldStorageSharedPreference
        FieldStorageUtils.setDefaultFS(FieldStorageSharedPreference(this.applicationContext))
        // set default File Management for FieldStorage to be FieldStorageSharedPreference
        FieldStorageUtils.setDefaultFileManagementStorage(FieldStorageSharedPreference(this.applicationContext))
        // remove all previous saved fields
        FieldStorageUtils
            .getDefaultFileManagementStorage()
            ?.getAll(FieldStorageUtils.FILE_MANAGEMENT_STORAGE_FILE)
            ?.forEach {
                when (val storageName = it.value.toString()) {
                    FieldStorageUtils.getDefaultFS()::class.java.name -> {
                        FieldStorageUtils.getDefaultFS().clear(storageName)
                    }
                }
            }
        FieldStorageUtils.getDefaultFileManagementStorage()?.clear(FieldStorageUtils.FILE_MANAGEMENT_STORAGE_FILE)
    }

}