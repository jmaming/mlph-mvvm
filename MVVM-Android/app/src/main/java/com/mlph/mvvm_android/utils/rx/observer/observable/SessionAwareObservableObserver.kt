package com.mlph.mvvm_android.utils.rx.observer.observable

import androidx.annotation.CallSuper
import com.mlph.mvvm_android.api.SessionExpiredException
import com.mlph.mvvm_android.utils.rx.observer.BaseSessionAwareObserver
import io.reactivex.Observer

abstract class SessionAwareObservableObserver<T> : BaseSessionAwareObserver,
        Observer<T> {

    @CallSuper
    override fun onError(e: Throwable) {
        if (e is SessionExpiredException) {
            onSessionExpiredError()
        } else {
            onCommonError(e)
        }
    }

    override fun onSessionExpiredError() {
    }

    override fun onCommonError(e: Throwable) {
    }

}