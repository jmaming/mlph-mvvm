package com.mlph.mvvm_android.utils.rx.scheduler

import io.reactivex.CompletableTransformer
import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RxSchedulerImpl @Inject constructor() : RxScheduler {

    override fun forCompletable(): CompletableTransformer {
        return CompletableTransformer { upstream -> upstream.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())}
    }

    override fun <T> forFlowable(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream -> upstream.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())}
    }

    override fun <T> forObservable(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream -> upstream.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()) }
    }

    override fun <T> forSingle(): SingleTransformer<T, T> {
        return SingleTransformer { upstream -> upstream.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()) }
    }

}