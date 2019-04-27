package com.umarfarisi.preservefield.storage

class FieldStorageUtils {

    var defaultFieldStorage: FieldStorage? = null

    companion object {

        private val instances: FieldStorageUtils = FieldStorageUtils()

        fun getDefaultFS(): FieldStorage {
            return instances.defaultFieldStorage
                ?: throw IllegalStateException("Need to set defaultFS first by calling FieldStorageUtils.setDefaultFS")
        }

        fun setDefaultFS(fieldStorage: FieldStorage) {
            instances.defaultFieldStorage = fieldStorage
        }

    }
}