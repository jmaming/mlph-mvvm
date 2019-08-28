package com.mlph.mvvm_android.utils.rx.internet

import io.reactivex.Single

interface RxInternet {

    fun hasInternetConnection(): Single<Boolean>

}