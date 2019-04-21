package com.umarfarisi.savefield.storage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import java.lang.IllegalStateException

class FieldStorageSharedPreference(private val context: Context) : FieldStorage() {


    override fun get(storageName: String, key: String): Any? {
        return getSP(storageName).all[key]
    }

    override fun clear(storageName: String) {
        getSP(storageName)
            .edit()
            .clear()
            .apply()
    }

    override fun put(storageName: String, key: String, value: Any) {
        val editor = getSP(storageName).edit()
        putValueToEditor(editor, key, value)
        editor.apply()
    }

    override fun putAll(storageName: String, from: Map<String, Any>) {
        val editor = getSP(storageName).edit()
        for (entry in from.entries) {
            putValueToEditor(editor, entry.key, entry.value)
        }
        editor.apply()
    }

    override fun remove(storageName: String, key: String) {
        getSP(storageName)
            .edit()
            .remove(key)
            .apply()
    }

    override fun containsKey(storageName: String, key: String): Boolean {
        return getSP(storageName).contains(key)
    }

    override fun getAll(storageName: String): Map<String, Any> {
        val all = mutableMapOf<String, Any>()
        getSP(storageName).all.toMutableMap().forEach { entry ->
            entry.value?.let { value ->
                all[entry.key] = value
            }
        }
        return all
    }

    private fun getSP(storageName: String): SharedPreferences = context.getSharedPreferences(storageName, MODE_PRIVATE)

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