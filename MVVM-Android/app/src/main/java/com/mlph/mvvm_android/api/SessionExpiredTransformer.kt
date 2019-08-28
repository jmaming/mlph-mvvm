package com.mlph.mvvm_android.api

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import retrofit2.Response
import java.net.HttpURLConnection

class SessionExpiredTransformer<T> : SingleTransformer<Response<T>, Response<T>> {

    override fun apply(upstream: Single<Response<T>>): SingleSource<Response<T>> {
        return upstream.flatMap { response ->
            if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED)
                Single.error<Any>(SessionExpiredException())
            Single.just(response)
        }
    }

}