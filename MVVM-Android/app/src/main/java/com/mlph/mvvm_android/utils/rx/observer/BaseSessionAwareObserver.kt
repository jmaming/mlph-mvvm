package com.mlph.mvvm_android.utils.rx.observer

interface BaseSessionAwareObserver {

    fun onSessionExpiredError()

    fun onCommonError(e: Throwable)

}