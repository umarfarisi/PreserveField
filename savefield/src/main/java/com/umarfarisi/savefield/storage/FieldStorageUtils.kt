package com.umarfarisi.savefield.storage

import android.content.Context

class FieldStorageUtils {

    var defaultFieldStorage: FieldStorage? = null

    companion object {

        private val instances: FieldStorageUtils = FieldStorageUtils()

        fun getDefaultFS(context: Context): FieldStorage {
            return instances.defaultFieldStorage ?: let {
                val fs = FieldStorageSharedPreference(context)
                instances.defaultFieldStorage = fs
                fs
            }
        }

        fun setDefaultFS(fieldStorage: FieldStorage) {
            instances.defaultFieldStorage = fieldStorage
        }

    }
}