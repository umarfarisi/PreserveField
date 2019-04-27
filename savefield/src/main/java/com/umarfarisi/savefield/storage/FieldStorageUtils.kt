package com.umarfarisi.savefield.storage

class FieldStorageUtils {

    var defaultFieldStorage: FieldStorage? = null
    var defaultFileManagementStorage: FieldStorage? = null

    companion object {

        const val FILE_MANAGEMENT_STORAGE_FILE: String = "FILE_MANAGEMENT_STORAGE_FILE"

        private val instances: FieldStorageUtils = FieldStorageUtils()

        fun getDefaultFS(): FieldStorage {
            return instances.defaultFieldStorage
                ?: throw IllegalStateException("Need to set defaultFS first by calling FieldStorageUtils.setDefaultFS")
        }

        fun setDefaultFS(fieldStorage: FieldStorage) {
            instances.defaultFieldStorage = fieldStorage
        }

        fun setDefaultFileManagementStorage(fieldStorage: FieldStorage) {
            instances.defaultFileManagementStorage = fieldStorage
        }

        fun getDefaultFileManagementStorage(): FieldStorage? {
            return instances.defaultFieldStorage
        }

    }
}