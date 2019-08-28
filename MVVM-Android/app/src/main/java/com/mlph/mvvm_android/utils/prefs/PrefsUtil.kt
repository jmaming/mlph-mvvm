package com.mlph.mvvm_android.utils.prefs

import io.reactivex.Completable
import io.reactivex.Single

interface PrefsUtil {

    /**
     * Clear all currently stored data on {@link SharedPreferences}
     */
    fun clearAll() : Completable

    /**
     * Retrieve a stored String on {@link SharedPreferences} by key.
     *
     * @param key unique identifier to retrieve data from prefs.
     */
    fun getString(key: String) : Single<String>

    /**
     * Retrieve a stored boolean on {@link SharedPreferences} by key.
     *
     * @param key unique identifier to retrieve data from prefs.
     */
    fun getBoolean(key: String) : Single<Boolean>

    /**
     * Retrieve a stored boolean on {@link SharedPreferences} by key.
     *
     * @param key unique identifier to retrieve data from prefs.
     */
    fun getBooleanDefaultTrue(key: String) : Single<Boolean>

    /**
     * Store a given string to {@link SharedPreferences} using the given key.
     *
     * @param key   unique identifier to store the string from prefs, and can be used later for
     *              retrieving.
     * @param value desired string to be saved on prefs.
     */
    fun putString(key: String, value: String) : Completable

    /**
     * Store a given boolean to {@link SharedPreferences} using the given key.
     *
     * @param key   unique identifier to store the string from prefs, and can be used later for
     *              retrieving.
     * @param value desired string to be saved on prefs.
     */
    fun putBoolean(key: String, value: Boolean) : Completable

    /**
     * Convert any given object to string and save it to prefs.
     *
     * @param key    unique identifier to store the string from prefs, and can be used later for
     *               retrieving.
     * @param object {@link Object} to be converted to string.
     */
    fun convertObjectToJsonStringAndSave(key: String, objectParam: Any) : Completable

    /**
     * Retrieve data on stored prefs by key and convert it back to object.
     *
     * @param key unique identifier to store the string from prefs, and can be used later for
     *            retrieving.
     * @param <T> the type of class to be returned after the conversion.
     *
     * @return a {@link Single} that emits the converted object coming from the JSON string.
     */
    fun <T> convertJsonStringToObject(key: String, clazz: Class<T>): Single<T>

}