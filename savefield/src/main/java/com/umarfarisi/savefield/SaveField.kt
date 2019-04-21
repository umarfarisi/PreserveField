package com.umarfarisi.savefield

import com.umarfarisi.savefield.storage.FieldStorage
import com.umarfarisi.savefield.storage.FieldStorageUtils

class SaveField @JvmOverloads constructor(
    private val storageName: String,
    val fieldStorage: FieldStorage = FieldStorageUtils.getDefaultFS()
) {

    private val fieldHolder: MutableMap<String, Any> = mutableMapOf()

    fun putField(fieldName: String, fieldValue: Any): SaveField {
        fieldHolder[fieldName] = fieldValue
        return this
    }

    fun putFields(fields: Map<String, Any>): SaveField {
        fieldHolder.putAll(fields)
        return this
    }

    fun save() {
        fieldStorage.putAll(storageName, fieldHolder)
        fieldHolder.clear()
    }

    fun clearDataAndGetFields(isSavedValueValid: Boolean): Getter {
        val fields: Map<String, Any> = if (isSavedValueValid) fieldStorage.getAll(storageName) else emptyMap()
        clear()
        return Getter(fields)
    }

    fun clear() {
        fieldStorage.clear(storageName)
    }

    class Getter constructor(private val fields: Map<String, Any>) {
        fun <V> get(fieldName: String, defaultValue: V): V {
            return (fields[fieldName] as? V) ?: defaultValue
        }
    }

}