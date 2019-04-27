package com.umarfarisi.preservefield.storage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import java.lang.IllegalStateException

/**
 * A [FieldStorage] implementation that use [SharedPreferences] as storage
 */
class FieldStorageSharedPreference(
    private val context: Context,
    val storageName: String
) : FieldStorage() {

    override fun get(key: String): Any? {
        return getSP().all[key]
    }

    override fun clear() {
        getSP()
            .edit()
            .clear()
            .apply()
    }

    override fun put(key: String, value: Any) {
        val editor = getSP().edit()
        putValueToEditor(editor, key, value)
        editor.apply()
    }

    override fun putAll(from: Map<String, Any>) {
        val editor = getSP().edit()
        for (entry in from.entries) {
            putValueToEditor(editor, entry.key, entry.value)
        }
        editor.apply()
    }

    override fun remove(key: String) {
        getSP()
            .edit()
            .remove(key)
            .apply()
    }

    override fun containsKey(key: String): Boolean {
        return getSP().contains(key)
    }

    override fun getAll(): Map<String, Any> {
        val all = mutableMapOf<String, Any>()
        getSP().all.toMutableMap().forEach { entry ->
            entry.value?.let { value ->
                all[entry.key] = value
            }
        }
        return all
    }

    private fun getSP(): SharedPreferences = context.getSharedPreferences(storageName, MODE_PRIVATE)

    private fun putValueToEditor(editor: SharedPreferences.Editor, key: String, value: Any) {
        when (value) {
            is Int -> editor.putInt(key, value)
            is Long -> editor.putLong(key, value)
            is Float -> editor.putFloat(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is String -> editor.putString(key, value)
            else -> throw IllegalStateException("Value type is not supported by FieldStorageSharedPreference")
        }
    }

}