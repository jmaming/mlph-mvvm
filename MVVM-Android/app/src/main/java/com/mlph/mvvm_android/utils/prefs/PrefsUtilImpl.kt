package com.mlph.mvvm_android.utils.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class PrefsUtilImpl @Inject constructor(context: Context) : PrefsUtil {

    companion object {
        @VisibleForTesting
        const val NO_DATA_FOUND = "No stored data found with key "

        const val IM_PREFS = "IM_PREFS"

        val DEFAULT_STRING_VALUE: String? = null
    }

    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(IM_PREFS, Context.MODE_PRIVATE)
    }

    override fun clearAll(): Completable {
        return Completable.defer {
            sharedPreferences.edit().clear().apply()
            Completable.complete()
        }
    }

    override fun getString(key: String): Single<String> {
        return Single.defer {
            val value = sharedPreferences.getString(key, DEFAULT_STRING_VALUE)
            if (null == value) {
                val errorMessage = NO_DATA_FOUND + key
                Timber.w(errorMessage)
                Single.error<String>(Throwable(errorMessage))
            }
            Single.just(value)
        }
    }

    override fun putString(key: String, value: String): Completable {
        return Completable.fromCallable {
            sharedPreferences.edit().putString(key, value).apply()
            Completable.complete()
        }
    }

    override fun getBoolean(key: String): Single<Boolean> {
        return Single.fromCallable {
            sharedPreferences.getBoolean(key, false)
        }
    }

    override fun getBooleanDefaultTrue(key: String): Single<Boolean> {
        return Single.fromCallable {
            sharedPreferences.getBoolean(key, true)
        }
    }

    override fun putBoolean(key: String, value: Boolean): Completable {
        return Completable.fromCallable {
            sharedPreferences.edit().putBoolean(key, value).apply()
            Completable.complete()
        }
    }

    override fun convertObjectToJsonStringAndSave(key: String, objectParam: Any) : Completable {
        return Completable.defer {
            val gson = Gson()
            val data = gson.toJson(objectParam)

            putString(key, data)
        }
    }

    override fun <T> convertJsonStringToObject(key: String, clazz: Class<T>): Single<T> {
        return getString(key)
            .flatMap { jsonData ->
                val gson = Gson()
                Single.just(gson.fromJson(jsonData, clazz))
            }
    }
}