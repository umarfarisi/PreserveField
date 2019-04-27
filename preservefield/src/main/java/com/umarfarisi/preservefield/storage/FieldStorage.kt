package com.umarfarisi.preservefield.storage

abstract class FieldStorage {

    abstract fun get(storageName: String, key: String): Any?

    abstract fun clear(storageName: String)

    abstract fun put(storageName: String, key: String, value: Any)

    abstract fun putAll(storageName: String, from: Map<String, Any>)

    abstract fun remove(storageName: String, key: String)

    abstract fun containsKey(storageName: String, key: String): Boolean

    abstract fun getAll(storageName: String): Map<String, Any>

}