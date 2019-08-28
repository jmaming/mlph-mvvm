package com.mlph.mvvm_android.api

import io.reactivex.Single
import retrofit2.Retrofit

interface ApiFactory {

    /**
     * This will be used for API request that are not requiring an authentication token on header.
     *
     * e.g. Login Request, Forgot Password, etc.
     *
     * */
    fun <T> create(apiClass: Class<T>): Single<T>

    /**
     * This will be used if the API request requires an authentication token on it's header.
     * */
    fun <T> createWithHeader(apiClass: Class<T>) : Single<T>

    /**
     * Expose instance of {@link Retrofit} whenever other module is in need of it.
     * */
    fun retrofit(): Single<Retrofit>

}