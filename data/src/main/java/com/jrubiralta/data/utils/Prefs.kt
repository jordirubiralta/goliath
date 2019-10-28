package com.jrubiralta.data.utils

import android.content.Context
import android.content.SharedPreferences
import com.jrubiralta.data.utils.security.SecurityUtil
import com.scottyab.aescrypt.AESCrypt


object Prefs {

    private const val DEFAULT_NAME = "GOLIATH_PREFS"
    private const val KEY_PREFIX_STRING = "KEY_PREFIX_STRING"
    private const val KEY_PREFIX_BOOLEAN = "KEY_PREFIX_BOOLEAN"
    private const val KEY_PREFIX_INT = "KEY_PREFIX_INT"
    private const val KEY_PREFIX_FLOAT = "KEY_PREFIX_FLOAT"
    private const val KEY_PREFIX_LONG = "KEY_PREFIX_LONG"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context, name: String = DEFAULT_NAME) {
        prefs = context.applicationContext.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    fun getString(context: Context, key: String, defaultValue: String?): String {
        return AESCrypt.decrypt(SecurityUtil.getEncrKey(context), prefs.getString(KEY_PREFIX_STRING + key, defaultValue) ?: "")
    }

    fun setString(context: Context, key: String, value: String) {
        prefs.edit().putString(KEY_PREFIX_STRING + key, AESCrypt.encrypt(SecurityUtil.getEncrKey(context), value)).apply()
    }

    fun getBoolean(context: Context, key: String, defaultValue: Boolean): Boolean {
        try {
            getString(context, KEY_PREFIX_BOOLEAN + key, defaultValue.toString()).toBoolean()
        } catch (e: Exception) {
        }
        return false
    }

    fun setBoolean(context: Context, key: String, value: Boolean) {
        prefs.edit().putString(KEY_PREFIX_BOOLEAN + key, AESCrypt.encrypt(SecurityUtil.getEncrKey(context), "" + value)).apply()
    }

    fun getInt(context: Context, key: String, defaultValue: Int): Int {
        try {
            getString(context, KEY_PREFIX_INT + key, defaultValue.toString()).toInt()
        } catch (e: Exception) {
        }
        return defaultValue
    }

    fun setInt(context: Context, key: String, value: Int) {
        prefs.edit().putString(KEY_PREFIX_INT + key, AESCrypt.encrypt(SecurityUtil.getEncrKey(context), "" + value)).apply()
    }

    fun getFloat(context: Context, key: String, defaultValue: Float): Float {
        try {
            getString(context, KEY_PREFIX_FLOAT + key, defaultValue.toString()).toFloat()
        } catch (e: Exception) {
        }
        return defaultValue
    }

    fun setFloat(context: Context, key: String, value: Float) {
        prefs.edit().putString(KEY_PREFIX_FLOAT + key, AESCrypt.encrypt(SecurityUtil.getEncrKey(context), "" + value)).apply()
    }

    fun getLong(context: Context, key: String, defaultValue: Long): Long {
        try {
            getString(context, KEY_PREFIX_LONG + key, defaultValue.toString()).toLong()
        } catch (e: Exception) {
        }
        return defaultValue
    }

    fun setLong(context: Context, key: String, value: Long) {
        prefs.edit().putString(KEY_PREFIX_LONG + key, AESCrypt.encrypt(SecurityUtil.getEncrKey(context), "" + value)).apply()
    }

    fun clear() = prefs.edit().clear().apply()

}