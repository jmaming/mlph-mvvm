package com.mlph.mvvm_android.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.mlph.mvvm_android.utils.rx.scheduler.RxScheduler
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<N>() : ViewModel() {

    private val mIsLoading: ObservableBoolean = ObservableBoolean(false)

    private lateinit var rxScheduler: RxScheduler

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var mNavigator: N? = null

    constructor(rxScheduler: RxScheduler) : this() {
        this.rxScheduler = rxScheduler
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getCompositeDisposable(): CompositeDisposable {
        return compositeDisposable
    }

    fun getIsLoading(): ObservableBoolean {
        return mIsLoading
    }

    fun setIsLoading(isLoading: Boolean) {
        mIsLoading.set(isLoading)
    }

    fun getNavigator(): N? {
        return mNavigator
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = navigator
    }

    fun setRxScheduler(rxScheduler: RxScheduler) {
        this.rxScheduler = rxScheduler
    }

    fun getRxScheduler(): RxScheduler {
        return rxScheduler
    }

}