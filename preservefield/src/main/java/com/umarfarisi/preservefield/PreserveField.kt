package com.umarfarisi.preservefield

import com.umarfarisi.preservefield.storage.FieldStorage
import com.umarfarisi.preservefield.storage.FieldStorageUtils

/**
 * Class for helping to save fields from Activity or Fragment
 * @param storageName name of storage that identified the storage
 * @param fieldStorage the storage for saving fields
 */
class PreserveField @JvmOverloads constructor(
    private val storageName: String,
    val fieldStorage: FieldStorage = FieldStorageUtils.getDefaultFS()
) {

    private val fieldHolder: MutableMap<String, Any> = mutableMapOf()
    private val fileManagementStorage = FieldStorageUtils.getDefaultFileManagementStorage()

    init {
        fileManagementStorage?.put(
            FieldStorageUtils.FILE_MANAGEMENT_STORAGE_FILE,
            storageName,
            fieldStorage::class.java.name
        )
    }

    /**
     * Put a field which want to be saved. This function won't save your field, this function just prepare field
     * that you want to save. If you already put that field by calling this function,
     * you must call [save] function for saving that field.
     * @param fieldName name of field
     * @param fieldValue value of that field, [fieldName]
     */
    fun putField(fieldName: String, fieldValue: Any): PreserveField {
        fieldHolder[fieldName] = fieldValue
        return this
    }

    /**
     * Put a lot of field which want to be saved. This function won't save your fields, this function just prepare fields
     * that you want to save. If you already put those fields by calling this function,
     * you must call [save] function for saving those fields.
     * @param fields a key-value pair, key is a field's name and value is a field's value
     */
    fun putFields(fields: Map<String, Any>): PreserveField {
        fieldHolder.putAll(fields)
        return this
    }

    /**
     * Function to save all fields that have been put by using [putField] or [putFields] function.
     * You have to call this function after you put your class field by those function ([putField] or [putFields] )
     */
    fun save() {
        fieldStorage.putAll(storageName, fieldHolder)
        fieldHolder.clear()
    }

    /**
     * Every time you want to get your saved fields, that means you do not need those fields to be saved anymore.
     * Therefore, every time you get those fields, you must clear those field. Another benefit is your app memory won't
     * get big. You can achieve it by calling this [clearDataAndGetFields] function
     * @param isSavedValueValid true means you will get your saved fields from storage, otherwise you'll get empty result.
     * we need this flag to make sure you really want your saved field, in case your saved field is not valid anymore so
     * you can make this function to be false. A case example is calling this function in Activity.onCreate() and then
     * checking if the bundle from that function is null or not. If null it means your saved field is not valid anymore.
     */
    fun clearDataAndGetFields(isSavedValueValid: Boolean): Getter {
        val fields: Map<String, Any> = if (isSavedValueValid) fieldStorage.getAll(storageName) else emptyMap()
        clear()
        return Getter(fields)
    }

    /**
     * Clear all saved field
     */
    fun clear() {
        fieldStorage.clear(storageName)
    }

    /**
     * Helper class to support [PreserveField] in retrieving the saved fields.
     */
    class Getter constructor(private val fields: Map<String, Any>) {
        fun <V> get(fieldName: String, defaultValue: V): V {
            return (fields[fieldName] as? V) ?: defaultValue
        }
    }

}