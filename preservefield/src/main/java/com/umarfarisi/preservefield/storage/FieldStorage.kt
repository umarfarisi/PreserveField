package com.umarfarisi.preservefield.storage

abstract class FieldStorage {

    abstract fun get(key: String): Any?

    abstract fun clear()

    abstract fun put(key: String, value: Any)

    abstract fun putAll(from: Map<String, Any>)

    abstract fun remove(key: String)

    abstract fun containsKey(key: String): Boolean

    abstract fun getAll(): Map<String, Any>

}